package com.domain.java.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with Intellij IDEA
 * @author QX
 * @version 1.0.0
 * @since 2015-7-24
 */
public class SimpleChatServer {

    ArrayList clientOutputStreams;

    public static void main(String[] args) {

        new SimpleChatServer().go();
    }

    private void go() {

        clientOutputStreams = new ArrayList();
        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            while (true) {
                Socket socket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                clientOutputStreams.add(writer);

                Thread thread = new Thread(new ClientHandler(socket));
                thread.start();

                System.out.println("get a connection");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler implements Runnable {

        BufferedReader reader;

        Socket socket;

        public ClientHandler(Socket socket) {

            try {
                this.socket = socket;
                this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {

            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("read " + message);
                    tellEveryone(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void tellEveryone(String message) {

        Iterator it = clientOutputStreams.iterator();
        while (it.hasNext()) {
            try {
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(message);
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
