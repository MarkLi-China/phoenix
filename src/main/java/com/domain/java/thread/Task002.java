package com.domain.java.thread;

import java.util.concurrent.TimeUnit;

/**
 * com.domain.java.thread
 *
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/12/1
 */
public class Task002 implements Runnable {

    @Override
    public void run() {
        int i = 0;
        try {
            task(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void task(int i) throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println(String.format("Task run %s times...", ++i));
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public static void main(String[] args) {

        Thread thread = new Thread(new Task002());
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
