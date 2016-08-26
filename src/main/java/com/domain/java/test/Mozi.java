package com.domain.java.test;

/**
 * com.domain.java.other
 * @author Mark Li
 * @version 1.0.0
 * @since 2016/8/17
 */
public class Mozi {

    public void ride(Horse h) {

        System.out.println("骑马");
    }

    public void ride(WhiteHorse wh) {

        System.out.println("骑白马");
    }

    public void ride(BlackHorse bh) {

        System.out.println("骑黑马");
    }

    public static void main(String[] args) {

        Horse wh = new WhiteHorse();
        Horse bh = new BlackHorse();
        // 静态分配
        Mozi mozi = new Mozi();
        mozi.ride(wh); // 骑马
        mozi.ride(bh); // 骑马
        // 动态分配
        wh.eat(); // 白马吃草
        bh.eat(); // 黑马吃草
    }
}

class Horse {

    public void eat() {

        System.out.println("马吃草");
    }
}

class WhiteHorse extends Horse {

    public void eat() {

        System.out.println("白马吃草");
    }
}

class BlackHorse extends Horse {

    public void eat() {

        System.out.println("黑马吃草");
    }
}
