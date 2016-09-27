package com.domain.java.test;

/**
 * Created with Intellij IDEA
 * @author QX
 * @version 1.0.0
 * @since 2015-8-19
 */
public class DemoClass {

    public static void main(String[] args) {

        DemoClass demoC = new DemoClass();
        demoC.test(1);
        demoC.test(2.1);
        demoC.test(1.0);

        // 基本类型和String型作为参数时，为传值方式，只把值传入方法，不管在方法中怎么处理这个参数，原值不变；
        String name = "TEST";
        System.out.println("Hello " + name);
        hello(name);
        System.out.println("Hello " + name);

        // 其他引用类型作为参数时，为传址方式，将指向内存中的地址传入方法，方法中此内存地址中的值发生变化时，原值也会改变；
        Demo1 demo1 = new Demo1("DEMO1");
        System.out.println("Hello " + demo1.getName()); // Hello DEMO1
        hello(demo1);
        System.out.println("Hello " + demo1.getName()); // Hello DEMO11

        // 如果引用类型的对象通过传址方式将其指向内存中的地址传入方法后，方法中使用new关键字重新给参数赋值时，会在内存中重新开辟空间，
        // 参数指向新的内存空间，此时参数和原对象指向的就不是同一个地址了，参数值的变化不会改变原值；
        Demo1 demo2 = change(demo1);
        System.out.println("Hello " + demo1.getName()); // Hello DEMO11
        System.out.println("Hello " + demo2.getName()); // Hello DEMO12

        demo1 = change(demo1);
        System.out.println("Hello " + demo1.getName()); // Hello DEMO12
    }

    private void test(Object obj) {

        System.out.println("obj = [" + obj + "]");
    }

    private void test(Integer i) {

        System.out.println("i = [" + i + "]");
    }

    private static void hello(String name) {

        name = "Demo";
        System.out.println("Hello " + name);
    }

    private static void hello(Demo1 demo1) {

        demo1.setName("DEMO11");
        System.out.println("Hello " + demo1.getName());
    }

    private static Demo1 change(Demo1 demo1) {

        demo1 = new Demo1("DEMO12");
        return demo1;
    }
}

class Demo1 {

    private String name;

    Demo1(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }
}