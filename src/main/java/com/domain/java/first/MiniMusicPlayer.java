package com.domain.java.first;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;

/**
 * Created with Intellij IDEA
 * @author QX
 * @version 1.0.0
 * @since 2015-7-23
 */
public class MiniMusicPlayer {

    static JFrame frame = new JFrame("My Music Video");

    static DrawPanel drawPanel;

    public static void main(String[] args) {

        MiniMusicPlayer player = new MiniMusicPlayer();
        player.go();
    }

    public void setUpGui() {

        drawPanel = new DrawPanel();
        frame.setContentPane(drawPanel);
        frame.setBounds(30, 30, 300, 300);
        frame.setVisible(true);
    }

    public void go() {

        setUpGui();

        try {
            Sequencer player = MidiSystem.getSequencer();
            player.open();

            player.addControllerEventListener(drawPanel, new int[] {127});

            Sequence sequence = new Sequence(Sequence.PPQ, 4);
            Track track = sequence.createTrack();

            int r = 0;
            for (int i=0; i<60; i+=4) {
                r = (int) ((Math.random() * 50) + 1);
                track.add(makeEvent(144, 1, r, 100, i));
                track.add(makeEvent(176, 1, 127, 0, i));
                track.add(makeEvent(128, 1, r, 100, i+2));
            }
            player.setSequence(sequence);
            player.setTempoInBPM(120);
            player.start();
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    private static MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {

        MidiEvent event = null;

        try {
            ShortMessage msg = new ShortMessage();
            msg.setMessage(comd, chan, one, two);
            event = new MidiEvent(msg, tick);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }

        return event;
    }

    class DrawPanel extends JPanel implements ControllerEventListener {

        boolean msg = false;

        @Override
        public void controlChange(ShortMessage event) {

            msg = true;
            repaint();
        }

        public void paintComponent(Graphics graphics) {

            if (msg) {
                Graphics2D g2d = (Graphics2D) graphics;

                int red = (int) (Math.random() * 250);
                int green = (int) (Math.random() * 250);
                int blue = (int) (Math.random() * 250);

                g2d.setColor(new Color(red, green, blue));

                int height = (int) ((Math.random() * 120) + 10);
                int width = (int) ((Math.random() * 120) + 10);
                int x = (int) ((Math.random() * 40) + 10);
                int y = (int) ((Math.random() * 40) + 10);

                g2d.fillRect(x, y, width, height);
                msg = false;
            }
        }
    }
}
