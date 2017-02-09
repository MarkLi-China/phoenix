package com.domain.java.thread.multi;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * volatile保证了可见性，但是不保证对变量操作的原子性
 * 采用Lock
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/2/9
 */
public class Test03 {

    public volatile int inc = 0;

    private Lock lock = new ReentrantLock();

    public void increase() {
        lock.lock();
        try {
            inc++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        final Test03 test03 = new Test03();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        test03.increase();
                    }
                }
            }.start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println("inc = " + test03.inc);
    }
}
