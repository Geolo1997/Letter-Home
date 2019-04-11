package team.dorm301.letterhome.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.spark.submitbutton.SubmitButton;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.Date;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseActivity;
import team.dorm301.letterhome.config.Yunzhi;
import team.dorm301.letterhome.dao.DAOService;
import team.dorm301.letterhome.entity.Letter;
import team.dorm301.letterhome.entity.User;
import team.dorm301.letterhome.service.LetterService;
import team.dorm301.letterhome.util.ActivityCollector;
import team.dorm301.letterhome.util.UtilsAnimator;
import team.dorm301.letterhome.util.UtilsForm;

public class WriteReceiverInformationActivity extends BaseActivity {

    @BindView(R.id.switch_email)
    SwitchCompat emailSwitch;
    @BindView(R.id.switch_rmail)
    SwitchCompat rmailSwitch;
    @BindView(R.id.receive_email_et)
    EditText receiveEmailEditText;
    @BindView(R.id.receive_email_form)
    LinearLayout receiveEmailForm;
    @BindView(R.id.receiver_edit_text)
    EditText receiverEditText;
    @BindView(R.id.tel_edit_text)
    EditText telEditText;
    @BindView(R.id.receiver_address_edit_text)
    EditText receiverAddressEditText;
    @BindView(R.id.receive_rmail_form)
    LinearLayout receiveRmailForm;
    @BindView(R.id.confirm_button)
    SubmitButton confirmButton;

    private Letter letter = new Letter();

    private LetterService letterService;

    @Override
    protected int getContentView() {
        return R.layout.activity_write_receiver_information;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle("发信");
        getToolbar().setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));

        letterService = Yunzhi.getBean(LetterService.class);
        setToolbarTitle("收信人信息");
        User user = new User();
        user.setUsername(DAOService.getInstance().getLogInfo().getUsername());
        letter.setUser(user);
        //接收上一步的信件信息
        Intent intent = getIntent();
        letter.setSubject(intent.getStringExtra("title"));
        letter.setContent(intent.getStringExtra("content"));

        /**
         * 发送信件方式1--switch_email
         */
        emailSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rmailSwitch.setChecked(false);
                    receiveEmailForm.setVisibility(View.VISIBLE);
                    UtilsAnimator.showOn(receiveEmailForm, 500);
                } else {
                    UtilsAnimator.showOff(receiveEmailForm, 500);
                    receiveEmailForm.setVisibility(View.GONE);
                }
            }
        });
        /**
         * 发送信件方式2--switch_rmail
         */
        rmailSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    emailSwitch.setChecked(false);
                    receiveRmailForm.setVisibility(View.VISIBLE);
                    UtilsAnimator.showOn(receiveRmailForm, 500);
                    UtilsAnimator.showOn(receiverAddressEditText);
                    UtilsAnimator.showOn(receiverEditText);
                    UtilsAnimator.showOn(telEditText);
                } else {
                    UtilsAnimator.showOff(receiveRmailForm, 500);
                    receiveRmailForm.setVisibility(View.GONE);
                }
            }
        });
    }


    /**
     * 填空逻辑判断
     */
    public String formJudge() {
        letter.setEmailTarget(receiveEmailEditText.getText().toString());
        letter.setRecipient(receiverEditText.getText().toString());
        letter.setTarget(receiverAddressEditText.getText().toString());
        letter.setTelephone(telEditText.getText().toString());
        if (!emailSwitch.isChecked() && !rmailSwitch.isChecked())
            return "请选择至少一种发送方式";
        else if (emailSwitch.isChecked()) {
            if (letter.getEmailTarget().isEmpty())
                return "邮箱地址不能为空";
            else if (!UtilsForm.isEmail(letter.getEmailTarget()))
                return "请输入合法邮箱地址";
        } else if (rmailSwitch.isChecked()) {
            if (letter.getRecipient().isEmpty()) return "收件人不能为空";
            else if (letter.getTelephone().isEmpty()) return "收件人电话不能为空";
            else if (letter.getTarget().isEmpty()) return "收件人地址不能为空";
        }
        return "formGood";
    }

    @OnClick(R.id.confirm_button)
    public void onViewClicked() {
        String formJudge = formJudge();
        if ("formGood".equals(formJudge)) {
            letter.setSendTime(new Date());
            letterService.sendLetter(letter)
                    .enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {

                            onSendLetterSuccess();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
        } else {
            showToast(formJudge);
        }
    }

    public void onSendLetterSuccess() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(WriteReceiverInformationActivity.this);
        dialog.setTitle("注意");
        dialog.setMessage("发送成功！");
        dialog.setCancelable(false);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               BaseActivity activity = ActivityCollector.getInstance().get(ActivityCollector.getInstance().size() - 2);
               activity.finish();
               finish();
            }
        });
        dialog.show();
    }
}
