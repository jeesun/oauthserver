package com.simon.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * redis配置
 *
 * @author simon
 * @create 2018-06-19 17:08
 **/
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    private static Logger logger = Logger.getLogger(RedisConfig.class);

    @Autowired
    private RedisConnectionFactory factory;

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        //设置缓存过期时间
        cacheManager.setDefaultExpiration(60);//60秒
        cacheManager.setUsePrefix(true);//将key的前后缀合并在一起
        return cacheManager;
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(){
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }

    @Bean
    public KeyGenerator simpleKey(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName()+":");
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    @Bean
    public KeyGenerator objectId(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params){
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName()+":");
                try {
                    sb.append(params[0].getClass().getMethod("getId", null).invoke(params[0], null).toString());
                }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException no){
                    no.printStackTrace();
                }
                return sb.toString();
            }
        };
    }

    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                logger.error(key);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                logger.error(value);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                logger.error(key);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {

            }
        };
        return cacheErrorHandler;
    }
}
