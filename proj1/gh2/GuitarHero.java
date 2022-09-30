package gh2;
import edu.princeton.cs.algs4.Count;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHero {
    public static final double CONCERT_A = 440.0;
    public static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    public static void main(String[] args) {

        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        int COUNT = keyboard.length();

        GuitarString[] hero = new GuitarString[COUNT];
        for (int i = 0; i < COUNT; i++) {
            hero[i] = new GuitarString(440.0 * Math.pow(2,(i - 24)/12.0));
        }
        while (true){
            int m  = 0;
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                  if(index == -1)
                    continue;
                  hero[index].pluck();
                    }
            /* compute the superposition of samples */
            //double sample = stringA.sample() + stringC.sample();
            double sample = 0.0;
            for (int j = 0; j < COUNT; j++) {
                sample += hero[j].sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int j = 0; j < COUNT; j++) {
                hero[j].tic();
            }
        }
    }
}


