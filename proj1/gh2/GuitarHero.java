package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static GuitarString[] createNotes(String a) {
        GuitarString[] notes = new GuitarString[a.length()];
        for (int i = 0; i < a.length(); i += 1) {
            notes[i] = new GuitarString(440 * Math.pow(2, (double) (i - 24.0) / 12.0));
            System.out.println( 440 * Math.pow(2.0,  (double)(i - 24) / 12));
        }
        return notes;
    }
    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        GuitarString[] notes = createNotes(keyboard);
        GuitarString s = new GuitarString(400.0);
        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (keyboard.indexOf(key) >= 0) {
                    s = notes[keyboard.indexOf(key)];
                    s.pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = s.sample();
            /* play the sample on standard audio */
            StdAudio.play(sample);
            /* advance the simulation of each guitar string by one step */
            s.tic();
        }
    }
}
