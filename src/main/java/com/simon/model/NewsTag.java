package com.simon.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "news_tag")
public class NewsTag implements Serializable {
    private static final long serialVersionUID = -6089611811346962418L;
    @Id
    @GeneratedValue(generator = "sequenceId")
    @GenericGenerator(name = "sequenceId", strategy = "com.simon.common.utils.snowflake.SequenceId")
    private Long id;

    @Column(name = "news_info_id")
    private Long newsInfoId;

    @Column(name = "tag_id")
    private Long tagId;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return news_info_id
     */
    public Long getNewsInfoId() {
        return newsInfoId;
    }

    /**
     * @param newsInfoId
     */
    public void setNewsInfoId(Long newsInfoId) {
        this.newsInfoId = newsInfoId;
    }

    /**
     * @return tag_id
     */
    public Long getTagId() {
        return tagId;
    }

    /**
     * @param tagId
     */
    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
}