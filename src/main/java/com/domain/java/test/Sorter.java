package com.domain.java.test;

import com.domain.common.utils.JsonHelper2;

/**
 * com.domain.java.test
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/11/1
 */
public class Sorter {

    public static <T extends Comparable<? super T>> void quickSort(T[] list) {
        quickSort(list, 0, list.length - 1);
    }

    private static <T extends Comparable<? super T>> void quickSort(T[] list, int low, int high) {

        if (low >= high) return;

        /*T pivot = list[low];
        int i = low, j = high;
        while (true) {
            while (list[j].compareTo(pivot) >= 0 && i < j) {j--;}
            while (list[i].compareTo(pivot) <= 0 && i < j) {i++;}
            if (i >= j) break;
            T tmp = list[i]; list[i] = list[j]; list[j] = tmp;
        }
        list[low] = list[i]; list[i] = pivot;
        quickSort(list, low, i - 1);
        quickSort(list, j + 1, high);*/

        int i = low - 1, j = high + 1;
        T pivot = list[low];

        while (true) {
            do {++i;} while (list[i].compareTo(pivot) < 0);
            do {--j;} while (list[j].compareTo(pivot) > 0);
            if (i >= j) break;
            T tmp = list[i]; list[i] = list[j]; list[j] = tmp;
            System.out.println(JsonHelper2.toJson(list));
        }
        quickSort(list, low, i);
        quickSort(list, j + 1, high);
    }

    public static void main(String[] args) {

        Integer[] list = new Integer[] {10,6,1,2,7,9,3,4,5,10,8};
        System.out.println("begin");
        quickSort(list);
        System.out.println("end");
        System.out.println(JsonHelper2.toJson(list));
    }
}
