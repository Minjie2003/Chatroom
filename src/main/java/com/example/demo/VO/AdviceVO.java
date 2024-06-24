package com.example.demo.VO;

import com.example.demo.enity.Advice;
import lombok.Data;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-12-08
 * Time: 0:13
 */
@Data
public class AdviceVO extends Advice {
    public String username;
    public String accountNum;
    public String headPath;

    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool("tcp://127.0.0.1:8888");
        Jedis jedis = jedisPool.getResource();
        String s = jedis.ping();
        System.out.println(s);
        jedis.exists("key1","key2");
        jedis.close();
    }
}
