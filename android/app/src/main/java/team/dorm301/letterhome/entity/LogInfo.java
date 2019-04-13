package team.dorm301.letterhome.entity;

public class LogInfo {
    private String username;
    private String password;
    private boolean savePassword;
    private boolean autoLogin;

    public LogInfo() {
    }

    public LogInfo(String username, String password, boolean savePassword, boolean autoLogin) {
        this.username = username;
        this.password = password;
        this.savePassword = savePassword;
        this.autoLogin = autoLogin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSavePassword() {
        return savePassword;
    }

    public void setSavePassword(boolean savePassword) {
        this.savePassword = savePassword;
    }

    public boolean isAutoLogin() {
        return autoLogin;
    }

    public void setAutoLogin(boolean autoLogin) {
        this.autoLogin = autoLogin;
    }
}
