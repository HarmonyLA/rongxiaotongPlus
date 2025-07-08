package com.ldk.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 因为 redis 中key 都是String 类型  的， 但是 redis中的value 可以有上面的几种类型  ，
 * 为了方便做转换，最好封装一个转换数据类型的配置
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Object>  template =new RedisTemplate<>();
        ObjectMapper om= new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        GenericJackson2JsonRedisSerializer jsonserializer = new GenericJackson2JsonRedisSerializer();
        template.setConnectionFactory(factory);
        RedisSerializer<String> redisSerializer =new StringRedisSerializer();
        // 设置 key 和  value的序列化 规则
        template.setKeySerializer(redisSerializer);
        template.setValueSerializer(jsonserializer);
        // 设置 hash key  和 hash value的序列化 规则
        template.setHashKeySerializer(redisSerializer);
        template.setHashValueSerializer(jsonserializer);
        return  template;
    }
}
