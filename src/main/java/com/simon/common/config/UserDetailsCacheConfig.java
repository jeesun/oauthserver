package com.simon.common.config;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.authentication.CachingUserDetailsService;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.cache.EhCacheBasedUserCache;
import org.springframework.util.Assert;

import java.lang.reflect.Constructor;

/**
 * 缓存UserDetails
 *
 * @author simon
 * @create 2018-08-03 23:52
 **/
@Slf4j
@Configuration
public class UserDetailsCacheConfig {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public UserCache userCache(){
        try {
            EhCacheBasedUserCache userCache = new EhCacheBasedUserCache();
            val cacheManager = CacheManager.getInstance();
            val cache = cacheManager.getCache("userCache");
            userCache.setCache(cache);
            return userCache;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        Constructor<CachingUserDetailsService> ctor = null;
        try {
            ctor = CachingUserDetailsService.class.getDeclaredConstructor(UserDetailsService.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Assert.notNull(ctor, "CachingUserDetailsService constructor is null");
        ctor.setAccessible(true);

        CachingUserDetailsService cachingUserDetailsService = BeanUtils.instantiateClass(ctor, customUserDetailsService);
        cachingUserDetailsService.setUserCache(userCache());
        return cachingUserDetailsService;
    }
}
