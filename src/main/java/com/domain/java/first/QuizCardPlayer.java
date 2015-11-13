package com.domain.java.first;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with Intellij IDEA
 * @author QX
 * @version 1.0.0
 * @since 2015-7-24
 */
public class QuizCardPlayer {

    JTextArea display;

    ArrayList<QuizCard> cardList;

    QuizCard currentCard;

    int currentCardIndex;

    JFrame frame;

    JButton nextButton;

    boolean isShowAnswer;

    public static void main(String[] args) {

        new QuizCardPlayer().go();
    }

    private void go() {

        frame = new JFrame("Quiz Card Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        Font bigFont = new Font("sanserif", Font.BOLD, 24);

        display = new JTextArea(10, 20);
        display.setFont(bigFont);
        display.setLineWrap(true);
        display.setEditable(false);

        JScrollPane quesScroller = new JScrollPane(display);
        quesScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        quesScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        nextButton = new JButton("Show Question");
        panel.add(quesScroller);
        panel.add(nextButton);
        nextButton.addActionListener(new NextCardListener());

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load Card Set");
        loadMenuItem.addActionListener(new OpenMenuListener());
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);

        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(640, 500);
        frame.setVisible(true);
    }

    class NextCardListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (isShowAnswer) {
                display.setText(currentCard.getAnswer());
                nextButton.setText("Next Card");
                isShowAnswer = false;
            } else {
                if (currentCardIndex < cardList.size()) {
                    showNextCard();
                } else {
                    display.setText("This is last card");
                    nextButton.setEnabled(false);
                }
            }
        }
    }

    class OpenMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(frame);
            loadFile(fileOpen.getSelectedFile());
        }
    }

    private void showNextCard() {

        currentCard = cardList.get(currentCardIndex);
        currentCardIndex++;
        display.setText(currentCard.getQuestion());
        nextButton.setText("Show Answer");
        isShowAnswer = true;
    }

    private void loadFile(File file) {

        cardList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                makeCard(line);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("couldn't read the card file");
            e.printStackTrace();
        }
    }

    private void makeCard(String lineToParse) {

        String[] result = lineToParse.split("/");
        QuizCard card = new QuizCard(result[0], result[1]);
        cardList.add(card);
        System.out.println("made a card");
    }
}
