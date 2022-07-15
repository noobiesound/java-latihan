// Nama package ngikut punyamu ya.
package <nama_package>;
import java.util.*;

/* Program Persamaan oleh AFP.
 * Dibuat pada tanggal 13/07/2022, ditulis menggunakan Java. 
 */

public class Persamaan {
    
    // Program utama.
    public static void main (String[] args) {

        System.out.println("Persamaan");
        System.out.println("=========\n");

        Scanner inputan = new Scanner(System.in);
        String eq;

        System.out.print("> Masukkan persamaan : ");
        eq = inputan.next();
        inputan.close();

        System.out.println("\n> Hasil :");

        EqSolve(eq);

    }

    // Method utama untuk menyelesaikan persamaan.
    public static void EqSolve(String input) {

        int i, finalCoe, finalCons;
        boolean eqDet = false;
        String res;
        String[] ruas = input.split("=");

        for (i = 0; i < input.length(); i++) {

            if (input.charAt(i) == '=') {
                eqDet = true;
                break;
            }

        }

        // Sebuah persamaan tentunya harus memuat tanda "="
        if (eqDet) {

            String[] frag0 = SplitByOp(ruas[0]);
            char var = GetVar(ruas[0]);
            int[] coe = new int[2];
            int[] cons = new int[2];

            // Cek apakah ruas kanan hanya terdapat konstanta atau memuat
            // variabel juga.
            if (FindVar(ruas[1]) > 0) {

                String[] frag1 = SplitByOp(ruas[1]);

                // Jika ruas kanan terdapat variabel juga, maka
                // kemungkinannya berbentuk ax + b = cx + d.
                if (FindVar(ruas[0]) > 0) {

                    for (i = 0; i < frag0.length; i++) {
                        if (FindVar(frag0[i]) > 0) {
                            coe[0] = FindCoe(frag0[i]);
                        } else {
                            cons[0] = Integer.parseInt(frag0[i]);
                        }
                    }
    
                    for (i = 0; i < frag1.length; i++) {
                        if (FindVar(frag1[i]) > 0) {
                            coe[1] = FindCoe(frag1[i]);
                        } else {
                            cons[1] = Integer.parseInt(frag1[i]);
                        }
                    }
    
                    finalCoe = coe[0] - coe[1];
                    finalCons = cons[1] - cons[0];
    
                    if (finalCoe == 0) {
                        System.err.println("Error! Bukan persamaan!");
                    } else {
                        res = div(finalCons, finalCoe);
                        System.out.printf("%c = %s\n\n", var, res);
                    }

                // Jika tidak, maka kemungkinannya berbentuk c = ax + b.
                } else {

                    for (i = 0; i < frag1.length; i++) {
                        if (FindVar(frag1[i]) > 0) {
                            coe[0] = FindCoe(frag1[i]);
                        } else {
                            cons[0] = Integer.parseInt(frag1[i]);
                        }
                    }

                    finalCons = Integer.parseInt(ruas[0]) - cons[0];

                    if (coe[0] == 0) {
                        System.err.println("Error! Bukan persamaan!");
                    } else {
                        res = div(finalCons, coe[0]);
                        System.out.printf("%c = %s\n\n", var, res);
                    }

                }
            
            // Jika ruas kanan hanya terdapat konstanta, maka persamaannya
            // berbentuk ax + b = c.
            } else {

                for (i = 0; i < frag0.length; i++) {
                    if (FindVar(frag0[i]) > 0) {
                        coe[0] = FindCoe(frag0[i]);
                    } else {
                        cons[0] = Integer.parseInt(frag0[i]);
                    }
                }

                finalCons = Integer.parseInt(ruas[1]) - cons[0];

                if (coe[0] == 0) {
                    System.err.println("Error! Bukan persamaan!");
                } else {
                    res = div(finalCons, coe[0]);
                    System.out.printf("%c = %s\n\n", var, res);
                }

            }

        } else {
            System.err.println("Error!");
        }

    }

    // Method untuk menemukan sekaligus menghitung variabel yang ada.
    private static int FindVar (String frag) {

        int varCount = 0;
        char tempCh;

        for (int i = 0; i < frag.length(); i++) {
            tempCh = frag.charAt(i);
            boolean det = ((tempCh == '+' || tempCh == '-') || ((int) tempCh >= 48 && (int) tempCh <= 57));

            if (!(det)) {
                varCount++;
            }
        }

        return varCount;

    }

    // Method untuk mengambil nama variabel yang dipakai.
    private static char GetVar(String input) {

        char var = 'x', tempCh;

        for (int i = 0; i < input.length(); i++) {
            tempCh = input.charAt(i);
            boolean det = ((tempCh == '+' || tempCh == '-') || ((int) tempCh >= 48 && (int) tempCh <= 57));

            if (!(det)) {
                var = tempCh;
            }
        }

        return var;

    }

    // Method untuk mengambil nilai koefisien.
    private static int FindCoe (String frag) {

        int coe = 0;
        char tempCh;
        String coeStr;
        boolean digDet = false, min = false;
        
        for (int i = 0; i < frag.length(); i++) {
            tempCh = frag.charAt(i);

            if ((int) tempCh >= 48 && (int) tempCh <= 57) {
                digDet = true;
            }
            if (tempCh == '-') {
                min = true;
            }

        }

        if (digDet) {
            coeStr = frag.substring(0, frag.length() - 1);
            coe = Integer.parseInt(coeStr);
        } else {
            coe = min ? (coe = -1) : (coe = 1);
        }

        return coe;

    }

    // Method khusus untuk pemisahan antara variabel dengan konstanta.
    private static String[] SplitByOp(String input) {

        String[] frag = new String[2];
        char tempCh;
        int len = input.length(), opCount = 0;

        for (int i = 0; i < len; i++) {
            tempCh = input.charAt(i);

            if (tempCh == '+') {
                opCount++;
                frag[0] = input.substring(0, i);
                frag[1] = input.substring(i + 1, len);

            } else if (tempCh == '-') {
                opCount++;
                frag[0] = input.substring(0, i);
                frag[1] = input.substring(i, len);

            }
        }

        if (opCount == 0) {
            frag[0] = input;
            frag[1] = input;
        }

        return frag;

    }

    

    // =====================================================
    // | Di bawah ini merupakan method-method khusus untuk |
    // | mekanisme penyederhanaan pecahan.                 |
    // =====================================================

    // Method khusus untuk membuat array berisikan bilangan prima.
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

    // Method khusus untuk menemukan KPK dan FPB.
    // Untuk pembagian, yang diperlukan hanya FPB saja >>> LcmGcd(angka[], 2);
    private static int LcmGcd (int[] angka, int opsi) {

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

        switch (opsi) {
            case 1 : return lcm;
            case 2 : return gcd;
            default :
                System.err.println("Opsi tidak dikenali!");

        }

        return 0;

    }

    // Method khusus untuk mekanisme penyederhanaan pecahan.
    private static String div(int bil1, int bil2) {

        int[] tempNum = new int[2];
        tempNum[0] = bil1;
        tempNum[1] = bil2;

        // Variabel tempNum harus bernilai mutlak karena akan
        // dipakai untuk mencari FPB kedua bilangan.
        if (tempNum[0] < 1) {
            tempNum[0] *= (-1);
        }
        if (tempNum[1] < 1) {
            tempNum[1] *= (-1);
        }

        int hasilInt;
        int fpb; 
        String hasilStr;
        
        if (bil2 < 0) {
            bil1 *= -1;
            bil2 *= -1;
        }

        // Di sini dibuatkan lagi variabel dengan nilai absolut karena
        // variabel tempNum hanya sekali pakai.
        int abs1 = Math.abs(bil1);
        int abs2 = Math.abs(bil2);

        // Proses utama.
        if (abs1 > abs2) {

            if (abs1 % abs2 == 0) {
                hasilInt = (int) bil1 / bil2;
                hasilStr = Integer.toString(hasilInt);

            } else if (abs2 == -1) {
                hasilInt = (int) bil1 / bil2;
                hasilStr = Integer.toString(hasilInt);

            } else {
                fpb = LcmGcd(tempNum, 2);
                bil1 /= fpb;
                bil2 /= fpb;

                hasilStr = bil1 + "/" + bil2;

            }
        
        } else if (abs1 == abs2) {
            hasilInt = (int) bil1 / bil2;
            hasilStr = Integer.toString(hasilInt);
        
        } else {
            if (abs1 == 0) {
                hasilStr = "0";
            } else if (abs1 == 1) {
                hasilStr = bil1 + "/" + bil2;
            } else {
                fpb = LcmGcd(tempNum, 2);
                bil1 /= fpb;
                bil2 /= fpb;
                
                hasilStr = bil1 + "/" + bil2;
            }

        }

        return hasilStr;

    }

}
