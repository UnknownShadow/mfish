package com.mfish.monitor;

import com.mfish.services.JedisProcessor;
import com.mfish.utils.JedisConnectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//创建的类名根据需要定义，但一定要实现ServletContextListener接口  
public class WebContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub  
        //这里可以放你要执行的代码或方法
        System.out.println("项目启动时执行....");
        Thread thread = new Thread(new JedisProcessor());
        thread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub  
    }


}  