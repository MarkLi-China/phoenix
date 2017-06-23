package com.domain.java.test;

/**
 * 静态方法可以被重写，但是多态时具体的调用由实际调用的类决定
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/6/12
 */
public class TestStaticFunc {

    public static void main(String[] args) {

        M m = new N();
        N n = new N();
        // case 1: N override function test()
        m.test(); // N
        n.test(); // N
        // case 2: N doesn't override function test()
        m.test(); // M
        n.test(); // M
        // case 3: N doesn't have static function output()
        m.test(); // M
        n.test(); // M

        // 实际上是类M调用
        m.output(); // M
        // 实际上是类N调用
        n.output(); // N

        M.output(); // M
        N.output(); // N
    }
}

class M {

    public void test() {
        output();
    }

    public static void output() {

        System.out.println("M");
    }
}

class N extends M {

    public void test() {
        output();
    }

    public static void output() {

        System.out.println("N");
    }
}
