package team.dorm301.letterhome.entity;

import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private String password;
    private boolean sex;
    private String telephone;
    private String email;

    public User() {
    }

    public User(String username, String password, boolean sex, String telephone, String email) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.telephone = telephone;
        this.email = email;
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

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
