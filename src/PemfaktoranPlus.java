// Nama package ngikut punyamu ya.
package <nama_package>;
import java.util.*;
import java.lang.Math;

/* Program KPK dan FPB oleh AFP.
 * Dibuat pada tanggal 14/12/2021, ditulis menggunakan Java. 
 */

public class PemfaktoranPlus {

    // Program utama.
    public static void main(String[] args) {

        System.out.println("KPK dan FPB");
        System.out.println("===========\n");

        int banyak;
        Scanner input = new Scanner(System.in);

        System.out.print("Banyaknya angka \t: ");
        banyak = input.nextInt();        

        int[] bil = new int[banyak];

        for (int i = 0; i < banyak; i++) {
            
            if (banyak <= 1) {
                System.out.printf("> Bilangan \t: ");
                bil[i] = input.nextInt();

            } else {
                System.out.printf("> Bilangan ke-%d \t: ", i + 1);
                bil[i] = input.nextInt();

            }

        }

        input.close();
        LcmGcd(bil);

    }

    // Method khusus untuk pembuatan array bilangan prima.
    private static int[] PrimeInRange (int limit) {

        int[] temp = new int[limit];
        int start = 2;
        int bagi = 1;
        int i = 0;

        do {

            bagi = 1;

            do {
                bagi++;
            } while (start % bagi != 0);

            if (start == bagi) {
                temp[i] = start;
                i++;
				
            }

            start++;

        } while (start <= limit);

        int[] arrayRes = new int[i];

        for (int j = 0; j <= i; j++) {

            if (temp[j] != 0) {
                arrayRes[j] = temp[j];
            }

        }

        return arrayRes;

    }

    // Method khusus mencari faktor prima. Opsional.
    private static void Fakt(int bil) {

        System.out.print("(");
        int bil2 = 2;

        if (bil <= 1) {
            System.out.print(bil + ")\n");
        } else {
            
            do {
            
                if (bil % bil2 == 0) {
    
                    System.out.print(bil2);
                    bil /= bil2;
    
                    if (bil != 1) {
                        System.out.print(" x ");
                    } else {
                        System.out.print(")\n");
                    }
    
                } else {
                    bil2++;
                }
    
            } while (bil > 1);

        }

    }

    // Method untuk menemukan KPK dan FPB.
    private static void LcmGcd (int[] angka) {

        Arrays.sort(angka);
        int p_Angka = angka.length;
        int maxNum = angka[p_Angka - 1];
        int[] arrayPrima = PrimeInRange(maxNum);
        int[][] faktNum = new int[p_Angka][arrayPrima.length];

        int i = 0;
        int j = 0;

        // Proses pemfaktoran setiap angka, faktor dari masing-masing
        // angka akan disimpan dalam array tersendiri.

        do {

            j = 0;

            while (angka[i] > 1) {

                if (angka[i] % arrayPrima[j] == 0) {
                    faktNum[i][j] += 1;
                    angka[i] /= arrayPrima[j];

                } else {
                    j++;
                }

            }
            
            i++;

        } while (i < p_Angka);

        // Proses menghitung KPK dan FPB.

        i = 0;
        j = 0;
        int maxIndex;
        int minIndex;
        int lcm = 1;
        int gcd = 1;

        while (j < arrayPrima.length) {
	
            maxIndex = 0;
			minIndex = 0;
			
            for (i = 1; i < p_Angka; i++) {

                if (faktNum[i][j] > faktNum[i - 1][j]) {
                    maxIndex = i;
                }

                if (faktNum[i][j] <= faktNum[i - 1][j]) {
                    minIndex = i;
                }

            }

            if (faktNum[maxIndex][j] != 0) {
                lcm *= Math.pow(arrayPrima[j], faktNum[maxIndex][j]);
            }

            if (faktNum[minIndex][j] != 0) {
                gcd *= Math.pow(arrayPrima[j], faktNum[minIndex][j]);
            }

            j++;

        }

        System.out.println("\nHasil");
        System.out.println("=====\n");
        System.out.println("KPK : " + lcm);
        Fakt(lcm);
        System.out.println("FPB : " + gcd);
        Fakt(gcd);

    }

}
