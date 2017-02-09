package com.domain.java.thread.multi;

/**
 * volatile保证了可见性，但是不保证对变量操作的原子性
 * 采用synchronized
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/2/9
 */
public class Test02 {

    public int inc = 0;

    public synchronized void increase() {
        inc++;
    }

    public static void main(String[] args) {

        final Test02 test02 = new Test02();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        test02.increase();
                    }
                }
            }.start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println("inc = " + test02.inc);
    }
}
