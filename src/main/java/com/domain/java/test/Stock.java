package com.domain.java.test;

/**
 * 已知一支股票的历史走势，求利润最高的区段
 * <p>动态规划：<br>
 * &nbsp;设F(d)为第d天的最大利润，Vd为第d天的卖出价格，则：<br>
 * &nbsp;F(d) = max{F(d-1), Vd - min{V1 ~ Vd}}
 * @author Mark Li
 * @version 1.0.0
 * @since 2016/8/25
 */
public class Stock {

    public static void main(String[] args) {

        int[] history = {3, 5, 6, 4, 10, 2, 4, 8, 9, 1, 2, 6, 8};
        int len = history.length;

        int min = 0;
        int index = 0;
        int[] profile_history = new int[len]; // 记录每天的最大利润
        int[][] record = new int[len][2]; // 记录最大利润对应的买入、卖出时机
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                min = history[0];
                profile_history[0] = 0;
                record[i][0] = 0;
                record[i][1] = 0;
                continue;
            }

            // 0 ~ i 的最小值
            if (history[i] < min) {
                min = history[i];
                index = i;
            }
            // 当前卖出的最大利润
            int profile_today = history[i] - min;
            // 前一天的最大利润
            int profile_yesterday = profile_history[i - 1];
            // 到当前为止的最大利润
            if (profile_today >= profile_yesterday) {
                profile_history[i] = profile_today;
                record[i][0] = index;
                record[i][1] = i;
            } else {
                profile_history[i] = profile_yesterday;
                record[i][0] = record[i-1][0];
                record[i][1] = record[i-1][1];
            }
        }
        int max = 0;
        for (int pro: profile_history) {
            if (pro > max) {
                max = pro;
            }
        }
        System.out.println("max = " + max);
        int start0 = -1;
        int end0 = -1;
        for (int j = 0; j < len; j++) {
            if (max == profile_history[j]) {
                int[] result = record[j];
                int start = result[0];
                int end = result[1];
                if (start0 != start && end0 != end) {
                    System.out.println("第" + (start + 1) + "天买入，价格为" + history[start] + ", 第" + (end + 1) + "天卖出，价格为" + history[end]);
                    start0 = start;
                    end0 = end;
                }
            }
        }
    }
}
