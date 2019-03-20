package team.dorm301.letterhome.entity;

import javax.persistence.*;

/**
 * 信件实体
 */
@Entity
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                     // 主键

    private String recipient;            // 收件人

    private String target;               // 目标地址

    @Column(length = 3000)
    private String content;              // 内容

    @ManyToOne
    private User user;                   // 寄信人

    public Letter() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}