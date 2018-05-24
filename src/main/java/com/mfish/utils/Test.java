package com.mfish.utils;

import com.mfish.entity.Carousel;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Administrator on 2018/5/18.
 */
public class Test {

    public static void main(String[] args) throws Exception {

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


        //测试 序列化存储
        Test.serializeMemory();
    }

    /**
     * 测试 将对象序列化后存入redis，从redis中读取数据 反序列化后 成为原始对象
     */
    public static void serializeMemory() throws Exception {
        Jedis jedis = JedisConnectUtil.getJedis();

        // 操作实体类对象
        Carousel good= new Carousel();
        good.setUrl( "测试" );
        good.setImgUrl("序列化存储");

        byte[] serialize = ObjectUtil.serialize(good);
        System.out.println(serialize);

        jedis.set("good".getBytes(), ObjectUtil.serialize(good));


        byte[] value = jedis.get("good".getBytes());
        Object object = ObjectUtil. unSerialize(value);
        if(object!= null){
            Carousel goods=(Carousel) object;
            System. out.println(goods.getImgUrl());
            System. out.println(goods.getUrl());
        }
    }
}