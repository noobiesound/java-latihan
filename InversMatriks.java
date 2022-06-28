// Nama package ngikut punyamu ya.
package com.mengcoba;

/* Program Invers Matriks oleh AFP.
 * Dibuat pada tanggal 25/06/2022, ditulis menggunakan Java. 
 */
 
import java.util.*;
import java.lang.Math;

public class InversMatriks {

    // Program Utama
    public static void main (String[] args) {

        System.out.println("Invers Matriks");
        System.out.println("==============\n");

        int[][] arrayDua = {
            {5, -8},
            {-3, 2}
        };

        int[][] arrayTiga = {
            {3, -2, 1},
            {2, 7, -8},
            {6, 1, -5}
        };

        // Output matriks pertama beserta inversnya.
        System.out.println("Matriks Pertama");
        PrintArray(arrayDua);

        System.out.println("> Invers");
        String arrayInv2[][] = Inv(arrayDua);
        PrintArray(arrayInv2);

        // Output matriks kedua beserta inversnya.
        System.out.println("Matriks Kedua");
        PrintArray(arrayTiga);

        System.out.println("> Invers");
        String arrayInv3[][] = Inv(arrayTiga);
        PrintArray(arrayInv3);

    }

    // Method untuk mencetak Array Integer.
    public static void PrintArray(int[][] arrayInput) {

        int baris = arrayInput.length;
        int kolom = arrayInput[0].length;

        for (int i = 0; i < baris; i++) {

            System.out.print("| ");

            for (int j = 0; j < kolom; j++) {

                System.out.print(arrayInput[i][j]);
				
			if (j < kolom - 1) {
				System.out.print(" ");
			}

            }

            System.out.print(" |\n");

        }

        System.out.print("\n");

    }

    // Method untuk mencetak Array String.
    private static void PrintArray(String[][] arrayInput) {

        int baris = arrayInput.length;
        int kolom = arrayInput[0].length;

        for (int i = 0; i < baris; i++) {

            System.out.print("| ");

            for (int j = 0; j < kolom; j++) {

                System.out.print(arrayInput[i][j]);
				
			if (j < kolom - 1) {
				System.out.print(" ");
			}

            }

            System.out.print(" |\n");

        }

        System.out.print("\n");

    }

    // Method khusus untuk mencari determinan matriks.
    private static int det(int[][] arrayInput) {
        
        int baris = arrayInput.length;
        int kolom = arrayInput[0].length;
        int hasil = 0;

        // Syarat untuk mencari determinan adalah matriksnya
        // haruslah berordo persegi.
        if (baris != kolom) {
            System.err.println("Matriks ini tidak memiliki determinan.");

        } else {

            switch(baris) {
            
                // Untuk ordo 2x2, rumus determinan = ad - bc.
                case 2 :
                    hasil = (arrayInput[0][0] * arrayInput[1][1]) - (arrayInput[0][1] * arrayInput[1][0]);
                    return hasil;
    
                // Untuk ordo 3x3, di sini digunakanlah aturan Sarrus.
                case 3 :
    
                    int i, j, k, l;
                    int temp = 1;
                    int hasil1 = 0;
                    int hasil2 = 0;
                    i = 0;
                    k = 0;
    
                    for (i = 0; i < baris; i++) {
    
                        j = 0;
                        temp = 1;
                        l = k;
    
                        for (j = 0; j < baris; j++) {
    
                            if (l > 2) {
                                l = 0;
                            }
    
                            temp *= arrayInput[j][l];
                            l++;
    
                        }
    
                        k++;
                        hasil1 += temp;
    
                    }
    
                    k = 2;
    
                    for (i = 0; i < baris; i++) {
    
                        j = 0;
                        temp = 1;
                        l = k;
    
                        for (j = 0; j < baris; j++) {
    
                            if (l < 0) {
                                l = 2;
                            }
    
                            temp *= arrayInput[j][l];
                            l--;
    
                        }
    
                        k--;
                        hasil2 += temp;
    
                    }
    
                    hasil = hasil1 - hasil2;
                    return hasil;
    
                default :
                    System.err.println("Error!");
                    break;
    
            }

        }

        return 0;

    }

    // Method khusus untuk mencari matriks transpos.
    private static int[][] Transpos(int[][] arrayInput) {

        int baris = arrayInput.length;
        int kolom = arrayInput[0].length;
        int i = 0;
        int j = 0;

        int[][] arrTrans = new int[baris][kolom];

        for (i = 0; i < baris; i++) {

            for (j = 0; j < kolom; j++) {
                arrTrans[i][j] = arrayInput[j][i];
            }

        }

        return arrTrans;

    }

    // Method untuk mencari invers.
    private static String[][] Inv(int[][] arrayInput) {

        int baris = arrayInput.length;
        int kolom = arrayInput[0].length;
        int detInput = det(arrayInput);
        String arrayInv[][] = new String[baris][kolom];
        int arrayAdj[][] = new int[baris][kolom];
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;

        // Kondisi apabila determinan = 0.
        if (detInput == 0) {
            System.err.println("Matriks ini tidak memiliki invers.");
            return arrayInv;

        }

        // Mulai mencari inversnya. Harap diingat bahwa invers hanya
        // bisa ditemukan pada matriks persegi.
        if (baris == kolom) {

            switch (baris) {

                // Untuk ordo 2x2.
                case 2 :

                    // Menentukan matriks adjoin.
                    arrayAdj[0][0] = arrayInput[1][1];
                    arrayAdj[1][1] = arrayInput[0][0];
                    arrayAdj[0][1] = arrayInput[0][1] * (-1);
                    arrayAdj[1][0] = arrayInput[1][0] * (-1);

                    // Mencari inversnya.
                    if (detInput == 1) {
                        
                        for (i = 0; i < baris; i++) {
                            for (j = 0; j < kolom; j++) {
                                arrayInv[i][j] = Integer.toString(arrayAdj[i][j]);
                            }
                        }

                        return arrayInv;

                    } else {

                        for (i = 0; i < baris; i++) {
                            for (j = 0; j < kolom; j++) {
                                
                                if (arrayAdj[i][j] == 0) {
                                    arrayInv[i][j] = Integer.toString(arrayAdj[i][j]);
                                } else {
                                    arrayInv[i][j] = div(arrayAdj[i][j], detInput);
                                }
                                
                            }
							
                        }

                        return arrayInv;

                    }

                // Untuk ordo 3x3.
                case 3 :

                    int KofIndex = 0;
                    int[] eleKof = new int[36];

                    // Mencari elemen matriks minornya.
                    for (i = 0; i < 3; i++) {
                        for (j = 0; j < 3; j++) {
                            for (k = 0; k < 3; k++) {
                                for (l = 0; l < 3; l++) {

                                    if ((k == i) || (l == j)) {
                                        continue;
                                    } else {
                                        eleKof[KofIndex] = arrayInput[k][l];
                                        KofIndex++;

                                    }

                                }

                            }

                        }

                    }

                    // Mencari matriks kofaktor dengan menggunakan elemen matriks
                    // minor yang telah dicari.
                    int[][] arrayKof = new int[3][3];
                    KofIndex = 0;

                    for (i = 0; i < 3; i++) {
                        for (j = 0; j < 3; j++) {
                            arrayKof[i][j] = (int) Math.pow(-1, (i + j)) * ((eleKof[KofIndex] * eleKof[KofIndex + 3]) - (eleKof[KofIndex + 1] * eleKof[KofIndex + 2]));
                            KofIndex += 4;

                        }

                    }

                    // Mentranspos matriks kofaktor untuk memperoleh matriks adjoin.
                    int[][] arrayAdj2 = Transpos(arrayKof);

                    // Mencari inversnya.
                    if (detInput == 1) {
                        
                        for (i = 0; i < baris; i++) {
                            for (j = 0; j < kolom; j++) {
                                arrayInv[i][j] = Integer.toString(arrayAdj[i][j]);
                            }
                        }

                        return arrayInv;

                    } else {

                        for (i = 0; i < baris; i++) {
                            for (j = 0; j < kolom; j++) {
                                
                                if (arrayAdj2[i][j] == 0) {
                                    arrayInv[i][j] = Integer.toString(arrayAdj2[i][j]);
                                } else {
                                    arrayInv[i][j] = div(arrayAdj2[i][j], detInput);
                                }
                                
                            }
    
                        }

                    }

                    return arrayInv;

                default :
                    System.err.println("Untuk saat ini hanya men-support matriks berordo 2x2 dan 3x3.");
                    return arrayInv;

            }

        } else {
            System.err.println("Matriks ini tidak dapat dicari inversnya!");

        }

        return arrayInv;

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
                break;
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
            if (abs1 == 1) {
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
