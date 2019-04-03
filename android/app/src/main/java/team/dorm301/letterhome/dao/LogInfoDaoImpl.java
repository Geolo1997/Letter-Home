package team.dorm301.letterhome.dao;

import android.content.Context;
import android.content.SharedPreferences;
import team.dorm301.letterhome.App;
import team.dorm301.letterhome.entity.LogInfo;

public class LogInfoDaoImpl implements LogInfoDao {

    private static final String SHARED_PREFERENCES_NAME = "LOG_INFO";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public LogInfoDaoImpl() {
        sharedPreferences = App.getContext().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void save(LogInfo logInfo) {
        editor = sharedPreferences.edit();
        editor.clear();
        editor.putString("username", logInfo.getUsername());
        editor.putString("password", logInfo.getPassword());
        editor.putBoolean("autoLogin", logInfo.isAutoLogin());
        editor.putBoolean("savePassword", logInfo.isSavePassword());
        editor.commit();
    }

    @Override
    public LogInfo getLogInfo() {
        LogInfo logInfo = new LogInfo();
        logInfo.setUsername(sharedPreferences.getString("username", ""));
        logInfo.setPassword(sharedPreferences.getString("password", ""));
        logInfo.setAutoLogin(sharedPreferences.getBoolean("autoLogin", false));
        logInfo.setSavePassword(sharedPreferences.getBoolean("savePassword", false));
        return null;
    }
}
