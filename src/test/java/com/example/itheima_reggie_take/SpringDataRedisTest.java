package com.example.itheima_reggie_take;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringDataRedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 操作string 类型数据
     * 这里我们需要改变redis 的序列号
     */
    @Test
    public void testString() {
        redisTemplate.opsForValue().set("city", "beijing");
        String city = (String) redisTemplate.opsForValue().get("city");
//        redisTemplate.delete("city");
        System.out.println(city);
        // 设置过期时间
        redisTemplate.opsForValue().set("key1", "value1", 10l, TimeUnit.SECONDS);
    }

    /**
     * 操作hash 类型数据
     */
    @Test
    public void testHash() {
        HashOperations hashOperations = redisTemplate.opsForHash();

        //存值
        hashOperations.put("002", "name", "xiaoming");
        hashOperations.put("002", "age", "20");
        hashOperations.put("002", "address", "bj");

        //取值
        String name = (String) hashOperations.get("002", "name");
        String age = (String) hashOperations.get("002", "age");
        System.out.println(name + age);

        //获取hash 结构中的所有字段
        Set keys = hashOperations.keys("002");
        for (Object key : keys) {
            System.out.println("jqk===" + key);
            System.out.println((String) key);
        }

        //后去hash 结构中的所有值
        List values = hashOperations.values("002");

        for (Object value : values) {
            System.out.println("value==" + value);
            System.out.println("======");
            System.out.println(value);
        }

    }

    /**
     * 操作List 类型的数据
     */
    @Test
    public void testList() {
        ListOperations listOperations = redisTemplate.opsForList();

        // 存值
        listOperations.leftPush("mylist", "a");
        listOperations.leftPushAll("mylist", "b", "c", "d", "e");

        //取值
        List<String> mylist = listOperations.range("mylist", 0, -1);
        for (String value : mylist) {
            System.out.println(value);
        }

        // 获得列表长度llen
        Long size = listOperations.size("mylist");
        int llsize = size.intValue();
        for (int i = 0; i < llsize; i++) {
            // 出队列
            String element = (String) listOperations.rightPop("mylist");
            System.out.println(element);
        }
    }

    /**
     * 操作 set 类型的数据
     */
    @Test
    public void testSet() {
        SetOperations setOperations = redisTemplate.opsForSet();

    }

}
