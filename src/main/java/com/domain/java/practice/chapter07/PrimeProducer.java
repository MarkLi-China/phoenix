package com.domain.java.practice.chapter07;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * PrimeProducer
 * <p/>
 * Using interruption for cancellation
 *
 * @author Brian Goetz and Tim Peierls
 */
public class PrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;

    PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            System.out.println(Thread.currentThread().getName()); // Thread-0
            while (!Thread.currentThread().isInterrupted())
                queue.put(p = p.nextProbablePrime());
        } catch (InterruptedException consumed) {
            /* Allow thread to exit */
            System.out.println("interrupted...");
            System.out.println(isInterrupted()); // false
            interrupt();
            System.out.println(isInterrupted()); // true
        }
    }

    public void cancel() {

        System.out.println("cancel...");
        interrupt();
        System.out.println(this.getName()); // Thread-0
        System.out.println(isInterrupted()); // true
        System.out.println(currentThread().isInterrupted()); // false
        System.out.println(currentThread().getName()); // main
        System.out.println(Thread.currentThread().isInterrupted()); // false
        System.out.println(Thread.currentThread().getName()); // main
    }

    public static void main(String[] args) {
        BlockingQueue<BigInteger> queue = new ArrayBlockingQueue<>(10);
        PrimeProducer producer = new PrimeProducer(queue);
        producer.start();
        while (queue.size() < 10) {
            System.out.println(queue);
            try {
                SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(queue);
        producer.cancel();
    }
}
