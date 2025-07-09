package com.ldk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        // 连接工厂
        template.setConnectionFactory(factory);

        // Key序列化
        template.setKeySerializer(new StringRedisSerializer());

        // Value序列化（使用GenericJackson2JsonRedisSerializer替代JdkSerializationRedisSerializer）
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        // Hash Key序列化
        template.setHashKeySerializer(new StringRedisSerializer());

        // Hash Value序列化
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        template.afterPropertiesSet();
        return template;
    }
}
