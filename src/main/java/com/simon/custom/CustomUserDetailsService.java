package com.simon.custom;

import com.simon.config.AppConfig;
import com.simon.domain.UserEntity;
import com.simon.model.Authority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Slf4j
@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private MessageSource messageSource;

    private Locale locale = AppConfig.getLocale();

    private final String sqlLoadUser;
    private final String sqlLoadAuthorities;
    private final RowMapper<UserEntity> myUserDetailsRowMapper;
    private final RowMapper<Authority> authorityRowMapper;

    public CustomUserDetailsService() {
        super();
        sqlLoadUser = "select id,username,password,enabled,phone,email from users where username=? OR phone=? OR email=?";
        sqlLoadAuthorities = "select user_id,authority from authorities where user_id = ?";

        myUserDetailsRowMapper = (rs, i) -> new UserEntity(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getString("phone"), rs.getString("email"));

        authorityRowMapper = (rs,i) -> new Authority(rs.getLong(1), rs.getString(2));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try{
            UserEntity userFromQuery = jdbcTemplate.queryForObject(sqlLoadUser, myUserDetailsRowMapper, s, s, s);
            log.info("查询得到用户：{}", userFromQuery);
            List<Authority> authorities = jdbcTemplate.query(sqlLoadAuthorities, authorityRowMapper, userFromQuery.getId());
            log.info("得到其权限：{}", authorities);

            return new UserEntity(userFromQuery.getId(), userFromQuery.getUsername(), userFromQuery.getPassword(), userFromQuery.isEnabled(), userFromQuery.getPhone(), userFromQuery.getEmail(), authorities);
        }catch (EmptyResultDataAccessException e){
            log.info("查询结果集为空：{}", s);
            throw new InvalidGrantException(messageSource.getMessage("usernameNotFound", null, locale));
        }
    }
}
