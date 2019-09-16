package com.simon.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ehcache缓存配置，若要使用Ehcache缓存，请注释RedisConfig的@Configuration和@EnableCaching注解，并取消EhcacheConfig的@Configuration和@EnableCaching注解的注释。
 *
 * @author simon
 * @create 2018-08-04 8:37
 **/

@Slf4j
@Configuration
@EnableCaching
public class EhcacheConfig extends CachingConfigurerSupport {
    @Override
    @Bean
    public org.springframework.cache.CacheManager cacheManager(){

        return new EhCacheCacheManager(net.sf.ehcache.CacheManager.getInstance());
    }

    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }
}
