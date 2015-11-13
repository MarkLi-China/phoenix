package com.domain.java.demo.string;

/**
 * Created with Intellij IDEA
 *
 * @author MarkLee
 * @since 2014/11/5
 */
public class StringDemo {

    public static void main(String[] args) {

        /*String s = "a,b,c,d";
        System.out.println(s.indexOf(97));

        String[] sa = s.split(",");
        for (int i=0; i<sa.length; i++) {
            System.out.println(sa[i]);
        }

        char[] cha = s.toCharArray();
        for (int i=0; i<cha.length; i++) {
            System.out.println(cha[i]);
        }

        byte[] bta = s.getBytes();
        for (int i=0; i<bta.length; i++) {
            System.out.println(bta[i]);
        }*/

        String s1 = "adsfjasdasdfs";
        String s2 = "sdfjslf";
        String maxSubstring = getMaxSubstring(s1, s2);
        System.out.println("maxSubstring = " + maxSubstring);
    }

    /**
     * 字符串str中，字符串key出现的次数
     * @param str
     * @param key
     * @return
     */
    public static int getKeyStringCount(String str, String key) {

        // 计数器
        int count = 0;
        // 记录key出现的位置
        int index = 0;

        /*while ((index = str.indexOf(key)) != -1) {
            str = str.substring(index+key.length());
            count++;
        }*/
        while ((index = str.indexOf(key, index)) != -1) {
            index += key.length();
            count++;
        }

        return count;
    }

    /**
     * 两个字符串中最大的相同字符串
     * @param s1
     * @param s2
     * @return
     */
    public static String getMaxSubstring(String s1, String s2)  {

        String min = s1.length() > s2.length() ? s2 : s1;
        String max = s1.length() > s2.length() ? s1 : s2;min.trim();

        for (int i = 0; i < min.length(); i++) { // 外循环 子串的长度
            for (int x = 0, y = min.length() - i; y != min.length() + 1; x++, y++) { // 内循环 分割min的索引位置
                String tmp = min.substring(x, y);
                if (max.contains(tmp)) {
                    return tmp;
                }
            }
        }

        return null;
    }
}
