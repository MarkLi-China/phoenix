package com.domain.java.demo.collection;


import java.util.*;

/**
 * Created with Intellij IDEA
 *
 * @author MarkLee
 * @since 2014/11/9
 */
public class CollectionDemo {

    public static void main(String[] args) {

//        Collection col =  new ArrayList();
//        col.add("abc1");
//        col.add("abc2");
//        col.add("abc3");
//        col.isEmpty();
//        Collection col1 =  new ArrayList();
//        col1.add("abc3");
//        col1.add("abc4");
//        col1.add("abc5");
//        col.retainAll(col1);
//
//        System.out.println("col"+col);
//        System.out.println("col1"+col1);

        /*Iterator it = col.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }*/

        /*// 节省内存空间
        for (Iterator it = col.iterator(); it.hasNext();) {
            System.out.println(it.next());
        }*/

        LinkedList<String> list = new LinkedList<String>();
        list.add("aaa");
        Collections.sort(list);
        Collections.replaceAll(list, "aaa", "bbb");
        Collections.synchronizedList(list);
        list.toArray();
//        list.peek();

        /*HashSet hs = new HashSet();
        hs.add("haha");
        hs.add("xixi");
        hs.add("hehe");
        hs.add("heihei");

        Iterator it = hs.iterator();
        while(it.hasNext()) {
            System.out.println("it.next() = " + it.next());
        }*/

        TreeSet<String> ts = new TreeSet<String>();
        ts.add("abc");
        ts.add("bbc");
        ts.add("cba");
        ts.add("nba");

        Iterator it = ts.iterator();
        while(it.hasNext()) {
            System.out.println("it.next() = " + it.next());
        }
    }

    public static <T extends Comparator<? super T>> void sort(List<T> list) {

    }

    public static <T> void sort(List<T> list, Comparator<? super T> comparator) {

    }
}
