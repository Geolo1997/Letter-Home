package team.dorm301.letterhome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.BindView;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseActivity;

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
    Button confirmButton;
    private String title, content, formJudge, receiver, tel, receiverAddress, receiveEmail;
    private int sendMethod;


    @Override
    protected int getContentView() {
        return R.layout.activity_write_receiver_information;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        UtilsBarStyle.setActionBar(this);
        //接收上一步的信件信息
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");

//        /**
//         * 发送信件方式1--switch_email
//         */
//        emailSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                    receiveEmailForm.setVisibility(View.VISIBLE);
//                    UtilsAnimator.showOn(receiveEmailForm,500);
//                }else {
//                    UtilsAnimator.showOff(receiveEmailForm,500);
//                    receiveEmailForm.setVisibility(View.GONE);
//                }
//            }
//        });
//        /**
//         * 发送信件方式2--switch_rmail
//         */
//        rmailSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                    receiveRmailForm.setVisibility(View.VISIBLE);
//                    UtilsAnimator.showOn(receiveRmailForm,500);
//                    UtilsAnimator.showOn(receiverAddressEditText);
//                    UtilsAnimator.showOn(receiverEditText);
//                    UtilsAnimator.showOn(telEditText);
//                }else {
//                    UtilsAnimator.showOff(receiveRmailForm,500);
//                    receiveRmailForm.setVisibility(View.GONE);
//                }
//            }
//        });
//
//        confirmButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                formJudge = formJudge();
//                if (!formJudge.equals("formGood")) Toast.makeText(getApplicationContext(), formJudge, Toast
//                .LENGTH_SHORT).show();
//                else {
//                    if(emailSwitch.isChecked()) {
//                        if(rmailSwitch.isChecked())sendMethod=3;
//                        else sendMethod = 1;
//                    } else  sendMethod = 2;
//                    Mail mail = new Mail();
//                    mail.setSenderUsername(ActivityCollector.getLoginUser().getUsername())
//                            .setReceiveAddress(receiver + "," + tel + "," + receiverAddress)
//                            .setTitle(title)
//                            .setContent(content)
//                            .setSendTime(new Date()).setSendMethod(sendMethod).setToEmail(receiveEmail);
//                    HttpUtil.sendSendMailRequest(mail, new okhttp3.Callback() {
//                        @Override
//                        public void onResponse(Call call, Response response) throws IOException {
//                            final ResponseType responseType = new Gson().fromJson(response.body().string(),
//                            ResponseType.class);
//                            if (responseType.getCode() == 0) {
//                                //发送成功
//                                Log.d("WriteMailContent", "发送邮件成功");
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        AlertDialog.Builder dialog = new AlertDialog.Builder
//                                        (WriteReceiverInformationActivity.this);
//                                        dialog.setTitle("注意");
//                                        dialog.setMessage(responseType.getMsg());
//                                        dialog.setCancelable(false);
//                                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialogInterface, int i) {
//                                                Intent intent = new Intent(WriteReceiverInformationActivity.this,
//                                                MainActivity.class);
//                                                startActivity(intent);
//                                            }
//                                        });
//                                        dialog.show();
//                                    }
//                                });
//                            } else {
//                                //发送失败
//                            }
//
//                        }
//
//                        @Override
//                        public void onFailure(Call call, IOException e) {
//                            //网络错误
//                            Log.d("SendMail", "网络错误");
//                            Toast.makeText(WriteReceiverInformationActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//
//            }
//        });
    }


    /**
     * 填空逻辑判断
     */
    public String formJudge() {
        receiveEmail = receiveEmailEditText.getText().toString();
        receiver = receiverEditText.getText().toString();
        receiverAddress = receiverAddressEditText.getText().toString();
        tel = telEditText.getText().toString();
        if (!emailSwitch.isChecked() && !rmailSwitch.isChecked()) return "请选择至少一种发送方式";
        else if (emailSwitch.isChecked()) {
            if (receiveEmail.isEmpty())
                return "邮箱地址不能为空";
//            else if(!UtilsForm.isEmail(receiveEmail))
            return "请输入合法邮箱地址";
        } else if (rmailSwitch.isChecked()) {
            if (receiver.isEmpty()) return "收件人不能为空";
            else if (tel.isEmpty()) return "收件人电话不能为空";
            else if (receiverAddress.isEmpty()) return "收件人地址不能为空";
        }
        return "formGood";
    }
}
