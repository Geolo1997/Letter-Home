package team.dorm301.letterhome.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import butterknife.BindView;
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

    @BindView(R.id.tv_timer)
    TextView timer;

    @Override
    protected int getContentView() {
        return R.layout.activity_loading;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new CountDownTimer(COUNT_DOWN_TOTAL_TIME, COUNT_DOWN_INTERVAL_TIME) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText("" + (int) millisUntilFinished);
            }

            @Override
            public void onFinish() {
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
        }.start();
    }
}
