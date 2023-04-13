package com.example.itheima_reggie_take;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

public class JedisTest {
    @Test
    public void testRedis() {
        //1、获取链接
        Jedis jedis = new Jedis();

        //2、执行具体的操作
        jedis.set("age", String.valueOf(20));
        String age = jedis.get("age");
        System.out.println(age);

        Set<String> keys = jedis.keys("*");
        System.out.println(keys);

        for (String key : keys ) {
            System.out.println("循环获取"+key);
        }

        // 删除
        jedis.del("age");

        //3、关闭链接
        jedis.close();
    }
}
