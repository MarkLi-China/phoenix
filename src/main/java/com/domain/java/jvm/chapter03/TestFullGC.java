package com.domain.java.jvm.chapter03;

/**
 * -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+HandlePromotionFailure
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/9/18
 */
public class TestFullGC {

    /*
    Error: Could not create the Java Virtual Machine.
    Error: A fatal exception has occurred. Program will exit.
    Unrecognized VM option 'HandlePromotionFailure'
    Did you mean '(+/-)PromotionFailureALot'?
     */
    // 在jdk8的文档中没有找到上面两个选项【http://docs.oracle.com/javase/8/docs/technotes/tools/windows/java.html#CBBIJCHG】

    private static final int _1MB = 1024 * 1024;

    public static void testHandlePromotion() {

        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation1 = null;
        allocation4 = new byte[2 * _1MB];
        allocation5 = new byte[2 * _1MB];
        allocation6 = new byte[2 * _1MB];
        allocation4 = null;
        allocation5 = null;
        allocation6 = null;
        allocation7 = new byte[2 * _1MB];
    }

    public static void main(String[] args) {
        testHandlePromotion();
    }
}
