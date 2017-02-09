package com.domain.java.thread.multi;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile保证了可见性，但是不保证对变量操作的原子性
 * 采用AtomicInteger
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/2/9
 */
public class Test04 {

    public AtomicInteger inc = new AtomicInteger();

    public void increase() {
        inc.getAndIncrement();
    }

    public static void main(String[] args) {

        final Test04 test04 = new Test04();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        test04.increase();
                    }
                }
            }.start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println("inc = " + test04.inc);
    }
}
