package com.domain.java.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理中心
 * @author Mark Li
 * @version 1.0.0
 * @since 2015-11-9
 */
public class TaskManager {

    /**
     * 线程池
     */
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(10), new TaskRejectedExecutionHandler());

    /**
     * 拒绝任务的处理策略
     */
    private static class TaskRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            r.run();
        }
    }

    /**
     * 添加线程任务到线程池
     * @param task
     */
    public static void addTask(Runnable task) {

        threadPoolExecutor.execute(task);
    }

    public static void main(String[] args) {

        for (int i = 0; i < 50; i++) {
            DemoTask task = new DemoTask(i);
            threadPoolExecutor.execute(task);
            System.out.println("i = " + i + "，线程池中线程数目：" + threadPoolExecutor.getPoolSize() + "，队列中等待执行的线程数目："
                    + threadPoolExecutor.getQueue().size() + "，已执行完线程数目：" + threadPoolExecutor.getCompletedTaskCount());
        }
        threadPoolExecutor.shutdown();
    }
}
