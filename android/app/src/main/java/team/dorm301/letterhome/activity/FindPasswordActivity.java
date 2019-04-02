package team.dorm301.letterhome.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseActivity;

public class FindPasswordActivity extends BaseActivity {
    private EditText username;
    private EditText email;
    private TextView hint;
    private android.widget.Button Button;

    @Override
    protected int getContentView() {
        return R.layout.activity_find_password;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();
    }
    protected void initLayout() {
//        UtilsBarStyle.setActionBar(this);
        username = findViewById(R.id.username_edit_text);
        email = findViewById(R.id.email_edit_text);
        hint = findViewById(R.id.hint_text);
        Button = findViewById(R.id.login_button);
//        Button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String usernameText = username.getText().toString();
//                String emailText = email.getText().toString();
//                if (usernameText.isEmpty()) {
//                    hint.setText("请输入账号");
//                    return;
//                } else if (emailText.isEmpty() || !UtilsForm.isEmail(emailText)) {
//                    hint.setText("请输入合法电子邮箱");
//                    return;
//                }
//                HttpUtil.findPasswordRequest(usernameText, emailText, new okhttp3.Callback() {
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        ResponseType responseType = new Gson().fromJson(response.body().string(), ResponseType.class);
//                        Log.d("HttpUtil", responseType.getMsg());
//                        if (responseType.getCode() == 0) {
//                            hint.setText("您的密码是:" + responseType.getData());
//                        } else {
//                            hint.setText(responseType.getMsg());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        Log.d("找回密码", "失败");
//                    }
//                });
//            }
//        });
    }
}
