package com.simon.common.config;

import com.simon.common.domain.UserEntity;
import com.simon.common.plugins.oauth.IntegrationAuthentication;
import com.simon.common.plugins.oauth.IntegrationAuthenticationContext;
import com.simon.common.plugins.oauth.IntegrationAuthenticator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CustomUserDetailsService implements UserDetailsService {

    /*@Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private MessageSource messageSource;

    private Locale locale = AppConfig.getLocale();

    private final String sqlLoadUser;
    private final String sqlLoadAuthorities;
    private final RowMapper<UserEntity> myUserDetailsRowMapper;
    private final RowMapper<Authority> authorityRowMapper;

    public CustomUserDetailsService(){
        sqlLoadUser = "select id,username,password,enabled,phone,email,address,birth,age,head_photo,person_brief,sex from t_users where username=? OR phone=? OR email=?";
        sqlLoadAuthorities = "select user_id,authority from t_authorities where user_id = ?";

        myUserDetailsRowMapper = (rs, i) -> new UserEntity(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getString("phone"), rs.getString("email"),rs.getString("address"), rs.getDate("birth"), rs.getInt("age"), rs.getString("head_photo"), rs.getString("person_brief"), rs.getBoolean("sex"));

        authorityRowMapper = (rs,i) -> {
            Authority authority = new Authority();
            authority.setUserId(rs.getLong(1));
            authority.setAuthority(rs.getString(2));
            return authority;
        };
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("loadUserByUsername");
        try{
            UserEntity userFromQuery = jdbcTemplate.queryForObject(sqlLoadUser, myUserDetailsRowMapper, s, s, s);
            log.info("查询得到用户：{}", userFromQuery);
            List<Authority> authorities = jdbcTemplate.query(sqlLoadAuthorities, authorityRowMapper, userFromQuery.getId());
            log.info("得到其权限：{}", authorities);

            return new UserEntity(userFromQuery.getId(), userFromQuery.getUsername(), userFromQuery.getPassword(), userFromQuery.isEnabled(), userFromQuery.getPhone(), userFromQuery.getEmail(), userFromQuery.getAddress(), userFromQuery.getBirth(), userFromQuery.getAge(), userFromQuery.getHeadPhoto(), userFromQuery.getPersonBrief(), userFromQuery.getSex(), authorities);
        }catch (EmptyResultDataAccessException e){
            log.info("查询结果集为空：{}", s);
            throw new InvalidGrantException(messageSource.getMessage("usernameNotFound", null, locale));
        }
    }*/

    private List<IntegrationAuthenticator> authenticators;

    @Autowired(required = false)
    public void setIntegrationAuthenticators(List<IntegrationAuthenticator> authenticators) {
        this.authenticators = authenticators;
    }

    @Override
    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        IntegrationAuthentication integrationAuthentication = IntegrationAuthenticationContext.get();
        //判断是否是集成登录
        if (integrationAuthentication == null) {
            integrationAuthentication = new IntegrationAuthentication();
        }
        integrationAuthentication.setUsername(username);
        UserEntity userEntity = this.authenticate(integrationAuthentication);

        if(userEntity == null){
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        return userEntity;

    }

    private UserEntity authenticate(IntegrationAuthentication integrationAuthentication) {
        if (this.authenticators != null) {
            for (IntegrationAuthenticator authenticator : authenticators) {
                if (authenticator.support(integrationAuthentication)) {
                    return authenticator.authenticate(integrationAuthentication);
                }
            }
        }
        return null;
    }
}
