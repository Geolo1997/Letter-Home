package team.dorm301.letterhome.entity;

public class LogInfo {
    private User user;
    private boolean savePassword;
    private boolean antoLogin;

    public LogInfo() {
    }

    public LogInfo(User user, boolean savePassword, boolean antoLogin) {
        this.user = user;
        this.savePassword = savePassword;
        this.antoLogin = antoLogin;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getPassword() {
        return user.getPassword();
    }
}
