package team.dorm301.letterhome.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseActivity;

public class RegisterActivity extends BaseActivity {
    private String data1;
    private EditText Username;
    private EditText Passord;
    private EditText Tel;
    private EditText Email;
    private TextView Hint;
    private Button Button;
    public static int a;

    @Override
    protected int getContentView() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        UtilsBarStyle.setActionBar(this);
        //
//        getWindow().setStatusBarColor(0xff1E90FF);

        Username =findViewById(R.id.username_edit_text);
        Passord =findViewById(R.id.password_edit_text);
        Tel =findViewById(R.id.tel_edit_text);
        Email =findViewById(R.id.email_edit_text);
        Hint = findViewById(R.id.hint_text);
        Button = findViewById(R.id.login_button);
//        Button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String usernameText = Username.getText().toString();
//                String passwordText = Passord.getText().toString();
//                String telText = Tel.getText().toString();
//                String emailText = Email.getText().toString();
//                if (usernameText.isEmpty()) {
//                    Hint.setText("请输入账号");
//                    return;
//                } else if (passwordText.isEmpty()) {
//                    Hint.setText("请输入密码");
//                    return;
//                } else if (telText.isEmpty()) {
//                    Hint.setText("请输入手机号");
//                    return;
//                } else if (emailText.isEmpty()||!UtilsForm.isEmail(emailText)){
//                    Hint.setText("请输入合法电子邮箱");
//                    return;
//                }
//                /*Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                startActivity(intent);
//                finish();*/
//                User user = new User(usernameText,passwordText,telText,emailText);
//                HttpUtil.sendRegisterRequest(user, new okhttp3.Callback() {
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        ResponseType responseType = new Gson().fromJson(response.body().string(), ResponseType.class);
//                        Log.d("HttpUtil", responseType.getMsg());
//                        if (responseType.getCode() == 0) {
//                            //data1 = Username.getText().toString();
//                            Intent intent = new Intent();
//                            intent.putExtra("username_return",responseType.getData());
//                            setResult(RESULT_OK,intent);
//                            finish();
//                        }else {
//                            Looper.prepare();
//                            Toast.makeText(getApplicationContext(), responseType.getMsg(), Toast.LENGTH_SHORT).show();
//                            Looper.loop();
//                        }
//                    }
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        Log.d("RegisterActivity", "failed");
//                    }
//                });
//            }
//        });
    }


}
