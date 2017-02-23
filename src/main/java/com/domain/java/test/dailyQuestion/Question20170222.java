package com.domain.java.test.dailyQuestion;

import java.util.Arrays;

/**
 * com.domain.java.test.dailyQuestion
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/2/22
 */
public class Question20170222 {

    public static void main(String[] args) {

        Dog[] dogs01 = new Dog[2];
        dogs01[0] = new Dog("01", "黑色");
        dogs01[1] = new Dog("02", "白色");
        Dog[] dogs02 = Arrays.copyOf(dogs01, dogs01.length);

        // 修改dogs02中第二只狗颜色
        dogs02[1].setColor("棕色");

        // 打印dogs01中狗的颜色
        for (int i = 0; i < dogs01.length; i++) {
            System.out.println(dogs01[i]); // 编号：01, 颜色：黑色\r\n编号：02, 颜色：棕色
        }
    }
}

class Dog {

    private String id;

    private String color;

    public Dog(String id, String color) {

        super();
        this.id = id;
        this.color = color;
    }

    /* 省略get set方法 */

    @Override
    public String toString() {

        return "编号：" + id + ", 颜色：" + color;
    }

    public void setColor(String color) {

        this.color = color;
    }
}
