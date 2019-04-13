package team.dorm301.letterhome.entity;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {

    private long id;
    private Date publishTime;
    private String author;
    private String title;
    private String content;
    private String imgUrl;

    public News() {
    }

    public News(long id, Date publishTime, String author, String title, String content) {
        this.id = id;
        this.publishTime = publishTime;
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
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
}
