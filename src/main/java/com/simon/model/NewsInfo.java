package com.simon.model;


import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author simon
 * @create 2018-07-24 0:01
 **/

@Entity
@Table(name = "news_info")
public class NewsInfo {
    private Long id;
    private String title;
    private Long userId;
    private Integer status;
    private String content;
    private String imageUrl;
    private Timestamp publishDate;
    private String tags;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Basic
    @Column(name = "publish_date")
    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    @Basic
    @Column(name = "tags")
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsInfo newsInfo = (NewsInfo) o;

        if (id != newsInfo.id) return false;
        if (title != null ? !title.equals(newsInfo.title) : newsInfo.title != null) return false;
        if (userId != null ? !userId.equals(newsInfo.userId) : newsInfo.userId != null) return false;
        if (status != null ? !status.equals(newsInfo.status) : newsInfo.status != null) return false;
        if (content != null ? !content.equals(newsInfo.content) : newsInfo.content != null) return false;
        if (imageUrl != null ? !imageUrl.equals(newsInfo.imageUrl) : newsInfo.imageUrl != null) return false;
        if (publishDate != null ? !publishDate.equals(newsInfo.publishDate) : newsInfo.publishDate != null)
            return false;
        if (tags != null ? !tags.equals(newsInfo.tags) : newsInfo.tags != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (publishDate != null ? publishDate.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }
}
