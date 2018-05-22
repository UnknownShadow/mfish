package com.mfish.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Administrator on 2018/5/18.
 */
public class T {

    public static void main(String[] args) {

        Jedis jedis = null;
        try {

            jedis = JedisConnectUtil.getJedis();
            jedis.set("test", "https://test-1255594100.cosgz.myqcloud.com/test.txt");
            jedis.expire("test", 30);

        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            //返还到连接池
            if(jedis != null) JedisConnectUtil.returnResource(jedis);
        }


    }
}