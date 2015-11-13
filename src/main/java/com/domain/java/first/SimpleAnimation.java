package com.domain.java.first;

import javax.swing.*;
import java.awt.*;

/**
 * Created with Intellij IDEA
 * @author QX
 * @version 1.0.0
 * @since 2015-7-23
 */
public class SimpleAnimation {

    int x = 70;
    int y = 70;

    public static void main(String[] args) {

        SimpleAnimation gui = new SimpleAnimation();
        gui.go();
    }

    public void go() {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DrawPanel drawPanel = new DrawPanel();

        frame.getContentPane().add(drawPanel);
        frame.setSize(300, 300);
        frame.setVisible(true);

        for (int i=0; i<130; i++) {
            x++;
            y++;

            drawPanel.repaint();

            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class DrawPanel extends JPanel {

        public void paintComponent(Graphics graphics) {

            graphics.setColor(Color.WHITE);
            graphics.fillOval(0, 0, this.getWidth(), this.getHeight());

            graphics.setColor(Color.ORANGE);
            graphics.fillOval(x, y, 40, 40);
        }
    }
}
