package team.dorm301.letterhome.entity;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.security.crypto.password.PasswordEncoder;
import team.dorm301.letterhome.config.ContextConfig;
import team.dorm301.letterhome.serialize.NoneJsonView;
import team.dorm301.letterhome.serialize.UserJsonView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User 实体
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                     // 主键

    @Column(nullable = false, unique = true)
    private String username;             // 用户名

    @Column(nullable = false)
    private String password;             // 密码

    private String name;                 // 姓名

    private Boolean sex;                 // 性别

    private String telephone;            // 手机号

    @OneToMany(mappedBy = "user")
    @JsonView({NoneJsonView.class,
            UserJsonView.getCurrentLoginUser.class})
    private List<Letter> letters = new ArrayList<>();        // 信件列表

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Letter> getLetters() {
        return letters;
    }

    public void setLetters(List<Letter> letters) {
        this.letters = letters;
    }

    @PrePersist
    public void encode() {
        PasswordEncoder encoder = ContextConfig.getContext().getBean(PasswordEncoder.class);
        this.password = encoder.encode(this.password);
    }
}
