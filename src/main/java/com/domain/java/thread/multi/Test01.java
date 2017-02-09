package com.domain.java.thread.multi;

/**
 * volatile保证了可见性，但是不保证对变量操作的原子性
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/2/9
 */
public class Test01 {

    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {

        final Test01 test01 = new Test01();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        test01.increase();
                    }
                }
            }.start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        Thread th[] = new Thread[2];
        Thread.enumerate(th);
        System.out.println(th[0] + "...." + th[1]); // Thread[main,5,main]....Thread[Monitor Ctrl-Break,5,main]

        System.out.println("inc = " + test01.inc);
    }
}
