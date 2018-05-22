package com.mfish.utils;

import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Properties;

public class JedisConnectUtil {

    @Value("${redis.ip}")
    String ip;
    @Value("${redis.port}")
    int port;
    @Value("${jedis.pool.maxTotal}")
    int maxTotal;
    @Value("${jedis.pool.maxIdle}")
    int maxIdle;
    @Value("${jedis.pool.maxWaitMillis}")
    static int maxWaitMillis;
    @Value("${jedis.pool.testOnBorrow}")
    boolean testOnBorrow;
    @Value("${jedis.pool.testOnReturn}")
    boolean testOnReturn;
    @Value("${redis.timeout}")
    static int timeout;


    private static JedisPool pool = null;


    /**
     * 获取jedispool 连接池
     * @return
     */
    private static JedisPool getPool() {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            Properties prop = new Properties();
            try {
                //加载properties配置文件
                prop.load(JedisConnectUtil.class.getClassLoader().getResourceAsStream("redis.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
            //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
            config.setMaxTotal(Integer.valueOf(prop.getProperty("jedis.pool.maxTotal").trim()));
            //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            config.setMaxIdle(Integer.valueOf(prop.getProperty("jedis.pool.maxIdle").trim()));
            //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
            config.setMaxWaitMillis(maxWaitMillis);
            //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(true);

            //pool = new JedisPool(config, prop.getProperty("redis.ip"),Integer.parseInt(prop.getProperty("redis.port")));
            pool = new JedisPool(config, prop.getProperty("redis.ip"),Integer.parseInt(prop.getProperty("redis.port")),timeout,prop.getProperty("redis.pass"));
        }
        return pool;
    }


    /**
     * 返回资源
     * @param jedis
     */
    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }


    /**
     * 获取jedis 实例
     * @return
     * @throws Exception
     */
    public static Jedis getJedis() throws Exception {
        Jedis jedis = null;
        pool = getPool();
        jedis = pool.getResource();
        return jedis;
    }


    /**
     * 得到 key 值
     * @param key
     * @return
     */
    public static String getnx(String key) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = getJedis();
            value = jedis.get(key);
        } catch (Exception e) {
            if (jedis != null) {
                jedis.close();
            }
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
        return value;
    }


    /***
     * 设置key value,如果key已经存在则返回0,nx==> not exist
     * @param
     * @return 成功返回1 如果存在 和 发生异常 返回 0
     */
    public static Long setnx(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.setnx(key, value);
        } catch (Exception e) {
            if (jedis != null) {
                jedis.close();
            }
            e.printStackTrace();
            return 0l;
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
    }




}
