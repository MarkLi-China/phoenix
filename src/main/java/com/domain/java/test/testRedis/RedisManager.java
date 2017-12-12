package com.domain.java.test.testRedis;

import com.domain.common.framework.redis.DefaultRedisService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.*;

/**
 * TODO describe the file
 *
 * @author lijing
 * @version 1.0.0
 * @since 2017/12/3
 */
public class RedisManager {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-test.xml");
        DefaultRedisService redis = (DefaultRedisService) context.getBean("defaultRedisService");
        System.out.println(redis.get("xxx"));
        // redis.brpop("xxx", 0, TimeUnit.SECONDS);

        /*RedisThread thread = new RedisThread(redis);
        thread.start();
        System.out.println("thread " + thread.getName() + " is running...");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("shutdown thread " + thread.getName());
        thread.shutdown1();*/

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(new RedisTask(redis));
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        future.cancel(true);
        System.out.println("end...");
        executorService.shutdown();
    }
}
