package com.simon.common.config;

import com.simon.common.domain.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Locale;

/**
 * @author simon
 * @date 2019-05-17
 */
@Slf4j
@Component
public class CustomLoginAuthProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PasswordEncoder encoder;

    private Locale locale = AppConfig.getLocale();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("authenticate");

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserEntity userEntity = (UserEntity) userDetailsService.loadUserByUsername(username);
        if (null == userEntity) {
            throw new UsernameNotFoundException(messageSource.getMessage("usernameNotFound", null, locale));
        }
        if (!userEntity.isEnabled()) {
            throw new DisabledException(messageSource.getMessage("accountDisabled", null, locale));
        }

        if (!encoder.matches(password, userEntity.getPassword())) {
            throw new BadCredentialsException(messageSource.getMessage("passwordError", null, locale));
        }

        Collection<? extends GrantedAuthority> authorities = userEntity.getAuthorities();
        return new UsernamePasswordAuthenticationToken(userEntity, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.equals(aClass);
    }
}
