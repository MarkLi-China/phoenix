package com.domain.java.first;

import javax.sound.midi.*;
import java.util.Arrays;

/**
 * Created with Intellij IDEA
 * @author QX
 * @version 1.0.0
 * @since 2015-7-21
 */
public class Music {

    public void play(int instrument, int note) {

        try {
            Sequencer player = MidiSystem.getSequencer();

            System.out.println("We get a sequencer!");

            player.open();

            Sequence sequence = new Sequence(Sequence.PPQ, 4);

            Track track = sequence.createTrack();

            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, instrument, 0);
            MidiEvent changeInstrument = new MidiEvent(first, 1);
            track.add(changeInstrument);

            ShortMessage msg1 = new ShortMessage();
            msg1.setMessage(144, 1, note, 100);
            MidiEvent noteOn = new MidiEvent(msg1, 1);
            track.add(noteOn);

            ShortMessage msg2 = new ShortMessage();
            msg2.setMessage(128, 1, note, 100);
            MidiEvent noteOff = new MidiEvent(msg2, 16);
            track.add(noteOff);

            player.setSequence(sequence);

            player.start();
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        System.out.println("args = " + Arrays.toString(args));

        Music music = new Music();
        if (args.length < 2) {
            System.out.println("Don't forget the instrument and note args!");
        } else {
            int instrument = Integer.parseInt(args[0]);
            int note = Integer.parseInt(args[1]);
            music.play(instrument, note);
        }
    }
}
