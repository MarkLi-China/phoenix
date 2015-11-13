package com.domain.java.demo.stream;

import com.domain.java.util.NameFilter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * Created with Intellij IDEA
 *
 * @author MarkLee
 * @since 2015/1/18
 */
public class PropertiesDemo {

    public static void main(String[] args) throws IOException {

//        read();

//        list();

//        store();

//        load();

//        test();

        test2();
    }

    /**
     *
     */
    private static void test2() {

        File dir = new File("E:\\workpro");

        NameFilter nameFilter = new NameFilter(".java");

        List<File> list = new ArrayList<File>();

        getFiles(dir, nameFilter, list);

        File destFile = new File("E:\\workpro\\listJava.txt");

        write2File(list, destFile);
    }

    /**
     * 深度遍历文件夹
     * @param dir
     * @param nameFilter
     * @param list
     */
    private static void getFiles(File dir, NameFilter nameFilter, List<File> list) {

        File[] fileNames = dir.listFiles();

        if (fileNames != null) {
            for (File name: fileNames) {
                if (name.isDirectory()) {
                    getFiles(name, nameFilter, list);
                } else {
                    if (nameFilter.accept(name, name.getAbsolutePath())) {
                        list.add(name);
                    }
                }
            }
        }
    }

    /**
     * 将路径写入目标文件中
     * @param list
     * @param destFile
     */
    private static void write2File(List<File> list, File destFile) {

        BufferedWriter bufWriter = null;
        try {
            bufWriter = new BufferedWriter(new FileWriter(destFile));
            for (File file: list) {
                bufWriter.write(file.getAbsolutePath());
                bufWriter.newLine();
                bufWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufWriter != null) {
                try {
                    bufWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("关闭失败");
                }
            }
        }
    }

    /**
     * 获取一个应用程序运行的次数，如果次数超过5次，给出“使用次数已到，请注册“的提示，并停止运行程序；
     * 思路；
     * 1、应该有计数器，每次程序启动都需要计数一次，在原有的次数上增加；
     * 2、计数器就是一个变量；
     * 3、如何使用计数器；
     * 4、文件中的信息如何存储并体现；
     */
    private static void test() throws IOException {

        File confFile = new File("config.properties");

        // create new file if not exists
        if (!confFile.exists()) {
            confFile.createNewFile();
        }

        FileInputStream fis = new FileInputStream(confFile);

        Properties prop = new Properties();
        prop.load(fis);

        String times = prop.getProperty("times");

        int count = 0;
        if (times != null) {
            count = Integer.parseInt(times);
            if (count >= 5) {
                throw new RuntimeException("使用次数已到，请注册");
            }
        }
        count++;

        prop.setProperty("times", String.valueOf(count));

        FileOutputStream fos = new FileOutputStream(confFile);

        prop.store(fos, "");

        fos.close();
        fis.close();
    }

    private static void load() throws IOException {

        Properties prop = new Properties();

        FileInputStream fis = new FileInputStream("info.txt");
        prop.load(fis);
        prop.list(System.out);
    }

    private static void store() throws IOException {

        Properties prop = new Properties();

        prop.setProperty("张三", "12");
        prop.setProperty("李四", "23");
        prop.setProperty("王五", "34");
        prop.setProperty("赵六", "45");

        FileOutputStream fos = new FileOutputStream("info.txt");
        prop.store(fos, "name:age");
    }

    private static void list() {

        Properties prop = new Properties();

        /*prop.setProperty("张三", "12");
        prop.setProperty("李四", "23");
        prop.setProperty("王五", "34");
        prop.setProperty("赵六", "45");*/

        prop = System.getProperties();

        prop.list(System.out);
    }

    private static void read() {

        Properties prop = new Properties();

        prop.setProperty("张三", "12");
        prop.setProperty("李四", "23");
        prop.setProperty("王五", "34");
        prop.setProperty("赵六", "45");

        Set<String> names = prop.stringPropertyNames();

        for (String name: names) {
            String value = prop.getProperty(name);
            System.out.println(name + ":" + value);
        }
    }
}
