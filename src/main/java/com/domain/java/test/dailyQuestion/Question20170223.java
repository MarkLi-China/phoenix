package com.domain.java.test.dailyQuestion;

/**
 * com.domain.java.test.dailyQuestion
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/2/23
 */
public class Question20170223 {

    public static void main(String[] args) {

        Parent002 parent = new Sub();
        parent.sum(1, 2);

        /*Sub sub = new Sub(); // 编译报错
        sub.sum(1, 2);*/
    }
}

class Parent002 {

    public void sum(int num1, int... nums) {

        System.out.println("Parent... sum");
    }
}

class Sub extends Parent002 {

    @Override
    public void sum(int num1, int[] nums) {

        System.out.println("Sub... sum");
    }
}
