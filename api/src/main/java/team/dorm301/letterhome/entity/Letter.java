package team.dorm301.letterhome.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.CreationTimestamp;
import team.dorm301.letterhome.serialize.LetterJsonView;
import team.dorm301.letterhome.serialize.NoneJsonView;

import javax.persistence.*;
import java.util.Calendar;

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

    private String emailTarget;          // 邮件地址

    private String subject;              // 主题

    @Column(length = 3000)
    private String content;              // 内容

    @ManyToOne
    @JsonView({NoneJsonView.class,
            LetterJsonView.getLetterList.class})
    private User user;                   // 寄信人

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Calendar sendTime;

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

    public String getEmailTarget() {
        return emailTarget;
    }

    public void setEmailTarget(String emailTarget) {
        this.emailTarget = emailTarget;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public Calendar getSendTime() {
        return sendTime;
    }

    public void setSendTime(Calendar sendTime) {
        this.sendTime = sendTime;
    }
}
