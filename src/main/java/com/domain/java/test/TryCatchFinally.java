package com.domain.java.test;

/**
 * 1 try、catch、finally语句中，如果try语句有return语句，则返回的是当前try中变量此时对应的值，此后对变量做任何的修改，都不影响try中return的返回值<br>
 * 2 如果finally块中有return语句，则返回try或catch中的返回语句忽略。<br>
 * 3 如果finally块中抛出异常，则整个try、catch、finally块中抛出异常
 * com.domain.java.other
 * @author Mark Li
 * @version 1.0.0
 * @since 2016/8/8
 */
public class TryCatchFinally {

    @SuppressWarnings("finally")
    public static final String test() {

        String t = "";

        // finally是在return后面的表达式运算后执行的（此时并没有返回运算后的值，而是先把要返回的值保存起来，
        // 不管finally中的代码怎么样，返回的值都不会改变，仍然是之前保存的值），所以函数返回值是在finally执行前确定的；
        try {
            t = "try";
            return t;
        } catch (Exception e) {
            // result = "catch";
            t = "catch";
            return t;
        } finally {
            t = "finally";
            return t;
        }
    }

    public static void main(String[] args) {

        System.out.print(TryCatchFinally.test());
    }
}
