package com.domain.java.first;

import javax.swing.*;
import java.awt.*;

/**
 * Created with Intellij IDEA
 * @author QX
 * @version 1.0.0
 * @since 2015-7-22
 */
public class DrawPanel extends JPanel {

    public void paintComponent(Graphics graphics) {

        Graphics2D g2d = (Graphics2D) graphics;

        Color startColor = randomColorFactory();

        Color endColor = randomColorFactory();

        GradientPaint gradientPaint = new GradientPaint(70, 70, startColor, 150, 150, endColor);
        g2d.setPaint(gradientPaint);
        g2d.fillOval(70, 70, 100, 100);

    }

    private Color randomColorFactory() {

        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);

        return new Color(red, green, blue);
    }
}
