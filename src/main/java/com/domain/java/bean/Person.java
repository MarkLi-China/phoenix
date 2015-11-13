package com.domain.java.bean;

import java.io.Serializable;

/**
 * Created with Intellij IDEA
 *
 * @author MarkLee
 * @since 2015/4/5
 */
public class Person implements Serializable {

    private transient String name;

    private static int age;

    public Person(String name, int age) {

        this.name = name;
        Person.age = age;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getAge() {

        return age;
    }

    public void setAge(int age) {

        Person.age = age;
    }
}
