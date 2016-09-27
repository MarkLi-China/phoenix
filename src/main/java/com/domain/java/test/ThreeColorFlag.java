package com.domain.java.test;

import java.util.Arrays;

/**
 * 问题：
 * 假设有一条绳子，上面有红、白、蓝三种颜色的旗子，起初绳子上的旗子颜色并没有顺序，
 * 您希望将之分类，并排列为蓝、白、红的顺序，要如何移动次数才会最少，注意您只能在绳
 * 子上进行这个动作，而且一次只能调换两个旗子。
 * <p/>
 * 网上的解法大多类似：
 * 在一条绳子上移动，在程式中也就意味只能使用一个阵列，而不使用其它的阵列来作辅助，
 * 问题的解法很简单，您可以自己想像一下在移动旗子，从绳子开头进行，遇到蓝色往前移，
 * 遇到白色留在中间，遇到红色往后移。
 * 只是要让移动次数最少的话，就要有些技巧：
 * 如果图中W所在的位置为白色，则W+1，表示未处理的部份移至至白色群组。
 * 如果W部份为蓝色，则B与W的元素对调，而B与W必须各+1，表示两个群组都多了一个元素。
 * 如果W所在的位置是红色，则将W与R交换，但R要减1，表示未处理的部份减1。
 * <p/>
 * 注意B、W、R并不是三色旗的个数，它们只是一个移动的指标；
 * 什么时候移动结束呢？一开始时未处理的R指标会是等于旗子的总数，
 * 当R的索引数减至少于W的索引数时，表示接下来的旗子就都是红色了，此时就可以结束移动
 * <p/>
 * 其实这个算法不复杂，自己拿一个实例来把算法走一遍就理解了
 * 核心就是维护b,w,r这三个指针（下标）：
 * w作为当前元素的下标，而b则表示下标在b之前的旗子颜色都是蓝色，r表示下标在r后面的都是红色，不符合这个条件的，就交换
 */
public class ThreeColorFlag {

    public static void main(String[] args) {

        char[] flags = "rbbwwbrbw".toCharArray();
        sort(flags);
        System.out.println(Arrays.toString(flags));

    }

    public static void sort(char[] flags) {

        if (flags == null || flags.length <= 1) {
            return;
        }
        int b = 0;
        int w = 0;
        int r = flags.length - 1;
        while (w <= r) {
            switch (flags[w]) {
                case 'w':
                    w++;
                    break;
                case 'b':
                    swap(flags, w, b);
                    w++;
                    b++; // w总是跟在b后面
                    break;
                case 'r':
                    swap(flags, w, r);
                    r--;
                    break;
                default:
                    throw new IllegalArgumentException("invalid input");
            }
        }
    }

    private static void swap(char[] array, int i, int j) {

        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
