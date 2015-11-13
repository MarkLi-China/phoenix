package com.domain.java.first;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with Intellij IDEA
 * @author QX
 * @version 1.0.0
 * @since 2015-7-23
 */
public class QuizCardBuilder {

    JTextArea question;

    JTextArea answer;

    ArrayList<QuizCard> cardList;

    JFrame frame;

    public static void main(String[] args) {

        new QuizCardBuilder().go();
    }

    private void go() {

        frame = new JFrame("Quiz Card Builder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        Font bigFont = new Font("sanserif", Font.BOLD, 24);
        question = new JTextArea(6, 20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(bigFont);

        JScrollPane quesScroller = new JScrollPane(question);
        quesScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        quesScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        answer = new JTextArea(6, 20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(bigFont);

        JScrollPane ansScroller = new JScrollPane(answer);
        ansScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        ansScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JButton nextButton = new JButton("next card");

        cardList = new ArrayList<>();

        JLabel quesLabel = new JLabel("question");
        JLabel ansLabel = new JLabel("answer");

        panel.add(quesLabel);
        panel.add(quesScroller);
        panel.add(ansLabel);
        panel.add(ansScroller);
        panel.add(nextButton);

        nextButton.addActionListener(new NextCardListener());

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem saveMenuItem = new JMenuItem("Save");

        newMenuItem.addActionListener(new NewMenuListener());
        saveMenuItem.addActionListener(new SaveMenuListener());
        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);

        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(500, 600);
        frame.setVisible(true);
    }

    private class NextCardListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            QuizCard card = new QuizCard(question.getText(), answer.getText());
            cardList.add(card);
            clearCard();
        }
    }

    private class SaveMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            QuizCard card = new QuizCard(question.getText(), answer.getText());
            cardList.add(card);

            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
        }
    }

    private class NewMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            cardList.clear();
            clearCard();
        }
    }

    private void clearCard() {

        question.setText("");
        answer.setText("");
        question.requestFocus();
    }

    private void saveFile(File file) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (QuizCard card: cardList) {
                bw.write(card.getQuestion() + "/");
                bw.write(card.getAnswer() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("couldn't write the cardList out");
            e.printStackTrace();
        }
    }
}
