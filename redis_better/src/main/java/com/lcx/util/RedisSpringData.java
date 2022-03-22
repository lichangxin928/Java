package com.lcx.util;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisSpringData {

    private static final RedisTemplate redisTemplate;
    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
    static {
        redisTemplate = new RedisTemplate();

    }


}
