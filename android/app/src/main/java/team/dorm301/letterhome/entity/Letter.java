package team.dorm301.letterhome.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * 信件实体
 */
public class Letter implements Serializable {

    private Long id;                     // 主键
    private String recipient;            // 收件人
    private String target;               // 目标地址
    private String emailTarget;          // 邮件地址
    private String subject;              // 主题
    private String content;              // 内容
    private User user;                   // 寄信人
    private Date sendTime;

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

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getEmailTarget() {
        return emailTarget;
    }

    public void setEmailTarget(String emailTarget) {
        this.emailTarget = emailTarget;
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

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
