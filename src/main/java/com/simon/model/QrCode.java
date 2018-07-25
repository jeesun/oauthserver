package com.simon.model;

import javax.persistence.*;

/**
 * @author simon
 * @create 2018-07-25 22:17
 **/

@Entity
@Table(name = "qr_code")
public class QrCode {
    private long id;
    private Boolean isOk;
    private String sid;
    private String token;
    private String username;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "is_ok")
    public Boolean getOk() {
        return isOk;
    }

    public void setOk(Boolean ok) {
        isOk = ok;
    }

    @Basic
    @Column(name = "sid")
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

        QrCode qrCode = (QrCode) o;

        if (id != qrCode.id) return false;
        if (sid != null ? !sid.equals(qrCode.sid) : qrCode.sid != null) return false;
        if (token != null ? !token.equals(qrCode.token) : qrCode.token != null) return false;
        if (username != null ? !username.equals(qrCode.username) : qrCode.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (sid != null ? sid.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}
