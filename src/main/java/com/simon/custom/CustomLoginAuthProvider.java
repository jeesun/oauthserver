package com.simon.custom;

import com.simon.domain.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Locale;

@Component
public class CustomLoginAuthProvider implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(CustomLoginAuthProvider.class);
    @Resource
    private CustomUserDetailsService userDetailsService;

    @Resource
    private MessageSource messageSource;

    private Locale locale = LocaleContextHolder.getLocale();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.info("authenticate");

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserEntity userEntity = (UserEntity) userDetailsService.loadUserByUsername(username);
        if (null == userEntity){
            throw new UsernameNotFoundException(messageSource.getMessage("usernameNotFound", null, locale));
        }
        if (!userEntity.isEnabled()){
            throw new DisabledException(messageSource.getMessage("accountDisabled", null, locale));
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);

        if(!encoder.matches(password, userEntity.getPassword())){
            throw new InvalidGrantException(messageSource.getMessage("passwordError", null, locale));
        }

        Collection<? extends GrantedAuthority> authorities = userEntity.getAuthorities();
        return new UsernamePasswordAuthenticationToken(userEntity, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.equals(aClass);
    }
}
