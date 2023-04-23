package com.example.itheima_reggie_take;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringDataRedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 操作string 类型数据
     */
    @Test
    public void testString() {
        redisTemplate.opsForValue().set("city", "beijing");
        String city = (String) redisTemplate.opsForValue().get("city");
        System.out.println(city);
    }

    /**
     * 操作hash 类型数据
     */
    @Test
    public void testHash() {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put("002", "name", "xiaoming");
    }

}
