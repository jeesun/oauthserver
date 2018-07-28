package com.simon.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author simon
 * @create 2018-07-25 22:17
 **/

@Entity
@Table(name = "log_login")
public class LogLogin {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogLogin logLogin = (LogLogin) o;

        if (id != logLogin.id) return false;
        if (createTime != null ? !createTime.equals(logLogin.createTime) : logLogin.createTime != null) return false;
        if (ip != null ? !ip.equals(logLogin.ip) : logLogin.ip != null) return false;
        if (username != null ? !username.equals(logLogin.username) : logLogin.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}
