package com.domain.java.first;

/**
 * Created with Intellij IDEA
 * @author QX
 * @version 1.0.0
 * @since 2015-7-29
 */
public class TestThread {

    public static void main(String[] args) {

        ThreadOne t1 = new ThreadOne();
        ThreadTwo t2 = new ThreadTwo();
        Thread one = new Thread(t1);
        Thread two = new Thread(t2);
        one.start();
        two.start();
    }
}

class Accum {

    private static Accum accum = new Accum();

    private int counter = 0;

    private Accum() {}

    public static Accum getInstance() {

        return accum;
    }

    public int getCounter() {

        return counter;
    }

    public void updateCounter(int add) {

        counter += add;
    }
}

class ThreadOne implements Runnable {

    Accum accum = Accum.getInstance();

    @Override
    public void run() {

        for (int i = 0; i < 98; i++) {
            accum.updateCounter(1000);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("one " + accum.getCounter());
    }
}

class ThreadTwo implements Runnable {

    Accum accum = Accum.getInstance();

    @Override
    public void run() {

        for (int i = 0; i < 99; i++) {
            accum.updateCounter(1);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("two " + accum.getCounter());
    }
}
