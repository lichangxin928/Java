package com.lcx.redis;

import com.lcx.util.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisTest {

    @Test
    public void test1(){
        // 1. 获取连接
        Jedis je = new Jedis("127.0.0.1",6379);
        // 2. 操作
        je.set("username","zhangsan");
        // 3. 关闭连接
        je.close();
    }

    /**
     * 对 String 操作
     */
    @Test
    public void testString(){
        // 1. 获取连接
        Jedis je = new Jedis();
        // 2. 操作
        String username = je.get("username");
        System.out.println(username);

        // 使用 setes() 方法存储可以指定过期时间的 key value
        je.setex("psw",3,"123");
        String psw = je.get("psw");
        System.out.println(psw);
        try {
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(je.get("psw"));
        // 3. 关闭连接
        je.close();
    }

    @Test
    public void testHash(){
        // 获取连接
        Jedis jedis = new Jedis();
        // 执行操作
        jedis.hset("set1","user","lcx");
        String user = jedis.hget("set1", "user");
        System.out.println(user);
        Map<String, String> set1 = jedis.hgetAll("set1");
        System.out.println(set1);
        // 关闭连接
        jedis.close();
    }

    @Test
    public void testList(){
        Jedis jedis = new Jedis();
        jedis.lpush("mylist","a","b");
        jedis.rpush("mylist","c","d");

        List<String> mylist = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist);
        jedis.close();
    }
    @Test
    public void testSet(){
        Jedis jedis = new Jedis();
        jedis.sadd("set","a","b","C");

        Set<String> set = jedis.smembers("set");
        System.out.println(set);
        jedis.close();
    }
    @Test
    public void testSortSet(){
        Jedis jedis = new Jedis();
        jedis.zadd("sortSet",10,"abc");
        jedis.zadd("sortSet",20,"def");

        Set<String> sortSet = jedis.zrange("sortSet", 0, -1);
        System.out.println(sortSet);

        jedis.close();
    }

    @Test
    public void testPool(){

        JedisPoolConfig config = new JedisPoolConfig();

        JedisPool jedisPool = new JedisPool(config,"localhost",6379);

        Jedis resource = jedisPool.getResource();
        String username = resource.get("username");
        System.out.println(username);
        jedisPool.close();
    }
    @Test
    public void poolUtil(){
        Jedis jedis = JedisPoolUtils.getJedis();
        jedis.set("a","b");
        System.out.println(jedis.get("a"));
        jedis.close();
    }
}
