package com.domain.java.test;

import java.util.Arrays;
import java.util.Random;

/**
 * CPU的分支预测模型
 * @author Mark Li
 * @version 1.0.0
 * @since 2016/9/14
 */
public class BranchPrediction {

    public static void main(String[] args) {
        // Generate data
        int arraySize = 32768;
        int data[] = new int[arraySize];

        Random rnd = new Random(0);
        for (int c = 0; c < arraySize; ++c)
            data[c] = rnd.nextInt() % 256;

        // !!! With this, the next loop runs faster
        Arrays.sort(data);

        // Test
        long start = System.nanoTime();
        long sum = 0;

        for (int i = 0; i < 100000; ++i) {
            // Primary loop
            for (int c = 0; c < arraySize; ++c) {
                /*if (data[c] >= 128)
                    sum += data[c];*/
                /**
                 * |x| >> 31 = 0 # 非负数右移31为一定为0
                 * ~(|x| >> 31) = -1 # 0取反为-1
                 *
                 * -|x| >> 31 = -1 # 负数右移31为一定为0xffff = -1
                 * ~(-|x| >> 31) = 0 # -1取反为0
                 *
                 * -1 = 0xffff
                 * -1 & x = x # 以-1为mask和任何数求与，值不变
                 */
                int t = (data[c] - 128) >> 31;
                sum += ~t & data[c];
            }
        }

        System.out.println((System.nanoTime() - start) / 1000000000.0);
        System.out.println("sum = " + sum);
    }
}
