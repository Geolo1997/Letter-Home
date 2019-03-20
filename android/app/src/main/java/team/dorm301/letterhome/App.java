package team.dorm301.letterhome;

import android.app.Application;

import team.dorm301.letterhome.config.Yunzhi;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Yunzhi.init()
                .setApi("http://192.168.2.110:8888")
                .setTimeout(10L);
    }
}
