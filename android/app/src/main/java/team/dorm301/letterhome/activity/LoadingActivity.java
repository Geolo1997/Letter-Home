package team.dorm301.letterhome.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseActivity;
import team.dorm301.letterhome.dao.DAOService;
import team.dorm301.letterhome.entity.LogInfo;
import team.dorm301.letterhome.network.HttpService;
import team.dorm301.letterhome.network.NetworkCallback;
import team.dorm301.letterhome.network.api.UserAPI;

public class LoadingActivity extends BaseActivity {

    private final int COUNT_DOWN_TOTAL_TIME = 5000;
    private final int COUNT_DOWN_INTERVAL_TIME = 1000;
    private CountDownTimer cut;

    //@BindView(R.id.tv_timer)
   // TextView timer;
    @BindView(R.id.b_button1)
    Button bButton1;

    @Override
    protected int getContentView() {
        return R.layout.activity_loading;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cut = new CountDownTimer(COUNT_DOWN_TOTAL_TIME, COUNT_DOWN_INTERVAL_TIME) {
            @Override
            public void onTick(long millisUntilFinished) {
                bButton1.setText("" + (int) ((millisUntilFinished / 1000)+1));
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
    public void checkLogInf(){
        LogInfo logInfo = DAOService.getInstance().getCurrentLogInfo();
        if (logInfo != null) { // 保存有登录信息
            HttpService.getInstance().getAPI(UserAPI.class)
                    .login(logInfo.getUsername(), logInfo.getPassword()).enqueue(new NetworkCallback<Void>() {
                @Override
                public void onSuccess(Void responseData) {
                    startActivityAndFinish(MainActivity.class);
                }

                @Override
                public void onError(int errorCode, String errorMessage) {
                    startActivityAndFinish(LoginActivity.class);
                }

                @Override
                public void onFailure() {
                    startActivityAndFinish(LoginActivity.class);
                }
            });
        } else {
            startActivityAndFinish(MainActivity.class);
        }
    }
}
