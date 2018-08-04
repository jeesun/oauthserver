package com.simon.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author simon
 * @create 2018-07-25 22:17
 **/
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "news_tag")
public class NewsTag implements Serializable {
    private static final long serialVersionUID = -1622210657684111480L;
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
}
