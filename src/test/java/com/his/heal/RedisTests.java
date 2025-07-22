package com.his.heal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class RedisTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void stringGet() {
        var ops = stringRedisTemplate.opsForValue();
        var he = ops.get("hello1");
        System.out.println(he);
    }

    @Test
    void stringSet() {
        var ops = stringRedisTemplate.opsForValue();
        ops.set("hello1", "world1");
    }


    @Test
    void set(){
       var ops = redisTemplate.opsForValue();
       ops.set("hello", "world");
    }

    @Test
    void get(){
        var ops = redisTemplate.opsForValue();
        var he = ops.get("hello");
        System.out.println(he);
    }
}
