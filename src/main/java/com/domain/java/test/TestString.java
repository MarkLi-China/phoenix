package com.domain.java.test;

/**
 * com.domain.java.test
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/9/15
 */
public class TestString {

    public static void main(String[] args) {

        String str1 = "abc";
        String str2 = "a" + "bc";
        String str3 = new String("abc");
        String str4 = new String("abc");
        String a = "a";
        String bc = "bc";
        String str5 = a + bc;

        // str1, str2在字符串常量池
        // str3, str4, str5在堆中
        // intern()不复制实例，只是在常量池中记录并返回首次出现的实例引用
        System.out.println(str1 == str2); // true
        System.out.println(str1 == str3); // false
        System.out.println(str3 == str4); // false
        System.out.println(str3.intern() == str1); // true
        System.out.println(str1 == str5); // false
        System.out.println(str3 == str5); // false
    }
}
