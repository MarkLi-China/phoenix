package com.domain.java.test.testRedis;

import com.domain.common.framework.redis.DefaultRedisService;

import java.util.concurrent.TimeUnit;

/**
 * TODO describe the file
 *
 * @author lijing
 * @version 1.0.0
 * @since 2017/12/3
 */
public class RedisThread extends Thread {

    private DefaultRedisService redis;

    private boolean shutdown = false;

    public RedisThread(DefaultRedisService redis) {
        this.redis = redis;
    }

    public void run() {

        System.out.println("thread start...");
        while (!shutdown) {
            String str = redis.brpop("xxx", 0, TimeUnit.SECONDS);
            System.out.println(str);
        }
        System.out.println("thread end...");
    }

    public void shutdown() {
        System.out.println("shutdown...");
        this.shutdown = true;
    }

    public void shutdown1() {
        System.out.println("shutdown1...");
        this.interrupt();
    }
}
