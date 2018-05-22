package com.mfish.monitor;

import com.mfish.utils.qcloud.cos.CosUtil;
import redis.clients.jedis.JedisPubSub;


/**
 * @ClassName: ExpiredMessageListener
 * @Description:TODO(rediskey失效监听)
 */
public class ExpiredMessageListener extends JedisPubSub {

    @Override
    public void onMessage(String channel, String message) {
        System.out.println("onMessage");
    }


    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println("redis中key的过期监听中......");

        System.out.println("channel:" + channel + "receives message :" + message);

        //-------------对该key进行业务处理-------------

        //根据该过期的Key删除腾讯云cos中相对应的对象，
        CosUtil c = new CosUtil();

        System.out.println("根据key："+message+"删除腾讯云cos中的对象");
        //删除文件
        String msg = c.delete("/"+message+".dll");
        System.out.println(msg);

        // 关闭释放资源
        /*c.cosClient.shutdown();
		System.out.println("shutdown!");*/
    }



    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println("channel:" + channel + "is been subscribed:" + subscribedChannels);
    }


    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        System.out.println("onPUnsubscribe");
    }


    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println("onPSubscribe，开启redis过期监听......");
    }


    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println("channel:" + channel + "is been unsubscribed:" + subscribedChannels);
    }


    /**
     * 测试
     */
    /*public static void main(String[] args) throws Exception {

        Jedis jedis = JedisConnectUtil.getJedis();

        jedis.select(2);

        jedis.set("oneKey", "hello");
        jedis.expire("oneKey", 10);

        jedis.set("twoKey", "word");
        jedis.expire("twoKey", 20);

        RedisMsgPubSubListener listener = new RedisMsgPubSubListener();

        jedis.subscribe(listener, "__keyevent@3__:expired");
    }*/

}