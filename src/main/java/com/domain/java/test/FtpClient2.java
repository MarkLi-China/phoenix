package com.domain.java.test;

import java.io.*;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * com.domain.java.test
 * @author Mark Li
 * @version 1.0.0
 * @since 2016/9/2
 */
public class FtpClient2 {

    static String ip;

    static int port;

    static FileWriter fileWriter;

    static boolean write;

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("120.55.93.40", 8021);
        InputStream is = System.in;
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        new Thread(new Runnable() {
            @Override
            public void run() {

                String line;
                try {
                    while ((line = reader.readLine()) != null) {
                        System.out.println("reader::::" + line);
                        if (line.contains("227 Entering Passive Mode")) {
                            String find = "\\(.*?\\)";
                            Pattern pattern = Pattern.compile(find);
                            Matcher matcher = pattern.matcher(line);
                            matcher.find();
                            String temp = matcher.group();
                            String temp1 = temp.substring(1, temp.length() - 1);
                            System.out.println(temp1);
                            String[] arr = temp1.split(",");
                            ip = arr[0] + "." + arr[1] + "." + arr[2] + "." + arr[3];
                            port = 256 * Integer.parseInt(arr[4]) + Integer.parseInt(arr[5]);

                            Socket socket1 = new Socket(ip, port);
                            final BufferedReader reader1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));

                            new Thread(new Runnable() {
                                @Override
                                public void run() {

                                    String line;
                                    try {
                                        while ((line = reader1.readLine()) != null) {
                                            System.out.println("reader1::::" + line);
                                            if (write) {
                                                fileWriter.write(line);
                                                fileWriter.flush();
                                            }
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                        }

                        if (line.contains("150 Opening BINARY mode data connection for")) {
                            write = true;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        StringBuilder sb = new StringBuilder();
        int ch;
        while ((ch = is.read()) != -1) {
            if (ch == '\r') {
                continue;
            }
            if (ch == '\n') {
                String temp = sb.toString();
                if ("over".equalsIgnoreCase(temp)) {
                    if (socket.isConnected()) {
                        write(writer, "QUIT\r\n");
                    }
                    break;
                }
                System.out.println("command::::" + temp.toUpperCase());
                if (temp.contains("RETR")) {
                    String fileName = temp.replace("RETR", "").replaceAll(" ", "");
                    System.out.println("fileName = " + fileName);
                    fileWriter = new FileWriter(new File(fileName));
                }
                write(writer, sb.toString() + "\r\n");
                sb.delete(0, temp.length());
            } else {
                sb.append((char) ch);
            }
        }

        /*String username = "USER weyao_datafile_02\r\n";
        write(writer, username);

        String password = "PASS ad23SDF2ADdds32dsdf\r\n";
        write(writer, password);

        String help = "HELP\r\n";
        write(writer, help);

        String pasv = "PASV\r\n";
        write(writer, pasv);

        String list = "LIST\r\n";
        write(writer, list);

        String quit = "QUIT\r\n";
        write(writer, quit);*/

        /*writer.close();
        reader.close();
        socket.close();*/
    }

    private static void write(PrintWriter writer, String command) {

        writer.write(command);
        writer.flush();
    }
}
