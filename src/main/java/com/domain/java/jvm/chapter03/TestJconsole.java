package com.domain.java.jvm.chapter03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * -Xms100M -Xmx100M -XX:+UseSerialGC
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/9/19
 */
public class TestJconsole {

    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {

        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        // fillHeap(1000);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        br.readLine();
        Object obj = new Object();
        createLockThread(obj);
    }

    public static void createBusyThread() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    ;
            }
        }, "testBusyThread");
        thread.start();
    }

    public static void createLockThread(final Object lock) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "testLockThread");
        thread.start();
    }
}
