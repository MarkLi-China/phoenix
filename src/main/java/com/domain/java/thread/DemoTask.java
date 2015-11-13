package com.domain.java.thread;

/**
 * Demo Task
 * @author Mark Li
 * @version 1.0.0
 * @since 2015-11-9
 */
public class DemoTask implements Runnable {

    private int taskNum;

    public DemoTask(int taskNum) {

        this.taskNum = taskNum;
    }

    @Override
    public void run() {

        System.out.println("正在执行task：" + taskNum);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task " + taskNum + " 执行完毕...");
    }
}