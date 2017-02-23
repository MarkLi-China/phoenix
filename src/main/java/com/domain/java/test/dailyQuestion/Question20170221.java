package com.domain.java.test.dailyQuestion;

/**
 * com.domain.java.test.dailyQuestion
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/2/21
 */
public class Question20170221 extends Parent001 {

    public static void main(String[] args) {

        final Parent001 p = new Parent001("aaa");
        // p = new Parent("bbb");
        p.str = "ccc";
        System.out.println(p.str);
    }
}

class Parent001 {

    String str;

    public Parent001() {

    }

    public Parent001(String s) {

        str = s;
    }
}
