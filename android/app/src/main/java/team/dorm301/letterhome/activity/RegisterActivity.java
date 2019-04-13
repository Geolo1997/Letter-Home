package team.dorm301.letterhome.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseActivity;
import team.dorm301.letterhome.entity.User;
import team.dorm301.letterhome.http.HttpClient;
import team.dorm301.letterhome.request.UserRequest;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.username_edit_text)
    EditText usernameEditText;
    @BindView(R.id.password_edit_text)
    EditText passwordEditText;
    @BindView(R.id.email_edit_text)
    EditText emailEditText;
    @BindView(R.id.hint_text)
    TextView hintText;
    @BindView(R.id.login_button)
    android.widget.Button loginButton;

    @Override
    protected int getContentView() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @OnClick(R.id.login_button)
    public void onViewClicked() {
        User user = new User();
        user.setUsername(usernameEditText.getText().toString());
        user.setPassword(passwordEditText.getText().toString());
        user.setEmail(emailEditText.getText().toString());
        HttpClient.request(UserRequest.class)
                .register(user)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        showToast("注册成功！");
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        showErrorToast("网络错误");
                    }
                });
    }
}
