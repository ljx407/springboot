package com.ljx.chapter7.configuration;

import com.ljx.chapter7.listeners.RedisMessageListener;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;

@Configuration
public class RedisConfiguration {
    private RedisConnectionFactory redisConnectionFactory = null ;

    @Bean
    public RedisConnectionFactory initRedisConnectionFactory() {

        if(null != redisConnectionFactory) {
            return redisConnectionFactory;
        }

        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(5);
        genericObjectPoolConfig.setMaxTotal(10);

        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder()
                .usePooling()
                .poolConfig(genericObjectPoolConfig).build();

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("localhost");
        redisStandaloneConfiguration.setPort(6379);
        redisStandaloneConfiguration.setPassword("123456");


        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration,jedisClientConfiguration);
        this.redisConnectionFactory = jedisConnectionFactory;
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate initRedisTemplate() {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(initRedisConnectionFactory());
//        template.setDefaultSerializer(template.getStringSerializer());
        template.setHashKeySerializer(template.getStringSerializer());
        template.setHashValueSerializer(template.getStringSerializer());
        template.setKeySerializer(template.getStringSerializer());
//        template.setValueSerializer(template.getStringSerializer());
        return template;
    }

    @Bean
    public RedisMessageListener initRedisMessageListener() {
        return new RedisMessageListener();
    }

    @Bean
    public RedisMessageListenerContainer initRedisMessageListenerContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        Topic topic = new ChannelTopic("book");
        container.setConnectionFactory(initRedisConnectionFactory());
        container.addMessageListener(initRedisMessageListener(),topic);
        return container;
    }

}
