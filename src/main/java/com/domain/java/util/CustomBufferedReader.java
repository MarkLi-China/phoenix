package com.domain.java.util;

import java.io.IOException;
import java.io.Reader;

/**
 * Created with Intellij IDEA
 * 自定义BufferedReader，实现缓冲区读功能
 * @author MarkLee
 * @since 2014/11/23
 */
public class CustomBufferedReader extends Reader {

    private Reader fileReader;

    // 数组缓冲区
    private char buffer[] = new char[1024];

    // 指针
    private int position = 0;

    // 计数器
    private int count = 0;

    CustomBufferedReader(Reader fileReader) {

        this.fileReader = fileReader;
    }

    /**
     * 从缓冲区一次读取一个元素
     * @return 读取的字符
     * @throws java.io.IOException
     */
    public int read() throws IOException {

        /*// 缓冲区没数据就读源文件
        if (count == 0) {
            count = fileReader.read(buffer);
            if (count < 0) {
                return -1;
            }

            // 读取源文件后强制指针置为0
            position = 0;
            char ch = buffer[position];

            position++;
            count--;

            return ch;
        }
        // 有数据直接取元素
        else if (count > 0) {
            char ch = buffer[position];

            position++;
            count--;

            return ch;
        } else {
            return -1;
        }*/

        if (count == 0) {
            count = fileReader.read(buffer);
            position = 0;
        }

        if (count < 0) {
            return -1;
        }

        char ch = buffer[position++];
        count--;

        return ch;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {

        return 0;
    }

    @Override
    public void close() throws IOException {

    }

    public String readLine() throws IOException {

        StringBuilder sb = new StringBuilder();
        int ch = 0;
        while((ch = read()) != -1) {
            if (ch == '\r') {
                continue;
            }
            if (ch == '\n') {
                return sb.toString();
            }
            sb.append((char)ch);
        }

        if (sb.length() > 0) {
            return sb.toString();
        }

        return null;
    }
}
