package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        SLList<Integer> N = new SLList<>();
        AList<Integer> Ns = new AList<>();
        AList<Double> time = new AList<>();
        AList<Integer> ops = new AList<>();
        int tick = 0;

        for(int i = 0; i < 128000; i += 1){
            N.addLast(i);
            if (i == Math.pow (2, tick) * 1000){
                Ns.addLast(i);
                Stopwatch sw = new Stopwatch();
                for (int j = 0; j < 10000; j += 1){
                    N.getLast();
                }
                ops.addLast(10000);
                time.addLast(sw.elapsedTime());
                tick += 1;
            }
        }
        printTimingTable(Ns, time, ops);
    }

}
