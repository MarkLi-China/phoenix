package com.domain.java.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * com.domain.java.test
 * @author Mark Li
 * @version 1.0.0
 * @since 2016/9/2
 */
public class FtpClient2 {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("120.55.93.40", 8021);
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.println("start: " + readStream(reader));

        String username = "USER weyao_datafile_02\r\n";
        writer.write(username);
        writer.flush();
        System.out.println("send username: " + readStream(reader));

        String password = "PASS ad23SDF2ADdds32dsdf\r\n";
        writer.write(password);
        writer.flush();
        System.out.println("send password: " + readStream(reader));

        String help = "HELP\r\n";
        writer.write(help);
        writer.flush();
        System.out.println("help: " + readStream(reader));

        String pasv = "PASV\r\n";
        writer.write(pasv);
        writer.flush();
        System.out.println("pasv: " + reader.readLine());

        String list = "LIST\r\n";
        writer.write(list);
        writer.flush();
        System.out.println("list: " + reader.readLine());

        String quit = "QUIT\r\n";
        writer.write(quit);
        writer.flush();
        System.out.println("quit: " + reader.readLine());

        writer.close();
        reader.close();
        socket.close();
    }

    private static String readStream(BufferedReader reader) throws IOException {

        String message = null;
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println("line = " + line);
            message += line;
        }
        System.out.println("message = " + message);

        return message;
    }
}
