package team.dorm301.letterhome.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseActivity;
import team.dorm301.letterhome.config.Yunzhi;
import team.dorm301.letterhome.entity.Auth;
import team.dorm301.letterhome.service.AuthService;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.username_edit_text)
    EditText usernameEditText;
    @BindView(R.id.password_edit_text)
    EditText passwordEditText;

    private AuthService authService;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.authService = Yunzhi.getBean(AuthService.class);
    }
    @OnClick(R.id.login_button)
    public void onViewClicked() {
        startActivity(MainActivity.class);
    }
//    @OnClick(R.id.login_button)
//    public void onViewClicked() {
//        Log.d(TAG, "获取用户名和密码");
//        String username = this.usernameEditText.getText().toString();
//        String password = this.passwordEditText.getText().toString();
//
//        Log.d(TAG, "请求登录");
//        this.authService.login(username, password)
//            .subscribe(new Observer<Auth>() {
//                @Override
//                public void onSubscribe(Disposable d) {
//
//                }
//
//                @Override
//                public void onNext(Auth auth) {
//                    Yunzhi.setToken(auth.getToken());
//                    startActivity(MainActivity.class);
//                }
//
//                @Override
//                public void onError(Throwable e) {
//                    showLongToast("用户名或密码错误");
//                }
//
//                @Override
//                public void onComplete() {
//
//                }
//            });
//    }
}
