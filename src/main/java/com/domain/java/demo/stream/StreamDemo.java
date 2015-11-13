package com.domain.java.demo.stream;

import com.domain.java.bean.Person;
import com.domain.java.util.NameFilter;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created with Intellij IDEA
 *
 * @author MarkLee
 * @since 2014/11/23
 */
public class StreamDemo {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static void main(String[] args) throws Exception {

//        testWriter();

//        testReader();

//        testBufferedWriter();

//        testBufferedReader();

//        testOutputStream();

//        testInputStream();

//        readKeyboard();

//        testPrintStream();

//        testPrintWriter();

//        testSequenceInputStream();

        /*File file = new File("JDK6API中文参考.chm");
        splitFile(file);*/

        /*File dir = new File("E:\\workpro\\first\\parts");
        mergeFile(dir);*/

//        testObjectOutputStream();

        testObjectInputStream();
    }

    private static void testObjectInputStream() throws Exception {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("obj.object"));

        Person person = (Person) ois.readObject();

        System.out.println("name:" + person.getName() + ", age:" + person.getAge());
    }

    private static void testObjectOutputStream() throws IOException {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("obj.object"));
        oos.writeObject(new Person("小强", 30));
        oos.close();
    }

    /**
     * 合并文件夹里的文件
     * @param dir
     */
    private static void mergeFile(File dir) throws IOException {

        /**
         * 读取配置文件
         * 思路：
         * 1、拿到配置文件（是否存在，是否唯一）
         * 2、输入流读取配置文件放到properties对象中
         * 3、读取配置
         */
        // 判断配置文件是否正确存在
        File[] props = dir.listFiles(new NameFilter(".properties"));
        int propsLength = props.length;
        if (propsLength < 1) {
            throw new RuntimeException("配置文件不存在！");
        } else if (propsLength > 1) {
            throw new RuntimeException("存在多个配置文件！");
        }
        // 存入properties对象
        File config = props[0];
        Properties prop = new Properties();
        prop.load(new FileInputStream(config));
        // 读取配置
        String fileName = prop.getProperty("file.name");
        int count = Integer.parseInt(prop.getProperty("file.part.count"));

        ArrayList<FileInputStream> list = new ArrayList<FileInputStream>();
        // 遍历文件夹里的文件
        File[] files = dir.listFiles(new NameFilter(".part"));
        if (files.length != (count)) {
            throw new RuntimeException("实际子文件与配置中的数目不一致！");
        }
        for (File file: files) {
            list.add(new FileInputStream(file));
        }

        SequenceInputStream sis = new SequenceInputStream(Collections.enumeration(list));

        FileOutputStream fos = new FileOutputStream(new File(dir, fileName));

        byte[] buf = new byte[1024];

        int length;

        while ((length = sis.read(buf)) != -1) {
            fos.write(buf, 0, length);
        }

        fos.close();
        sis.close();
    }

    /**
     * 文件切割 按大小
     */
    private static void splitFile(File file) throws IOException {

        // 用读取流关联源文件
        FileInputStream fis = new FileInputStream(file);

        // 创建目的
        FileOutputStream fos = null;

        // 源文件同级目录下创建parts文件夹
        File pf = file.getParentFile();
        File dir = new File(pf, "parts");
        if (!dir.exists()) {
            dir.mkdir();
        }

        // 定义缓冲区
        byte[] buf = new byte[1024*1024];

        int length;

        int count = 0;

        while ((length = fis.read(buf)) != -1) {
            fos = new FileOutputStream(new File(dir, count++ + ".part"));
            fos.write(buf, 0, length);
            fos.close();
        }

        // 写入.properties文件
        // properties对象
        Properties prop = new Properties();
        prop.setProperty("file.name", file.getName());
        prop.setProperty("file.part.count", String.valueOf(count));
        // 输出流
        fos = new FileOutputStream(new File(dir, count + ".properties"));
        prop.store(fos, "split properties");
        fos.close();

        fis.close();
    }

    private static void testSequenceInputStream() throws IOException {

        /*Vector<FileInputStream> vec = new Vector<FileInputStream>();
        vec.add(new FileInputStream("buf.txt"));
        vec.add(new FileInputStream("byte_demo.txt"));
        vec.add(new FileInputStream("demo.txt"));
        Enumeration<FileInputStream> en = vec.elements();*/

        ArrayList<FileInputStream> list = new ArrayList<FileInputStream>();
        list.add(new FileInputStream("demo.txt"));
        list.add(new FileInputStream("byte_demo.txt"));
        list.add(new FileInputStream("buf.txt"));

        /*final Iterator<FileInputStream> it = list.iterator();

        Enumeration<InputStream> en = new Enumeration<InputStream>() {
            @Override
            public boolean hasMoreElements() {

                return it.hasNext();
            }

            @Override
            public InputStream nextElement() {

                return it.next();
            }
        };*/

        Enumeration<FileInputStream> en = Collections.enumeration(list);

        SequenceInputStream sis = new SequenceInputStream(en);

        FileOutputStream fos = new FileOutputStream("merge.txt");

        byte[] buf = new byte[1024];

        int length;

        while ((length = sis.read(buf)) != -1) {
            fos.write(buf, 0, length);
        }

        fos.close();
        sis.close();
    }

    private static void testPrintWriter() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PrintWriter pw = new PrintWriter(System.out, true);

        String line;
        while ((line = br.readLine()) != null) {
            if ("over".equals(line)) {
                break;
            }
            pw.println(line.toUpperCase());
//            pw.flush();
        }

        pw.close();
        br.close();
    }

    private static void testPrintStream() throws FileNotFoundException {

        PrintStream out = new PrintStream(new File("out.txt"));

        out.write(97); // 将指定的字节写入此输出流。write 的常规协定是：向输出流写入一个字节。要写入的字节是参数 b 的八个低位。b 的 24 个高位将被忽略。

//        out.write(610);

        out.print(97); // 先将97转为字符串，再写入文件中

        out.close();
    }

    private static void testWriter() {

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("demo.txt");

            fileWriter.write("abcde" + LINE_SEPARATOR + "fghijk");

//        fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("close failed");
                }
            }
        }
    }

    private static void testReader() throws IOException {

        FileReader fileReader = new FileReader("demo.txt");

        /*int ch = 0;
        while((ch = fileReader.read()) != -1){
            System.out.println((char)ch);
        }*/

        char[] buf = new char[1024];

        int num = 0;
        while((num = fileReader.read(buf)) != -1) {
            System.out.println(num + ":" + new String(buf, 0, num));
        }

        fileReader.close();
    }

    public static void testBufferedWriter() throws IOException {

        FileWriter fileWriter = new FileWriter("buf.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (int i = 0; i < 4; i++) {
            bufferedWriter.write("abcdef" + i);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        bufferedWriter.close();
    }

    public static void testBufferedReader() throws IOException {

        FileReader fileReader = new FileReader("buf.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = null;
        while((line=bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        bufferedReader.close();
    }

    public static void testOutputStream() throws IOException {

        FileOutputStream fos = new FileOutputStream("byte_demo.txt");

        fos.write("abcdefg".getBytes());

        fos.close();
    }

    public static void testInputStream() throws IOException {

        FileInputStream fis = new FileInputStream("byte_demo.txt");

        /*int ch = 0;
        while((ch = fis.read()) != -1){
            System.out.println((char)ch);
        }*/

        byte[] buf = new byte[1024];

        int num = 0;
        while((num = fis.read(buf)) != -1) {
            System.out.println(num + ":" + new String(buf, 0, num));
        }
    }

    public static void readKeyboard() throws IOException {

        StringBuilder sb = new StringBuilder();

        InputStream in = System.in;

        int ch = 0;
        while((ch = in.read()) != -1){ // 阻塞式方法

            if (ch == '\r') {
                continue;
            }
            if (ch == '\n') {
                String temp = sb.toString();
                if ("over".equalsIgnoreCase(temp)) {
                    break;
                }
                System.out.println(temp.toUpperCase());
                sb.delete(0, temp.length());
            } else {
                sb.append((char) ch);
            }
//            System.out.println((char)ch);
        }
    }
}
