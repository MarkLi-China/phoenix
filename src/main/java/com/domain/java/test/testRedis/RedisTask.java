package com.domain.java.test.testRedis;

import com.domain.common.framework.redis.DefaultRedisService;

import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;

/**
 * TODO describe the file
 *
 * @author lijing
 * @version 1.0.0
 * @since 2017/12/3
 */
public class RedisTask implements Runnable {

    private DefaultRedisService redis;

    public RedisTask(DefaultRedisService redis) {
        this.redis = redis;
    }

    public void run() {

        System.out.println("thread start...");
        /*while (!shutdown) {
            String str = redis.brpop("xxx", 0, TimeUnit.SECONDS);
            System.out.println(str);
        }*/
        while (!Thread.currentThread().isInterrupted()) {
            String str = redis.brpop("xxx", 1, TimeUnit.SECONDS);
            if (str == null) continue;
            System.out.println(str);
        }
        System.out.println("thread end...");
    }
}
