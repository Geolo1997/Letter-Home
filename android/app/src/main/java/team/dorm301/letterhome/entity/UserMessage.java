package team.dorm301.letterhome.entity;

public class UserMessage {
    private String name;
    private String sex;
    private String email;
    private String phone;

    public UserMessage(String name, String sex, String email, String phone){
        this.name = name;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
    }
    public String getName(){return this.name;}
    public void setName(String name){this.name = name;}
    public String getSex(){return this.sex;}
    public void setSex(String sex){this.sex = sex;}
    public String getEmail(){return this.email;}
    public void setEmail(String email){this.email = email;}
    public String getPhone(){return this.phone;}
    public void setPhone(String phone){this.phone = phone;}

    @Override
    public String toString() {
        return "User{" +
                "username='" + name + '\'' +
                ", password='" + sex + '\'' +
                ", password='" + email + '\'' +
                ", email='" + phone + '\'' +
                '}';
    }



}
