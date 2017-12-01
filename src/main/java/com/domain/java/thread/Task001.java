package com.domain.java.thread;

import java.util.concurrent.TimeUnit;

/**
 * com.domain.java.thread
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/12/1
 */
public class Task001 implements Runnable {

    @Override
    public void run() {
        int i = 0;
        while (!Thread.interrupted()) {
            System.out.println(String.format("Task run %s times...", ++i));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                // 必须再次触发中断
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {

        Thread thread = new Thread(new Task001());
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ready to interrupt thread: " + thread.getName());
        thread.interrupt();
    }
}
