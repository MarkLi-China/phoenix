package com.domain.java.first;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created with Intellij IDEA
 * @author QX
 * @version 1.0.0
 * @since 2015-7-24
 */
public class SimpleChatClient {

    JTextArea incoming;

    JTextField outgoing;

    BufferedReader reader;

    PrintWriter writer;

    Socket socket;

    public static void main(String[] args) {

        new SimpleChatClient().go();
    }

    private void go() {

        JFrame frame = new JFrame("Simple Chat Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();

        incoming = new JTextArea(15, 50);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);
        JScrollPane scroller = new JScrollPane(incoming);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        outgoing = new JTextField(20);
        JButton button = new JButton("Send");
        button.addActionListener(new SendButtonListener());

        panel.add(scroller);
        panel.add(outgoing);
        panel.add(button);
        frame.getContentPane().add(BorderLayout.CENTER, panel);

        setUpNetworking();

        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();

        frame.setSize(650, 500);
        frame.setVisible(true);
    }

    private void setUpNetworking() {

        try {
            socket = new Socket("127.0.0.1", 5000);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
            System.out.println("networking established");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class SendButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

            try {
                writer.println(outgoing.getText());
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }

    private class IncomingReader implements Runnable {

        @Override
        public void run() {

            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("read " + message);
                    incoming.append(message + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
