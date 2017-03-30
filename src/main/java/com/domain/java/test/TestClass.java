package com.domain.java.test;

import com.domain.common.utils.JsonHelper2;
import org.apache.commons.collections.CollectionUtils;
import sun.reflect.Reflection;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with Intellij IDEA
 * @author lijing
 * @version 1.0.0
 * @since 2014/10/29
 */
public class TestClass {

    public static void main(String[] args) throws Exception {

//        testInteger();

//        testGeneric();

//        testCalendar();

//        testStatic();

//        testReflection();

//        testReflection2();

//        testQuickSort();

//        testRegular();

//        newFeatures_1_7();

//        testArrayList();

//        testFileWriter();

//        testFileWriter2();

//        monteCarloMethod();

//        leibnizFormula();

//        testShiftOperator(-11);

//        testClassLoader();
    }

    private static void testClassLoader() {

        // Bootstrap ClassLoader
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url.toExternalForm());
        }

        // Extension ClassLoader
        System.out.println(System.getProperty("java.ext.dirs"));
        ClassLoader extensionClassLoader = ClassLoader.getSystemClassLoader().getParent();
        System.out.println("the parent of extension classLoader : " + extensionClassLoader.getParent());

        // System ClassLoader
        System.out.println(System.getProperty("java.class.path"));
    }

    private static void testShiftOperator(int x) {

//        int x = -11;

        assert (x > 0) : "x = " + x;

        String s1 = Integer.toBinaryString(x);
        System.out.println(s1 + " ===> " + x);

        int y = x >> 2;
        String s2 = Integer.toBinaryString(y);
        System.out.println(s2 + " ===> " + y);

        int y1 = x >>> 2;
        String s21 = Integer.toBinaryString(y1);
        System.out.println(s21 + " ===> " + y1);

        int z = x << 2;
        String s3 = Integer.toBinaryString(z);
        System.out.println(s3 + " ===> " + z);
    }

    /**
     * 蒙特卡洛方法计算圆周率
     */
    private static void monteCarloMethod() {

        final int COUNT = 10000;

        for (int z = 0; z < 10; z++) {

            double x, y, dis, pi;
            int cnt = 0;

            for (int i = 0; i < COUNT; i++) {
                x = Math.random();
                y = Math.random();
                dis = Math.pow(x, 2) + Math.pow(y, 2);
                if (dis < 1) cnt++;
            }

            pi = (double) cnt / COUNT * 4d;

            System.out.println("PI = " + pi);
        }
    }

    /**
     * 莱布尼兹公式计算圆周率
     */
    private static void leibnizFormula() {

        final int COUNT = 100000000;
        double quarter = 1.0, pi;
        int n, symbol;

        for (int k = 1; k <= COUNT; k++) {
            n = 2 * k + 1;
            symbol = k % 2 == 0 ? 1 : -1;
            quarter += 1d * symbol / n;
            if (k % 100000 == 0) {
                System.out.println("PI = " + quarter * 4);
            }
        }

        pi = quarter * 4;

        System.out.println("PI = " + pi);
    }

    private static void testFileWriter2() {

        try {
            File file = new File("E:\\first\\t_order_log.sql");
            FileWriter fileWriter = new FileWriter(file);
            int flag = 0;
            String sql = "";
            for (int i = 113600000; i <= 124000000; ) {
                System.out.println(i);
                if (flag > 0) {
                    sql += "union all\n";
                }
                sql += "select * from t_order_log_" + i + " where msg like \"%里程:From%\"\n";
                flag++;
                if (flag == 10 || i == 124000000) {
                    sql += "--------------------\n";
                    fileWriter.write(sql);
                    flag = 0;
                    sql = "";
                }
                i += 100000;
            }
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testFileWriter() {

        try {
            File file = new File("E:\\first\\t_customer_update_kd.sql");
            FileWriter fileWriter = new FileWriter(file);
            BufferedReader br = new BufferedReader(new FileReader("E:\\first\\cid_kd.txt"));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                // String sql = "update t_customer set country_id = 1 where cid >= " + i + " and cid < " + (i + 1000) + " and country_id = 0;\n";
                String sql = "update t_customer set country_id = 2 where cid = " + line + ";\n";
                fileWriter.write(sql);
            }
            /*for (int i = 106896460; i < 106910733; ) {
                System.out.println(i);
                // String sql = "update t_customer set country_id = 1 where cid >= " + i + " and cid < " + (i + 1000) + " and country_id = 0;\n";
                String sql = "update t_customer set country_id = 1 where cid = " + i + " and country_id = 0;\n";
                fileWriter.write(sql);
                i += 1;
            }*/
            fileWriter.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testArrayList() throws Exception {

        Class<?> clazz = ArrayList.class; // class java.util.ArrayList
//        Class<?> clazz = instance.getClass(); // class com.dhf.boss.first.TestClass$2

        List<String> list1 = new ArrayList<>(3);
        System.out.println(list1.size()); // size != elementData.length();
        List<String> list2 = new ArrayList<String>() {{
            add("test1");
            add("test2");
        }};
        System.out.println(list2.size());
        /*list1.addAll(2, list2);
        System.out.println(list1.size());*/

        // 获取elementData
        Object result = getPrivateValue(clazz, list1, "elementData");
        System.out.println(JsonHelper2.toJson(result)); // [null,null,null]

        List<String> list3 = new ArrayList<String>() {{
            add("test1");
            add(null);
            add("test3");
            add(null);
        }};
        System.out.println(list3.size());
        System.out.println(list3.get(3));

        // 获取elementData
        Object result1 = getPrivateValue(clazz, list3, "elementData");
        System.out.println(JsonHelper2.toJson(result1)); // ["test1",null,"test3",null,null,null,null,null,null,null]

        list3.remove(1);
        System.out.println(list3.size());
//        System.out.println(list3.get(3));

        System.out.println(list3);
        // 获取elementData
        Object result2 = getPrivateValue(clazz, list3, "elementData");
        System.out.println(JsonHelper2.toJson(result2)); // ["test1","test3",null,null,null,null,null,null,null,null]

    }

    /**
     * 获取对象私有变量
     * @param clazz
     * @param instance
     * @param privateVariableName
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static Object getPrivateValue(Class<?> clazz, Object instance, String privateVariableName) throws NoSuchFieldException, IllegalAccessException {

        System.out.println(clazz);
        Field nameField = clazz.getDeclaredField(privateVariableName);
        System.out.println(nameField);
        nameField.setAccessible(true); // private

        return nameField.get(instance);
    }

    private static void testInteger() {

        // 装箱时翻译成 Integer.valueOf(i);
        /*public static Integer valueOf(int i) {
            assert IntegerCache.high >= 127;
            if (i >= IntegerCache.low && i <= IntegerCache.high)
                return IntegerCache.cache[i + (-IntegerCache.low)];
            return new Integer(i);
        }*/
        Integer i = 127; // Integer i = Integer.valueOf(127);
        Integer j = 128;
        Integer.valueOf(127);

        /*String s = String.format("%,d", 100000000);
        System.out.println(s);*/
    }

    /**
     * JDK1.7新特性
     */
    private static void newFeatures_1_7() {

        // 数字变量对下滑线的支持
        int a = 222_222;
        float b = 333_333f;
        long c = 444_444l;

        // 捕获多种异常并用改进后的类型检查来重新抛出异常
        try {
            BufferedReader reader = new BufferedReader(new FileReader(""));
            Connection con = null;
            Statement stmt = con.createStatement();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 邮箱校验
     */
    private static void testRegular() {

        String regular = "(\\s*)|([0-9,a-z,A-Z,_,.,-]+@[0-9,a-z,A-Z,-]+(.\\w+){1,2})";
        String email1 = "46456@asfds.com";
        String email2 = "46456@sd.com.cn";
        System.out.println(email1.matches(regular));
        System.out.println(email2.matches(regular));
    }

    /**
     * List<Map<String, Object>>提取Map
     * @param sourceList
     * @param keyStr
     * @param valueStr
     * @param <K>
     * @param <V>
     * @return
     */
    private static <K, V> Map<K, V> resetList(List<Map<String, Object>> sourceList, String keyStr, String valueStr) {

        Map<K, V> result = new HashMap<>();

        if (!CollectionUtils.isEmpty(sourceList)) {
            for (Map<String, Object> map : sourceList) {
                if (map.get(keyStr) != null && map.get(valueStr) != null) {
                    K key = (K) map.get(keyStr);
                    V value = (V) map.get(valueStr);
                    result.put(key, value);
                }
            }
        }

        return result;
    }

    private static void testQuickSort() {

        int[] array = {9, 3, 5, 2, 7, 4, 5, 2, 7, 4, 1, 8, 9, 0};
//        quickSort(array, 0, array.length-1);
        QuickSort(array, 0, array.length - 1);
        System.out.println("array = [" + Arrays.toString(array) + "]");
    }

    /**
     * 测试反射
     * @throws Exception
     */
    private static void testReflection() throws Exception {

        Demo demo = new Demo();
        Class<?> clazz = Demo.class;

        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true); // private
        nameField.set(demo, "first");

        Field ageField = clazz.getField("age"); // public
        ageField.set(demo, 10);

        System.out.println("name:" + demo.getName());
        System.out.println("age:" + demo.getAge());
    }

    private static void testReflection2() throws Exception {

        System.out.println(Reflection.getCallerClass().getClassLoader());
        // DefaultController imports package 'javax.servlet' which is loaded by WebappClassLoader of Tomcat.
        // But this class's ClassLoader is AppClassLoader
        Class<?> clazz = Class.forName("com.domain.java.test.Demo");
//        Class<?> clazz = Class.forName("com.domain.java.controller.DefaultController");
        Object o = clazz.newInstance();
        Class[] parameterTypes = new Class[]{Map.class, int.class};
        Method invokeMethod = clazz.getMethod("getDemoInfo", parameterTypes);
        Map<String, Object> params = new HashMap<>();
        params.put("name", "test");
        params.put("age", 1);
        Object[] args = new Object[]{params, 1};
        invokeMethod.invoke(o, args);
    }

    /**
     * 测试静态变量初始化顺序
     */
    private static void testStatic() {

        Dd dd = Dd.getInstance();
        System.out.println("a:" + Dd.a + "; b:" + Dd.b);
    }

    private static void testCalendar() throws Exception {

        String startDate = "2014-01-12";
        String endDate = "2015-03-03";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal1.setTime(sdf.parse(startDate));
        cal2.setTime(sdf.parse(endDate));

        int monthDiffer = (cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH)) + (cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR)) * 12;

        System.out.println("monthDiffer = " + monthDiffer);

        cal1.set(Calendar.YEAR, cal2.get(Calendar.YEAR));
        cal1.set(Calendar.MONTH, cal2.get(Calendar.MONTH));

        int dayDiffer = cal2.get(Calendar.DAY_OF_MONTH) - cal1.get(Calendar.DAY_OF_MONTH);

        System.out.println("dayDiffer = " + dayDiffer);

        float period = monthDiffer + (float) dayDiffer / cal1.getActualMaximum(Calendar.DATE);

        System.out.println((float) (dayDiffer / cal1.getActualMaximum(Calendar.DATE)));

        DecimalFormat df = new DecimalFormat("#.0");

        System.out.println("period = " + df.format(period));
    }

    private static void testGeneric() {

        String[] sa = new String[100];
        Collection<String> cs = new Vector<String>();
        Collection<Object> co = new Vector<Object>();

        Cc.fun(sa, cs);    // fun中的泛型T此时匹配类型String
        System.out.println(cs); // [Ljava.lang.String;@33e92e10
        Cc.fun(sa, co);    // fun中的泛型T此时匹配类型Object
        System.out.println(co); // [Ljava.lang.String;@33e92e10
    }

    public static void func1(double num) {

        BigDecimal dec = new BigDecimal(num);
        double result = dec.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println("result = " + result);
    }

    public static void func2(double num) {

        DecimalFormat df = new DecimalFormat("0.##");
        String result = df.format(num);
        System.out.println("result = " + result);
    }

    public static void func3(double num) {

        System.out.println(String.format("%.2f", num));
    }

    public static void func4(double num) {

        NumberFormat nf = NumberFormat.getCurrencyInstance();
        nf.setMaximumFractionDigits(2);
        String result = nf.format(num);
        System.out.println("result = " + result);
    }

    /**
     * 快速排序算法
     * @param array
     * @param left
     * @param right
     */
    public static void quickSort(int[] array, int left, int right) {

        if (left < right) {
            int key = array[left];
            int i = left;
            int j = right;
            while (i < j) {
                // 从右往左，小于等于key的数移到左边
                while (i < j && array[j] > key)
                    j--;
                if (i < j)
                    array[i++] = array[j];
                // 从左往右，大于key的数移到右边
                while (i < j && array[i] <= key)
                    i++;
                if (i < j)
                    array[j--] = array[i];
            }
            // 循环完毕，i=j
            array[i] = key;

            quickSort(array, left, i - 1);
            quickSort(array, j + 1, right);
        }
    }

    /**
     * 快速排序算法
     * @param array
     * @param left
     * @param right
     */
    public static void QuickSort(int[] array, int left, int right) {
        // 左边索引小于右边，则还未排序完成
        if (left < right) {
            // 取中间的元素作为比较基准，小于他的往左边移，大于他的往右边移
            int middle = array[(left + right) / 2];
            int i = left - 1;
            int j = right + 1;
            while (true) {
                // 移动下标，左边的往右移动，右边的向左移动
                while (array[++i] < middle && i < right) ;
                while (array[--j] > middle && j > 0) ;
                if (i >= j)
                    break;
                // 交换位置
                int number = array[i];
                array[i] = array[j];
                array[j] = number;
            }
            QuickSort(array, left, i - 1);
            QuickSort(array, j + 1, right);
        }
    }
}

class Cc {

    public static <T> void fun(T[] a, Collection<T> c) {

        for (T o : a) {
            c.add(o);
        }
    }
}

class Dd {

    public static int a;

    public static int b = 0; // a:1; b:1

    private static Dd dd = new Dd();
//    public static int a;
//    public static int b = 0; // a:1; b:0

    private Dd() {

        a++;
        b++;
    }

    public static Dd getInstance() {

        return dd;
    }
}

class Demo {

    private String name;

    public int age;

    public String getName() {

        return this.name;
    }

    public int getAge() {

        return age;
    }

    public void getDemoInfo(Map<String, Object> params, int flag) {

        switch (flag) {
            case 1:
                System.out.println("params = " + params.get("name"));
                break;
            case 2:
                System.out.println("params = " + params.get("age"));
                break;
            default:
                break;
        }
    }
}
