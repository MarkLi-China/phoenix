package com.domain.java.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * com.domain.java.test
 * @author Mark Li
 * @version 1.0.0
 * @since 2016/9/2
 */
public class FtpClient {

    public static void main(String[] args) {

        try (
                Socket socket = new Socket("120.55.93.40", 8021);
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
                InputStreamReader reader = new InputStreamReader(socket.getInputStream());
        ) {
            Thread readThread = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        char[] line = new char[1000];
                        while ((reader.read(line)) > 0) {
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            readThread.start();
            writeLine(writer, "USER weyao_datafile_02\r\n");
            writeLine(writer, "PASS ad23SDF2ADdds32dsdf\r\n");
            writeLine(writer, "HELP\r\n");
            writeLine(writer, "PASV\r\n");
            writeLine(writer, "LIST\r\n");
            writeLine(writer, "QUIT\r\n");
            readThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeLine(OutputStreamWriter writer, String line) throws IOException {

        writer.write(line);
        writer.flush();
    }
}
