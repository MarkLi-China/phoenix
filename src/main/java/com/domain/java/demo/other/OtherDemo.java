package com.domain.java.demo.other;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

/**
 * Created with Intellij IDEA
 *
 * @author MarkLee
 * @since 2014/11/16
 */
public class OtherDemo {

    private static final String CLASS_KEY = "";

    public static void main(String[] args) {

        testCalendar();

    }

    public static void getProp() {

        Properties prop = System.getProperties();

        Set<String> keySet = prop.stringPropertyNames();

        for (String key: keySet) {
            String value = prop.getProperty(key);

            System.out.println(key + ":" + value);
        }
    }

    public static void getRun() {

        Runtime r = Runtime.getRuntime();
    }

    public static void dateFormat() {

        Date date = new Date();

        // 固定风格
        DateFormat dateFormat = DateFormat.getDateInstance();
        DateFormat dateTimeFormat = DateFormat.getDateTimeInstance();

        String dateStr = dateFormat.format(date);
        String dateTimeStr = dateTimeFormat.format(date);

        System.out.println("dateStr = " + dateStr);
        System.out.println("dateTimeStr = " + dateTimeStr);

        //
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static void testCalendar() {

        Calendar cal = Calendar.getInstance();
//        System.out.println(cal);

//        cal.set(2014, Calendar.FEBRUARY, 31);

        cal.add(Calendar.YEAR, 2);

        showDate(cal);
    }

    private static void showDate(Calendar cal) {

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int week = cal.get(Calendar.DAY_OF_WEEK);

        System.out.println(year + "-" + month + "-" + day);
    }
}
