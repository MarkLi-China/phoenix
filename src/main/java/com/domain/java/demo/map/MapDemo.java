package com.domain.java.demo.map;

import java.util.*;

/**
 * Created with Intellij IDEA
 *
 * @author MarkLee
 * @since 2014/11/13
 */
public class MapDemo {

    public static void main(String[] args) {

        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "test1");
        map.put(2, "test2");
        map.put(3, "test3");
        map.put(4, "test4");
        map.put(5, "test5");

//        keyset(map);

//        entryset(map);

        countStringChar("afsjkdlsajeru");
    }

    public static void keyset(Map<Integer, String> map) {

        Set<Integer> keySet = map.keySet();
        Iterator<Integer> it = keySet.iterator();
        while(it.hasNext()) {
            System.out.println("value = [" + map.get(it.next()) + "]");
        }
    }

    public static void entryset(Map<Integer, String> map) {

        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();
        Iterator<Map.Entry<Integer, String>> it = entrySet.iterator();
        while(it.hasNext()) {
            Map.Entry<Integer, String> tmp = it.next();
            System.out.println(tmp.getKey() + " = " + tmp.getValue());
        }

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    public static void printCollection(Collection<? extends Object> col) {

        for (Iterator<?> it = col.iterator();it.hasNext();) {
            System.out.println("it.next() = " + it.next());
        }
    }

    public static void countStringChar(String str) {

//        char[] chr = str.toCharArray();
        Map<Character, Integer> map = new TreeMap<Character, Integer>();
        for (int i=0; i<str.length(); i++) {
            char s = str.charAt(i);
            if (map.get(s) == null) {
                map.put(s, 1);
            } else {
                map.put(s, map.get(s)+1);
            }
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "(" + entry.getValue() + ")");
        }
    }
}
