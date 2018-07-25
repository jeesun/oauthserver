package com.simon.model;

import javax.persistence.*;

/**
 * @author simon
 * @create 2018-07-25 22:17
 **/

@Entity
@Table(name = "news_tag")
public class NewsTag {
    private long id;
    private Long newsInfoId;
    private Long tagId;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "news_info_id")
    public Long getNewsInfoId() {
        return newsInfoId;
    }

    public void setNewsInfoId(Long newsInfoId) {
        this.newsInfoId = newsInfoId;
    }

    @Basic
    @Column(name = "tag_id")
    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsTag newsTag = (NewsTag) o;

        if (id != newsTag.id) return false;
        if (newsInfoId != null ? !newsInfoId.equals(newsTag.newsInfoId) : newsTag.newsInfoId != null) return false;
        if (tagId != null ? !tagId.equals(newsTag.tagId) : newsTag.tagId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (newsInfoId != null ? newsInfoId.hashCode() : 0);
        result = 31 * result + (tagId != null ? tagId.hashCode() : 0);
        return result;
    }
}
