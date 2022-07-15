// Nama package ngikut punyamu ya.
package <nama_package>; 
import java.util.*;

/* Program Konversi Bilangan oleh AFP.
 * Dibuat pada tanggal 07/12/2021, ditulis menggunakan Java.
 */

public class KonversiBilangan {
	
	// Program utama.
	public static void main(String[] args) {
		
		System.out.println("Konversi");
		System.out.println("========\n");
		
		// Method ConvertNum_Test digunakan untuk mengetes apakah
		// method ConvertNum berjalan dengan baik atau tidak.
		String a = "101011001101110101";
		String b = "531565";
		String c = "177013";
		String d = "2b375";
		
		ConvertNum_Test(a, b, c, d);
		
		String angka = null;
		int basis1 = 0;
		int basis2 = 0;
		Scanner masuk = new Scanner(System.in);
		
		try {
			
			System.out.print("> Bilangan \t: ");
			angka = masuk.next();
			
			System.out.print("> Basis Awal \t: ");
			basis1 = masuk.nextInt();
			
			System.out.print("> Basis Tujuan \t: ");
			basis2 = masuk.nextInt();
			
		} catch (java.util.InputMismatchException error) {
			System.err.println("Tolong masukin angka, jangan masukin macem-macem njer!");
		}
		
		System.out.print("\n=================");
		System.out.print("\n> Hasil \t: ");
		
		String konversi = ConvertNum(angka, basis1, basis2);
		System.out.println(konversi + "\n");
		
		masuk.close();
	
	}

	private static String ConvertNum(String bil, int base1, int base2) {
		
		// Bilangan basis-10 (desimal) bisa langsung diproses.
		// Sedangkan untuk bilangan basis non-10, bilangan tersebut harus dipreteli
		// satu per satu, kemudian dimasukkan ke dalam wadah temp, baru nantinya
		// bisa diproses.
		
		int i = 0;
		int pString = bil.length();
		long[] tempNum = new long[1000];
		long hasil = 0;
		String[] tempChar = new String[1000];
		String[] tempStr = new String[1000];
		String newStr = "";
		String newStr_2 = "";
		boolean konf1 = ((base1 == 2) || (base1 == 8) || (base1 == 10) || (base1 == 16));
		boolean konf2 = ((base2 == 2) || (base2 == 8) || (base2 == 10) || (base2 == 16));
		
		// Program ini hanya akan menerima angka 2/8/10/16 sebagai basis asal dan
		// basis tujuan.
		if (konf1 && konf2) {
			
			// Case asal = tujuan, langsung return input-nya.
			if (base1 == base2) {
				return bil;

			} else {
				
				switch (base1) {
				case 2 :
					
					switch (base2) {
					case 10 : // Case (2, 10)	
								
						for (i = 0; i < pString; i++) {
							
							tempChar[i] = bil.substring(i, i+1);
							tempNum[i] = Long.parseLong(tempChar[i]);
							
							if (tempNum[i] == 1) {
								hasil += (Math.pow(2, (pString - i - 1)));
							} else if (tempNum[i] > 1) {
								System.err.println("ERROR!");
								break;
							}
							
						}
						
						// Khusus untuk case (2, 16) karena nantinya konversi 2 -> 16,
						// dikonversi ke desimal dulu (2 -> 10 -> 16).
						switch ((int) hasil) {
						case 10 :
							newStr = newStr + "A";
							break;
						
						case 11 :
							newStr = newStr + "B";
							break;
							
						case 12 :
							newStr = newStr + "C";
							break;
						
						case 13 :
							newStr = newStr + "D";
							break;
						
				   		case 14 :
							newStr = newStr + "E";
							break;
						
						case 15 :
							newStr = newStr + "F";
							break;
						
						default :
							newStr = newStr + Long.toString(hasil);
							break;
						}

						return newStr;
					
					case 8 : // Case (2, 8), di akhir case nanti bakal memakai konversi 2 -> 10.
						
						int pString_8 = pString;
						int count_8 = pString_8 / 3;
						
						if (pString_8 % 3 != 0) {
							count_8 += 1;
						}
						
						for (i = 0; i < count_8; i++) {
							
							if (pString_8 >= 3) {
								
								tempStr[i] = bil.substring((pString_8 - 3), (pString_8));
								pString_8 -= 3;
								
							} else {
								tempStr[i] = bil.substring(0, (pString_8 % 3));
							}
							
						}
						
						for (i = count_8 - 1; i >= 0 ; i--) {
							newStr_2 = newStr_2 + ConvertNum(tempStr[i], 2, 10);
						}
						
						return newStr_2;
						
					case 16 : // Case (2, 16), di akhir case nanti bakal memakai konversi 2 -> 10.
					
						int pString_16 = pString;
						int count_16 = pString_16 / 4;
						
						if (pString_16 % 4 != 0) {
							count_16 += 1;
						}
						
						for (i = 0; i < count_16; i++) {
							
							if (pString_16 >= 4) {
								
								tempStr[i] = bil.substring((pString_16 - 4), (pString_16));
								pString_16 -= 4;
								
							} else {
								tempStr[i] = bil.substring(0, (pString_16 % 4));
							}
							
						}
						
						for (i = count_16 - 1; i >= 0 ; i--) {
							newStr_2 = newStr_2 + ConvertNum(tempStr[i], 2, 10);
						}
						
						return newStr_2;
						
					}
				
				case 10 : // Case (10, non-10), semua hanya dalam satu case.
					
					long temp_10 = Long.parseLong(bil);
					
					if ((base2 == 2) || (base2 == 8) || (base2 == 16)) {
						
						while (temp_10 > 0) {
							
							tempNum[i] = temp_10 % base2;
							temp_10 /= base2;
							i++;
							
						}
						
					} else {
						System.err.println("ERROR!");
					}
					
					// Case khusus untuk bilangan heksadesimal.
					for (int j = i - 1; j >= 0; j--) {
						
						switch ((int) tempNum[j]) {
						case 10 :
							newStr = newStr + ("A");
							continue;
							
						case 11 :
							newStr = newStr + ("B");
							continue;
								
						case 12 :
							newStr = newStr + ("C");
							continue;
							
						case 13 :
							newStr = newStr + ("D");
							continue;
					
						case 14 :
							newStr = newStr + ("E");
							continue;
							
						case 15 :
							newStr = newStr + ("F");
							continue;
							
						default :
							newStr = newStr + (tempNum[j]);
							continue;
							
						}
						
					}
					
					return newStr;
					
				case 8 :
					
					switch (base2) {
					case 10 : // Case (8, 10)
						
						for (i = 0; i < pString; i++) {
							tempChar[i] = bil.substring(i, i+1);
							tempNum[i] = Long.parseLong(tempChar[i]);
							
							if (tempNum[i] < 8) {
								hasil += (Math.pow(8, (pString - i - 1))) * tempNum[i];
							} else {
								System.err.println("ERROR!");
								break;
							}
						
						}
					
						return newStr = newStr + hasil;
					
					case 2 :  // Case (8, 2), konversinya secara tidak langsung (8 -> 10 -> 2).
						return newStr_2 = ConvertNum(ConvertNum(bil, 8, 10), 10, 2);
					
					case 16 : // Case (8, 16), konversinya secara tidak langsung (8 -> 10 -> 16).
						return newStr_2 = ConvertNum(ConvertNum(bil, 8, 10), 10, 16);
						
					}
					
				case 16 :
					
					// Karena input dari user bisa memuat huruf kecil maupun besar,
					// input-nya harus dibuat kapital semua.
					bil = bil.toUpperCase();
					
					switch (base2) {
					case 10 : // Case (16, 10).
						
						for (i = 0; i < pString; i++) {
							tempChar[i] = bil.substring(i, i+1);
							
							switch (tempChar[i]) {
							case "A" : 
								tempNum[i] = 10;
								break;
								
							case "B" : 
								tempNum[i] = 11;
								break;
								
							case "C" : 
								tempNum[i] = 12;
								break;
								
							case "D" : 
								tempNum[i] = 13;
								break;
								
							case "E" : 
								tempNum[i] = 14;
								break;
								
							case "F" : 
								tempNum[i] = 15;
								break;
								
							default :
								tempNum[i] = Long.parseLong(tempChar[i]);
							}
							
							if (tempNum[i] < 16) {
								hasil += (Math.pow(16, (pString - i - 1))) * tempNum[i];
							} else {
								System.err.println("ERROR!");
								break;
							}
						
						}
					
						return newStr = newStr + hasil;
					
					case 2 : // Case (16, 2), konversinya secara tidak langsung (16 -> 10 -> 2).
						return newStr_2 = ConvertNum(ConvertNum(bil, 16, 10), 10, 2);
					
					case 8 : // Case (16, 8), konversinya secara tidak langsung (16 -> 10 -> 8).
						return newStr_2 = ConvertNum(ConvertNum(bil, 16, 10), 10, 8);
						
					}
					
				}
				
			}
		
		} else {
			
			System.err.println("ERROR!");
			return newStr;
		}
		
		return null;
		
	}
	
	// Method khusus untuk mengecek apakah method ConvertNum berfungsi dengan baik.
	private static void ConvertNum_Test(String a, String b, String c, String d) {
		
		System.out.println("[Test Purpose Only]\n");
		
		String konversi = null;
		
		// Dari biner ke basis apa saja.
		konversi = ConvertNum(a, 2, 8);
		System.out.printf("%s [2] = %s [8]\n", a, konversi);
		konversi = ConvertNum(a, 2, 10);
		System.out.printf("%s [2] = %s [10]\n", a, konversi);
		konversi = ConvertNum(a, 2, 16);
		System.out.printf("%s [2] = %s [16]\n", a, konversi);
		System.out.print("\n");
		
		// Dari oktal ke basis apa saja.
		konversi = ConvertNum(b, 8, 2);
		System.out.printf("%s [8] = %s [2]\n", b, konversi);
		konversi = ConvertNum(b, 8, 10);
		System.out.printf("%s [8] = %s [10]\n", b, konversi);
		konversi = ConvertNum(b, 8, 16);
		System.out.printf("%s [8] = %s [16]\n", b, konversi);
		System.out.print("\n");
		
		// Dari desimal ke basis apa saja.
		konversi = ConvertNum(c, 10, 2);
		System.out.printf("%s [10] = %s [2]\n", c, konversi);
		konversi = ConvertNum(c, 10, 8);
		System.out.printf("%s [10] = %s [8]\n", c, konversi);
		konversi = ConvertNum(c, 10, 16);
		System.out.printf("%s [10] = %s [16]\n", c, konversi);
		System.out.print("\n");
		
		// Dari heksadesial ke basis apa saja.
		d = d.toUpperCase();
		konversi = ConvertNum(d, 16, 2);
		System.out.printf("%s [16] = %s [2]\n", d, konversi);
		konversi = ConvertNum(d, 16, 8);
		System.out.printf("%s [16] = %s [8]\n", d, konversi);
		konversi = ConvertNum(d, 16, 10);
		System.out.printf("%s [16] = %s [10]\n", d, konversi);
		System.out.print("\n");
		
	}

}
