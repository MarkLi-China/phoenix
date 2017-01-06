package com.domain.java.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 定时任务
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/1/6
 */
public class Example {

    private static final Logger logger = LoggerFactory.getLogger(Example.class);

    private boolean pause = false;

    private boolean stop = false;

    private Scheduler scheduler;

    private final byte[] locker = new byte[1];

    public void run() throws Exception {

        SchedulerFactory factory = new StdSchedulerFactory();
        scheduler = factory.getScheduler();

        JobDetail job = newJob(ResumeJob.class).withIdentity("resumeJob", "group1").build();
        // CronTrigger trigger = newTrigger().withIdentity("resumeTrigger", "group1").withSchedule(cronSchedule("0 30 9 * * ?")).build();
        SimpleTrigger trigger = (SimpleTrigger) newTrigger().withIdentity("resumeTrigger", "group1").startAt(futureDate(45, DateBuilder.IntervalUnit.SECOND)).build();
        job.getJobDataMap().put("example", this);
        Date ft = scheduler.scheduleJob(job, trigger);
        logger.info(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000 + " seconds");
        // logger.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());

        job = newJob(PauseJob.class).withIdentity("pauseJob", "group1").build();
        // trigger = newTrigger().withIdentity("pauseTrigger", "group1").withSchedule(cronSchedule("0 30 21 * * ?")).build();
        trigger = (SimpleTrigger) newTrigger().withIdentity("pauseTrigger", "group1").startAt(futureDate(15, DateBuilder.IntervalUnit.SECOND)).build();
        job.getJobDataMap().put("example", this);
        ft = scheduler.scheduleJob(job, trigger);
        logger.info(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000 + " seconds");
        // logger.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());

        scheduler.start();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                destroyExample();
            }
        });

        start();
    }

    /**
     * 重启job，内部必须是静态的或者外部public的
     */
    public static class ResumeJob implements Job {

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

            Example example = (Example) jobExecutionContext.getJobDetail().getJobDataMap().get("example");
            example.resume();
        }
    }

    /**
     * 暂停job，内部必须是静态的或者外部public的
     */
    public static class PauseJob implements Job {

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

            Example example = (Example) jobExecutionContext.getJobDetail().getJobDataMap().get("example");
            example.pause();
        }
    }

    public void start() {

        int i = 0;
        while(true) {

            synchronized (locker) {
                if (pause) {
                    try {
                        locker.wait();
                    } catch (InterruptedException e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
            synchronized (locker) {
                if (stop) {
                    break;
                }
            }
            System.out.println("current times is " + i++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    private void destroyExample() {

        stop();
        if (scheduler != null) {
            try {
                scheduler.shutdown(true);
            } catch (SchedulerException e) {
                logger.error(e.getMessage(), e);
            }
        }
        logger.info("destroy...");
    }

    private void stop() {

        synchronized (locker) {
            stop = true;
        }
        logger.info("stop example...");
    }

    private void pause() {

        synchronized (locker) {
            pause = true;
        }
        logger.info("pause example...");
    }

    private void resume() {

        synchronized (locker) {
            pause = false;
            locker.notify();
        }
        logger.info("resume example...");
    }
}
