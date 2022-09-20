/** Class that prints the Collatz sequence starting from a given number.
 *  @author Hao Wang
 */
import java.util.Scanner;

public class Collatz {

    /** Buggy implementation of nextNumber! */
    public static int nextNumber(int n) {
        while (n != 1) {
            System.out.print(n + " ");
            if (n % 2 == 0) {
                n = n / 2;
            }
            else {
                n = 3 * n + 1;
            }
        }
        System.out.print(1);
        return n;
    }
    public static void main(String[] args) {
        Scanner x = new Scanner(System.in);
        System.out.println("Please type in an integer:");
        int n = x.nextInt();
        nextNumber(n);
         }
}

