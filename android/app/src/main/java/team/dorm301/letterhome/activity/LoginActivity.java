package team.dorm301.letterhome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;
import org.litepal.crud.DataSupport;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseActivity;
import team.dorm301.letterhome.entity.User;

public class LoginActivity extends BaseActivity {


    private EditText UsernameEditText;
    private EditText passwordEditText;
    private TextView hintTextView;
    private TextView forgetTextView;
    private TextView registerTextView;
    private Button loginButton;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String returnedUsername = data.getStringExtra("username_return");
            UsernameEditText.setText(returnedUsername);
            passwordEditText.requestFocus();
        }
    }

    protected void initLayout() {   //初始化布局、关联控件
//        UtilsBarStyle.StatusBarLightMode(this);
        UsernameEditText = findViewById(R.id.username_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        hintTextView = findViewById(R.id.hint_text);
        loginButton = findViewById(R.id.login_button);
        forgetTextView = findViewById(R.id.forget_text);
        registerTextView = findViewById(R.id.register_text);
        /**
         *
         *
         * 登陆
         */
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameText = UsernameEditText.getText().toString(), passwordText =
                        passwordEditText.getText().toString();
                if (usernameText.isEmpty()) {
                    hintTextView.setText("请输入账号");
                    return;
                } else if (passwordText.isEmpty()) {
                    hintTextView.setText("请输入密码");
                    return;
                }
                else if (usernameText.equals("123456") && passwordText.equals("123456"))
                    startActivity(MainActivity.class);
//                final User user = new User(usernameText, passwordText, "0", "");
//                HttpUtil.sendLoginRequest(user, new okhttp3.Callback() {
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        ResponseType responseType = new Gson().fromJson(response.body().string(), ResponseType.class);
//                        Log.d("HttpUtil", responseType.getMsg());
//                        if (responseType.getCode() == 0) {
//                            ActivityCollector.setLoginUser(user);
//                            user.save();
//                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                            startActivity(intent);
//                            finish();
//                        } else {
//                            Looper.prepare();
//                            Toast.makeText(getApplicationContext(), responseType.getMsg(), Toast.LENGTH_SHORT).show();
//                            Looper.loop();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        Log.d("LoginActivity", "failed");
//                        hintTextView.setText("网络错误，请重试");
//                        return;
//                    }
//                });
            }
        });
        /**
         * 注册账号
         */
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        /**
         * 忘记密码
         */
        forgetTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, FindPasswordActivity.class);
                startActivity(intent);
            }
        });
        //自动登录
        //DataSupport.deleteAll(User.class);
//        automaticLogin();
    }

//    private void automaticLogin() {
//        List<User> userList = DataSupport.findAll(User.class);
//        if (userList.size() != 0) {
////            ActivityCollector.setLoginUser(userList.get(0));
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
//    }
}
