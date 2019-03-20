package team.dorm301.letterhome.activity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseActivity;
import team.dorm301.letterhome.http.HttpClient;
import team.dorm301.letterhome.service.AuthService;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.status_bar)
    View statusBar;
    @BindView(R.id.username_edit_text)
    EditText usernameEditText;
    @BindView(R.id.password_edit_text)
    EditText passwordEditText;
    @BindView(R.id.hint_text)
    TextView hintText;
    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.forget_text)
    TextView forgetText;
    @BindView(R.id.register_text)
    TextView registerText;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.login_button)
    public void onViewClicked() {
        Log.d(TAG, "获取用户名和密码");
        String username = this.usernameEditText.getText().toString();
        String password = this.passwordEditText.getText().toString();

        Log.d(TAG, "BASIC 认证");
        String credentials = username + ":" + password;
        String basicAuth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

        Log.d(TAG, "请求登录");
        HttpClient.request(AuthService.class)
                .login(basicAuth)
                .subscribeOn(Schedulers.io())                  // 在IO线程发起网络请求
                .observeOn(AndroidSchedulers.mainThread())     // 在主线程处理
                .subscribe(new Observer<Void>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        showLongToast("onSubscribe");
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        showLongToast("onNext");
                    }

                    @Override
                    public void onError(Throwable e) {
                        showLongToast("onError");
                    }

                    @Override
                    public void onComplete() {
                        showLongToast("onComplete");
                    }
                });
    }
}
