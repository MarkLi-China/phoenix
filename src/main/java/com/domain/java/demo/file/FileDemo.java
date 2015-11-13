package com.domain.java.demo.file;

import com.domain.java.util.NameFilter;

import java.io.File;
import java.io.IOException;

/**
 * Created with Intellij IDEA
 *
 * @author MarkLee
 * @since 2014/12/7
 */
public class FileDemo {

    public static void main(String[] args) throws IOException {

//        createAndDeleteFile();
//        listDemo();
//        listAll();
        deleteDir();
    }

    private static void deleteDir() {

        File dir = new File("E:\\temp");
        removeDir(dir);
    }

    private static void removeDir(File dir) {

        File[] files = dir.listFiles();

        for (File file: files) {
            if (file.isDirectory()) {
                removeDir(file);
            } else {
                file.delete();
            }
        }

        dir.delete();
    }

    private static void listAll() {

        File dir = new File("E:\\workpro\\first");

        listAll(dir, 0);
    }

    private static void listAll(File dir, int level) {

        System.out.println(getSpace(level) + dir.getName());

        level++;
        File[] files = dir.listFiles();

        /*for (File file: files) {

        }*/
        for (int x=0; x < files.length; x++) {
            if (files[x].isDirectory()) {
                listAll(files[x], level);
            } else {
                System.out.println(getSpace(level) + files[x].getName());
            }
        }
    }

    private static String getSpace(int level) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }

        return sb.toString();
    }

    private static void listDemo() {

        File dir = new File("E:\\workpro\\first");

        String[] names = dir.list(new NameFilter(".txt"));

        for (String name : names) {
            System.out.println("name = "+name);
        }
    }

    private static void createAndDeleteFile() throws IOException {

        File file = new File("file.txt");
        // 和输出流不一样,如果文件不存在,则创建;存在,则不创建;
//        boolean result = file.createNewFile();

        boolean result = file.delete();

        System.out.println("result = " + result);
    }
}
