package com.mfish.services;

import com.mfish.monitor.ExpiredMessageListener;
import com.mfish.utils.JedisConnectUtil;
import redis.clients.jedis.Jedis;

/**
 * 起一个线程执行jedis监听事件
 */
public class JedisProcessor implements Runnable {

    @Override
    public void run() {
        subscriber();
    }


    /**
     *  开启 jedis监听事件
     */
    private void subscriber(){
        Jedis jedis = null;
        try {
            jedis = JedisConnectUtil.getJedis();
            jedis.psubscribe(new ExpiredMessageListener(), "__key*__:*");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                JedisConnectUtil.returnResource(jedis);
            }
        }
    }
}