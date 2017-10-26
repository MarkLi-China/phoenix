package com.domain.java.test;

import java.util.Date;

/**
 * @author lijing
 * @version 1.0.0
 * @since 2017/4/4
 */
public class Param {

    public static void main(String[] args) {

        Date d1 = new Date("1 Apr 98");
        nextDateUpdate(d1);
        System.out.println("d1 = " + d1); // arg = [Thu Apr 02 00:00:00 CST 1998]

        Date d2 = new Date("1 Apr 98");
        nextDateReplace(d2);
        System.out.println("d2 = " + d2); // d2 = Wed Apr 01 00:00:00 CST 1998
    }

    private static void nextDateReplace(Date arg) {

        arg = new Date(arg.getYear(), arg.getMonth(), arg.getDate() + 1);
        System.out.println("arg = [" + arg + "]"); // arg = [Thu Apr 02 00:00:00 CST 1998]
    }

    private static void nextDateUpdate(Date arg) {

        arg.setDate(arg.getDate() + 1);
        System.out.println("arg = [" + arg + "]"); // arg = [Thu Apr 02 00:00:00 CST 1998]
    }
}
