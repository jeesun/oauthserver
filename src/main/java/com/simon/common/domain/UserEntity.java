package com.simon.common.domain;

import com.simon.model.Authority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * Created by simon on 2017/2/24.
 */
public class UserEntity implements UserDetails{
    private static final long serialVersionUID = 2834517421924891212L;

    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private String phone;
    private String email;
    private List<Authority> authorities;

    public UserEntity(Long id, String username, String password, boolean enabled, String phone, String email) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.phone = phone;
        this.email = email;
    }

    public UserEntity(Long id, String username, String password, boolean enabled, String phone, String email, List<Authority> authorities) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.phone = phone;
        this.email = email;
        this.authorities = authorities;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean getEnabled(){
        return enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public List<Authority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return this.username;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(null == obj){
            return false;
        }
        if(!(obj instanceof UserEntity)){
            return false;
        }
        return this.getId().equals(((UserEntity)obj).getId());
    }
}
