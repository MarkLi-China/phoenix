package com.domain.java.jvm.chapter02;

/**
 * VM Args: -Xss128k
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/9/14
 */
public class JVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        // 压栈
        stackLeak();
    }

    public static void main(String[] args) {

        JVMStackSOF oom = new JVMStackSOF();

        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length: " + oom.stackLength);
            throw e;
        }
    }
}
