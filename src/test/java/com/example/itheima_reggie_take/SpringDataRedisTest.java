package com.example.itheima_reggie_take;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.*;
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
        // 存值
        setOperations.add("myset", "a", "b", "c", "d");
        // 取值
        Set<String> myset = setOperations.members("myset");

        for (String o : myset) {
            System.out.println("第一次取值" + o);
        }

        // 删除成员
        setOperations.remove("myset", "a", "b");

        // 取值
        Set<String> myset1 = setOperations.members("myset");

        for (String o : myset1) {
            System.out.println("第二次取值" + o);
        }

    }

    /**
     * 操作Zset类型的数据
     * 有序集合
     * 唯一的
     */
    @Test
    public void testZset() {
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        //存值
        zSetOperations.add("myZset", "a", 10.0);
        zSetOperations.add("myZset", "b", 12.0);
        zSetOperations.add("myZset", "c", 9.0);
        zSetOperations.add("myZset", "a", 10.0);
        //取值
        Set<String> myZset = zSetOperations.range("myZset", 0, -1);
        for (String s : myZset) {
            System.out.println(s);
        }
        //修改分数
        zSetOperations.incrementScore("myZset", "b", 20.0);
        myZset = zSetOperations.range("myZset", 0, -1);
        for (String s : myZset) {
            System.out.println("修改分数后---" + s);
        }
        //删除成员
        zSetOperations.remove("myZset", "a", "b");
        myZset = zSetOperations.range("myZset", 0, -1);
        for (String s : myZset) {
            System.out.println("删除后分数---" + s);
        }
    }

    /**
     * 通用操作，针对不同的数据类型都可以操作
     */
    @Test
    public void testCommon() {
        //获取redis中所有的key
        Set<String> keys = redisTemplate.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }
        //判断某个key是否存在
        Boolean itcast = redisTemplate.hasKey("itcast");
        System.out.println(itcast);
        //删除指定key
        Boolean myZset = redisTemplate.delete("myZset");
        System.out.println(myZset);

        //获取指定key 对应的value的数据类型
        DataType dataType = redisTemplate.type("myset");
        System.out.println(dataType.name());
    }
}
