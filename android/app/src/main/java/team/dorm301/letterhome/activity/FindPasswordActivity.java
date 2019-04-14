package team.dorm301.letterhome.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseActivity;
import team.dorm301.letterhome.entity.User;
import team.dorm301.letterhome.http.HttpClient;
import team.dorm301.letterhome.request.UserRequest;

public class FindPasswordActivity extends BaseActivity {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_confirm_code)
    EditText etConfirmCode;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.et_repeat_new_password)
    EditText etRepeatNewPassword;
    @BindView(R.id.hint_text)
    TextView hintText;
    @BindView(R.id.bt_get_confirm_code)
    Button btGetConfirmCode;
    @BindView(R.id.bt_save)
    Button btSave;

    String confirmCode;

    @Override
    protected int getContentView() {
        return R.layout.activity_find_password;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle("重置密码");
        setBackEnable(true);
    }

    @OnClick(R.id.bt_get_confirm_code)
    public void onBtGetConfirmCodeClicked() {
        String username = etUsername.getText().toString().trim();
        if ("".equals(username)) {
            hintText.setText("用户名不能为空");
            return;
        }
        HttpClient.request(UserRequest.class)
                .forgetPassword(username)
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        confirmCode = s;
//                        getConfirmCountDown();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        showErrorToast("网络错误");
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        confirmCode = response.body();
                        getConfirmCountDown();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        showErrorToast("网络错误");
                    }
                });
    }

    private void getConfirmCountDown() {
        int totalTime = 30000;
        int intervalTime = 1000;
        btGetConfirmCode.setClickable(false);
        btGetConfirmCode.setBackgroundColor(getColorOf(R.color.gray));
        new CountDownTimer(totalTime, intervalTime) {

            @Override
            public void onTick(long millisUntilFinished) {
                btGetConfirmCode.setText("" + (int) (millisUntilFinished / 1000) + "秒后重新获取");
            }

            @Override
            public void onFinish() {
                btGetConfirmCode.setText("获取验证码");
                btGetConfirmCode.setClickable(true);
                btGetConfirmCode.setBackgroundColor(getColorOf(R.color.colorPrimary));
            }
        };
    }

    @OnClick(R.id.bt_save)
    public void onBtSaveClicked() {
        if ("".equals(btGetConfirmCode.getText().toString().trim())) {
            hintText.setText("请输入验证码");
            return;
        }
        if ("".equals(etNewPassword.getText().toString().trim())) {
            hintText.setText("请输入新密码");
            return;
        }
        if (!etNewPassword.getText().toString().trim().equals(etRepeatNewPassword.getText().toString().trim())) {
            hintText.setText("两次输入密码不一致");
            return;
        }
        if (confirmCode != null && confirmCode.equals(etConfirmCode.getText().toString().trim())) {
            User user = new User();
            user.setUsername(etUsername.getText().toString().trim());
            user.setPassword(etRepeatNewPassword.getText().toString().trim());
            HttpClient.request(UserRequest.class)
                    .updatePassword(user)
                    .enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            showToast("修改成功！");
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            showErrorToast("修改失败");
                        }
                    });
        }
    }
}
