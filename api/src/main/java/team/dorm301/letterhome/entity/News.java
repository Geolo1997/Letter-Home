package team.dorm301.letterhome.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Calendar;

@Entity
public class News {

    @Id
    private String id;

    private String author;

    private String title;

    private String content;

    private String imgUrl;

    @Transient
    private Calendar publishTime;

    public News() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Calendar getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Calendar publishTime) {
        this.publishTime = publishTime;
    }
}
