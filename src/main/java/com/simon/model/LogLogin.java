package com.simon.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author simon
 * @create 2018-07-25 22:17
 **/
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "log_login")
public class LogLogin implements Serializable {
    private static final long serialVersionUID = -4527200937557834187L;

    private long id;
    private Date createTime;
    private String ip;
    private String username;

    public LogLogin() {
    }

    public LogLogin(Date createTime, String ip, String username) {
        this.createTime = createTime;
        this.ip = ip;
        this.username = username;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
