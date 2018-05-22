package com.mfish.mytask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
//@PropertySource("classpath:cron.properties")
public class Task {

    private Logger logger = LoggerFactory.getLogger(getClass());


    //@Scheduled(cron = "0 */5 * ? * *")
    public void statisticalFinancialDaily() {
        //获取当前时间，录入，
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String sysDate = df.format(new Date());// new Date()为获取当前系统时间

        logger.info("统计日报时间：" + sysDate);
        long begin = System.currentTimeMillis();


        //日志输出：
        long end = System.currentTimeMillis();
        logger.info("财务日报统计结束，共耗时：[" + (end - begin) / 1000 + "]秒");
    }


    //查询合伙人下的所有下级  0 */1 * ? * *
    @Scheduled(cron = "0 1 16 ? * *")
    public void partnerData() {
       /* Jedis jedis = null;
        try {
            logger.info("启动redis监听......");
            jedis = JedisConnectUtil.getJedis();
            jedis.psubscribe(new ExpiredMessageListener(), "__key*__:*");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                JedisConnectUtil.returnResource(jedis);
            }
        }*/
    }

}
