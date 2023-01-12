import java.util.Scanner;
import java.lang.Math;

/* Program Kalkulator Tone Equal Temperament oleh AFP.
 * Dibuat pada tanggal 12/01/2023, ditulis menggunakan Java.
 */

public class ToneEqualTemperament {
    // Main method.
    public static void main(String[] args) {
        int tet, oct;
        Scanner scan = new Scanner(System.in);

        clrscr();
        System.out.println("TET Frequencies List");
        System.out.println("====================\n");

        System.out.print("> Division    : ");
        tet = scan.nextInt();
        System.out.print("> Start point : ");
        oct = scan.nextInt();
        
        System.out.printf("\n%d-TET Frequencies List :\n", tet);
        TETFreqList(tet, oct);
        System.out.printf("(With N0 = C%d and N%d = C%d)", oct, tet, oct + 1);
        scan.close();
    }
	
    // Making C4 frequency as a constant.
    public static final double freqC4 = fracPow(2, -3, 4) * 440;

    // Method for displaying list of frequencies with given TET value.
    public static void TETFreqList (int input, int oct) {
        double freq = 0f;
        double cent = 0f;
        
        for (int i = 0; i <= input; i++) {
            freq = Math.pow(2, (oct - 4)) * (double) fracPow(2, i, input) * freqC4;
            cent = ((double) 1200 / input) * i;
            System.out.printf("N%d = %.2f Hz >>> Distance (from C%d) = %.2f cent(s)\n", i, freq, oct, cent);
        }
    }
	
    // Method for performing exponential operation with fraction as
    // its exponent, since Java doesn't support for this kind of
    // operation.
    private static double fracPow(double a, double m, double n) {
        if (m == 0) {
            if ((a == 0) || (n == 0))  {
                return -1;
            } else {
                return 1;
            }
        } else if (m == n) {
            return a;
        } else if (a == 1) {
            return 1;
        } else {
            return root(Math.pow(a, m), n);
        }
    }
	
    // Method for performing n-th root. 
    private static double root(double a, double n) {
        if (n <= 0) {
        	return -1;
        } else if (n == 1) {
        	return a;
        } else if (n == 2) {
        	return Math.sqrt(a);
        }
        
        double eps = 1 / Math.pow(10, 10);
        double x1 = 1;
        double x2 = 1;
        
        do {
            x1 = x2;
            x2 = x1 - ((Math.pow(x1, n) - a) / (n * Math.pow(x1, (n - 1))));
        } while (Math.abs(x2 - x1) > eps);

        return x2;
    }
	
    // Method for clearing screen. Optional.
    public static void clrscr () {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (Exception e) {
            System.err.println("Error : Clrscr can not be executed!");
        }
    }
}
