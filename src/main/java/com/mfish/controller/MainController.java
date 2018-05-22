package com.mfish.controller;

import com.mfish.monitor.ExpiredMessageListener;
import com.mfish.utils.JedisConnectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;


@RestController
public class MainController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /*@Autowired
    UserDao userdao;*/


    /**
     * 游戏下载
     */
    @RequestMapping("download")
    public ModelAndView down(@RequestParam(value = "channelId", defaultValue = "1") int channelId) {
        ModelAndView mv = new ModelAndView();
        /*Channel channel = channelDao.find(channelId);
        mv.addObject("ios_url", channel.getIos_url());*/
        mv.setViewName("download");
        return mv;
    }

    //测试用 缓存数据
    @RequestMapping("/test")
    @ResponseBody
    public String val() {

        Jedis jedis = null;   //获取池

        String value = "";
        String tips="";

        try {
            jedis = JedisConnectUtil.getJedis();

            jedis.select(2);

            jedis.set("oneKey", "hello");
            jedis.expire("oneKey", 20);

            jedis.set("msdia80", "https://test-1255594100.cosgz.myqcloud.com/msdia80.dll");
            jedis.expire("msdia80", 20);

            /*ExpiredMessageListener listener = new ExpiredMessageListener();

            jedis.subscribe(listener, "__keyevent@3__:expired");*/


            //List<String> clubs = jedis.lrange("ID", 0, -1);

           /* String id = jedis.get("test");
            if(id == null || "".equals(id)){

                tips="From Mysql：<br>";

                List list = new ArrayList();
                list.add("1");
                list.add("2");
                list.add("3");
                list.add("4");
                list.add("5");

                logger.info("redis中没有数据！");
                jedis.set("test","10010");
            }else{
                logger.info("从redis中取得ID值为：{}",id);
            }*/


            //jedis操作List
            //开始前，先移除所有的内容
           /* jedis.del("java framework");
            logger.info("输出的List值：{}",jedis.lrange("java framework",0,-1));

            //先向key java framework中存放三条数据
            jedis.lpush("java framework","spring");
            jedis.lpush("java framework","struts");
            jedis.lpush("java framework","hibernate");

            // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
            logger.info("输出的List值：{}",jedis.lrange("java framework",0,-1));

            jedis.del("java framework");
            jedis.rpush("java framework","spring");
            jedis.rpush("java framework","struts");
            jedis.rpush("java framework","hibernate");

            logger.info("输出的List值：{}",jedis.lrange("java framework",0,-1));*/
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JedisConnectUtil.returnResource(jedis);
        }

        return tips+value;
    }

}
