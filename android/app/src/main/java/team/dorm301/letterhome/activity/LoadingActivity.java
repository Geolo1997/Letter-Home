package team.dorm301.letterhome.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import butterknife.BindView;
import butterknife.OnClick;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseActivity;
import team.dorm301.letterhome.http.HttpCallBack;
import team.dorm301.letterhome.config.Yunzhi;
import team.dorm301.letterhome.dao.DAOService;
import team.dorm301.letterhome.entity.Auth;
import team.dorm301.letterhome.entity.LogInfo;
import team.dorm301.letterhome.service.AuthService;

public class LoadingActivity extends BaseActivity {

    private static final int COUNT_DOWN_TOTAL_TIME = 5000;
    private static final int COUNT_DOWN_INTERVAL_TIME = 1000;
    //@BindView(R.id.tv_timer)
    // TextView timer;
    @BindView(R.id.b_button1)
    Button bButton1;
    private CountDownTimer cut;

    private AuthService authService;

    @Override
    protected int getContentView() {
        return R.layout.activity_loading;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authService = Yunzhi.getBean(AuthService.class);
        cut = new CountDownTimer(COUNT_DOWN_TOTAL_TIME, COUNT_DOWN_INTERVAL_TIME) {
            @Override
            public void onTick(long millisUntilFinished) {
                bButton1.setText("" + (int) ((millisUntilFinished / 1000) + 1));
            }

            @Override
            public void onFinish() {
                checkLogInf();
            }
        };
        cut.start();
    }

    @OnClick(R.id.b_button1)
    public void onViewClicked() {
        cut.cancel();
        checkLogInf();
    }

    public void checkLogInf() {
        LogInfo logInfo = DAOService.getInstance().getCurrentLogInfo();
        if (logInfo != null) {
            authService.login(logInfo.getUsername(), logInfo.getPassword())
                    .subscribe(new HttpCallBack<Auth>() {
                        @Override
                        public void onSuccess(Auth auth) {
                            Yunzhi.setToken(auth.getToken());
                            startActivityAndFinish(MainActivity.class);
                        }

                        @Override
                        public void onError(int code, String message) {
                            startActivityAndFinish(LoginActivity.class);
                        }

                        @Override
                        public void onFailure() {
                            startActivityAndFinish(LoginActivity.class);
                        }
                    });
        } else {
            startActivityAndFinish(LoginActivity.class);
        }
    }
}
