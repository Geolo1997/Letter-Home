package team.dorm301.letterhome;

import android.app.Application;

import android.content.Context;
import org.litepal.LitePal;
import team.dorm301.letterhome.config.Yunzhi;
import team.dorm301.letterhome.service.AuthService;
import team.dorm301.letterhome.service.AuthServiceImpl;

public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Yunzhi.init()
                .setApi("http://192.168.2.110:8888")
                .setTimeout(1L)
                .registerBean(AuthService.class, new AuthServiceImpl());
        // LitePal初始化
        LitePal.initialize(getApplicationContext());
        // 获取Context
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
