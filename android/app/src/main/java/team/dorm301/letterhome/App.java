package team.dorm301.letterhome;

import android.app.Application;
import android.content.Context;
import org.litepal.LitePal;
import team.dorm301.letterhome.config.Yunzhi;
import team.dorm301.letterhome.service.AuthService;
import team.dorm301.letterhome.service.AuthServiceImpl;
import team.dorm301.letterhome.service.LetterService;
import team.dorm301.letterhome.service.LetterServiceImpl;

public class App extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Yunzhi.init()
                .setApi("http://192.168.3.12:8888")
                .setTimeout(10L)
                .registerBean(AuthService.class, new AuthServiceImpl())
                .registerBean(LetterService.class, new LetterServiceImpl());
        // LitePal初始化
        LitePal.initialize(getApplicationContext());
        // 获取Context
        context = getApplicationContext();
    }
}
