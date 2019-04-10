package team.dorm301.letterhome.util;

import java.util.ArrayList;
import java.util.List;
import team.dorm301.letterhome.base.BaseActivity;

public class ActivityCollector extends ArrayList<BaseActivity> {

    private static final ActivityCollector INSTANCE = new ActivityCollector();

    private ActivityCollector() {
    }

    public static ActivityCollector getInstance() {
        return INSTANCE;
    }

    public void finishAll() {
        for (BaseActivity activity : this) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        clear();
    }
}
