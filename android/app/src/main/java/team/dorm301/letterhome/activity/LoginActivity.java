package team.dorm301.letterhome.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseActivity;
import team.dorm301.letterhome.config.Yunzhi;
import team.dorm301.letterhome.dao.DAOService;
import team.dorm301.letterhome.entity.Auth;
import team.dorm301.letterhome.entity.LogInfo;
import team.dorm301.letterhome.service.AuthService;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.username_edit_text)
    EditText usernameEditText;
    @BindView(R.id.password_edit_text)
    EditText passwordEditText;
    @BindView(R.id.cb_save_password)
    CheckBox cbSavePassword;
    @BindView(R.id.cb_auto_login)
    CheckBox cbAutoLogin;

    private AuthService authService;
    private CustomVideoView videoview;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        this.authService = Yunzhi.getBean(AuthService.class);
        loadLogInfo();
    }
    private void initView() {
        //加载视频资源控件
        videoview = (CustomVideoView) findViewById(R.id.videoview);
        //设置播放加载路径
        videoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video));
        //播放
        videoview.start();
        //循环播放
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoview.start();
            }
        });
    }

    //返回重启加载
    @Override
    protected void onRestart() {
        initView();
        super.onRestart();
    }

    //防止锁屏或者切出的时候，音乐在播放
    @Override
    protected void onStop() {
        videoview.stopPlayback();
        super.onStop();
    }
    private void loadLogInfo() {
        LogInfo logInfo = DAOService.getInstance().getLogInfo();
        if (logInfo != null) {
            usernameEditText.setText(logInfo.getUsername());
            passwordEditText.setText(logInfo.getPassword());
            cbSavePassword.setChecked(logInfo.isSavePassword());
            cbAutoLogin.setChecked(logInfo.isAutoLogin());
        }
    }

    @OnClick(R.id.login_button)
    public void onViewClicked() {
        Log.d(TAG, "获取用户名和密码");
        String username = this.usernameEditText.getText().toString();
        String password = this.passwordEditText.getText().toString();
        boolean savePassword = cbSavePassword.isChecked();
        boolean autoLogin = cbAutoLogin.isChecked();
        final LogInfo logInfo = new LogInfo(username, password, savePassword, autoLogin);
        Log.d(TAG, "请求登录");
        this.authService.login(username, password)
                .subscribe(new Observer<Auth>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Auth auth) {
                        Yunzhi.setToken(auth.getToken());
                        DAOService.getInstance().saveLogInfo(logInfo);
                        startActivityAndFinish(MainActivity.class);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showErrorToast("用户名或密码错误");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @OnClick(R.id.bt_register)
    public void toRegisterView() {
        startActivity(RegisterActivity.class);
    }

    @OnClick(R.id.bt_forget)
    public void toForgetPassword() {
        startActivity(FindPasswordActivity.class);
    }
}