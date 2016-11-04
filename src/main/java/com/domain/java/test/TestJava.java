package com.domain.java.test;

/**
 * com.domain.java.test
 * @author Mark Li
 * @version 1.0.0
 * @since 2016/10/27
 */
public class TestJava {

    public static void main(String[] args) {

        System.out.println(5 % 3); // 2
        System.out.println(5 % -3); // 2
        System.out.println(-5 % 3); // -2
        System.out.println(-5 % -3); // -2

        System.out.println(5.2 % 3.1); // 2.1
        System.out.println(5.2 % -3.1); // 2.1
        System.out.println(-5.2 % 3.1); // -2.1
        System.out.println(-5.2 % -3.1); // -2.1

        /*
         * java处理成员变量和成员方法的继承时是有区别的
         * 当通过这些变量调用方法时，方法的行为总是表现出他们的实际类型的行为，
         * 但是如果通过这些变量来访问他们所指向对象的实例变量的时候，这些实例变量的值总是表现出声明这些变量所用类型的行为。
         */
        A a = new A();
        a.out();
        System.out.println(a.message);
        B b = new B();
        b.out();
        System.out.println(b.message);
        A ab = new B();
        ab.out();
        System.out.println(ab.message);
    }
}

class A {

    String message = "AAA";

    void out() {

        System.out.println(this.getClass());
        System.out.println(message);
    }
}

class B extends A {

    String message = "BBB";
}
