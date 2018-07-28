package com.simon.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Table(name = "authorities")
@Entity
public class Authority implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 6565100352773737675L;
    @Id
    private Long userId;

    private String authority;

    public Authority() {
    }

    public Authority(String authority) {
        this.authority = authority;
    }

    public Authority(Long userId, String authority) {
        this.userId = userId;
        this.authority = authority;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority == null ? null : authority.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority1 = (Authority) o;
        return Objects.equals(userId, authority1.userId) &&
                Objects.equals(authority, authority1.authority);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, authority);
    }

    @Override
    public String toString() {
        return "Authority{" +
                "userId=" + userId +
                ", authority='" + authority + '\'' +
                '}';
    }
}