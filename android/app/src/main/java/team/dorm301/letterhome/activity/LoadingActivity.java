package team.dorm301.letterhome.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
    @BindView(R.id.iv_logo)
    LinearLayout ivLogo;

    private AuthService authService;

    @Override
    protected int getContentView() {
        return R.layout.activity_loading;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authService = Yunzhi.getBean(AuthService.class);
        togetherProperty(ivLogo);
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
        LogInfo logInfo = DAOService.getInstance().getLogInfo();
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

    public void togetherProperty(View myView) {
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(myView, "alpha", 0f, 1.0f);
//        ObjectAnimator transXAnim = ObjectAnimator.ofFloat(myView, "translationX", 100, 400);
        ObjectAnimator transYAnim = ObjectAnimator.ofFloat(myView, "translationY", 120f, 0);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(alphaAnim, transYAnim);
        set.setDuration(1500);
        set.start();
    }
}
