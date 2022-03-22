package com.lcx;


import com.lcx.util.JedisConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;


public class redis {

    private Jedis jedis;


    @BeforeEach
    void getConnect(){
        jedis = JedisConnectionFactory.getJedis();
    }


    @Test
    void redisTest(){
        jedis.set("name","lcx");
        System.out.println(jedis.get("name"));
    }

    @AfterEach
    void close(){
        if (jedis!=null){
            jedis.close();
        }
    }

}
