import java.util.*;
import java.lang.Math;

/* Program Kalkulator Tanggal oleh AFP.
 * Dibuat pada tanggal 12/01/2023, ditulis menggunakan Java.
 */

public class DateCalculation {
	// Program utama.
	public static void main(String[] args) {
		String date1, date2;
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("Date Calculator");
		System.out.println("==========\n");
		
		System.out.print("> Start Date (yyyy/mm/dd) : ");
		date1 = userInput.next();
		System.out.print("> End Date  (yyyy/mm/dd) : ");
		date2= userInput.next();
		
		System.out.println("\nResults");
		System.out.println("======\n");
		
		System.out.println("> Start Date : ");
		formatDate(date1);
		System.out.println("> End Date  : ");
		formatDate(date2);
		
		int res = getDiff(date1, date2);
		System.out.printf("\nDifference : \n%,d days\n", res);
		getDetails(date1, date2, res);
		
		userInput.close();
		
		System.out.println("\nJump Function Test");
		System.out.println("===============\n");
		String test = jump(date2, res);
		System.out.printf("%s + %,d days = %s\n", date2, res, test);	
	}
	
	// Method untuk membuat array berisikan data tanggal.
	public static int[][] getArrDate(String date1, String date2) {	
		int[][] res = new int[2][3];
		String[] arrDate1 = date1.split("/");
		String[] arrDate2 = date2.split("/");
		int temp;
		
		res[0][0] = Integer.parseInt(arrDate1[0]);
		res[1][0] = Integer.parseInt(arrDate2[0]);
		
		if (res[0][0] <= res[1][0]) {
			temp = res[0][0];
			res[0][0] = res[1][0];
			res[1][0] = temp;
			
			res[0][1] = Integer.parseInt(arrDate2[1]);
			res[0][2] = Integer.parseInt(arrDate2[2]);
			res[1][1] = Integer.parseInt(arrDate1[1]);
			res[1][2] = Integer.parseInt(arrDate1[2]);	
		} else {
			res[0][1] = Integer.parseInt(arrDate1[1]);
			res[0][2] = Integer.parseInt(arrDate1[2]);
			res[1][1] = Integer.parseInt(arrDate2[1]);
			res[1][2] = Integer.parseInt(arrDate2[2]);	
		}
		
		return res;
	}
	
	// Method untuk menghitung perbedaan waktu.
	public static int getDiff (String date1, String date2) {	
		String yearStr1 = date1.substring(0, 4);
		String yearStr2 = date2.substring(0, 4);
		String tempStr = null;
		
		if (yearStr1.compareTo(yearStr2) < 0) {
			tempStr = date1;
			date1 = date2;
			date2 = tempStr;
		}
		
		int date[][] = getArrDate(date1, date2);
		int res = 0;
		int temp, i, leapCount;
		int y1 = date[0][0];
		int m1 = date[0][1];
		int d1 = date[0][2];
		int y2 = date[1][0];
		int m2 = date[1][1];
		int d2 = date[1][2];
		
		if ((m1 == 1) && (d1 == 1)) {
			m1 = 12;
			d1 = 31;
			y1--;
				
			String date3 = y1 + "/" + m1 + "/" + d1;
			return res = getDiff(date3, date2) + 1;		
		}
		
		if (y1 == y2) {
			if (m1 == m2) {
				if (d1 == d2) {
					return 0;
				} else {
					return res = Math.abs(d1 - d2);
				}
			} else {
				if (m1 < m2) {
					temp = m1;
					m1 = m2;
					m2 = temp;
					temp = d1;
					d1 = d2;
					d2 = temp;
				}
				
				for (i = m1 - 1; i > m2; i--) {
					res += day(i, y1);
				}
				
				res += (day(m2, y2) - d2) + d1;
			}
		} else {
			leapCount = 0;
			
			// Mencari jumlah tahun kabisat.
			for (i = y1 - 1; i > y2; i--) {
				if (i % 4 == 0) {
					leapCount += 1;
				}
			}
			
			// Mencari hari ekstra jika batas salah satu atau dua tahun
			// merupakan tahun kabisat juga.
			if (y1 % 4 == 0) {
				if (m1 >= 3) {
					leapCount += 1;
				}
			}
			
			if (y2 % 4 == 0) {
				if (m2 < 3) {
					leapCount += 1;
				}
			}
			
			String u_limit = Integer.toString(y1) + "/01/01";
			String b_limit = Integer.toString(y2) + "/12/31";
			
			// Mencari jaraknya.
			res = (y1 - y2 - 1) * 365 + getDiff(u_limit, date1) + getDiff(date2, b_limit) + leapCount + 1;	
		}
		
		return res;
	}
	
	// Method untuk mendapatkan jumlah hari seseuai bulannya.
	public static int day(int m, int y) {		
		switch (m) {
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				return 31;
			
			case 4: case 6: case 9: case 11:
				return 30;
			
			case 2 :
				if (y % 4 == 0) {
					return 29;
				} else {
					return 28;
				}
			
			default :
				System.err.println("Error!!!");				
		}
				
		return 0;
	}
	
	// Method untuk mengetahui nama hari.
	public static String getDay(String date) {	
		String std = "2023/01/01";
		int gap;
		
		if (std.equals(date)) {
			return "Sunday";
		} else {
			gap = getDiff(std, date) - 1;
		}
		
		switch (gap % 7) {
			case 0 : return "Sunday";
			case 1 : return "Monday";
			case 2 : return "Tuesday";
			case 3 : return "Wednesday";
			case 4 : return "Thursday";
			case 5 : return "Friday";
			case 6 : return "Saturday";
			default : return null;
		}	
	}
	
	// Method untuk lompat ke waktu tujuan dalam
	// satuan hari.
	public static String jump(String date, int length) {
		String[] arrDate = date.split("/");
		String res = null;
		int y = Integer.parseInt(arrDate[0]);
		int m = Integer.parseInt(arrDate[1]);
		int d = Integer.parseInt(arrDate[2]);
		int i;
		
		if (length == 0) {
			return date;
			
		} else if (length > 0) {
			if (d + length <= day(m, y)) {
				d += length;
				res = y + "/" + m + "/" + d;
				return res;
			} else {
				length -= (day(m, y) - d);
				
				i = m + 1;
				if (i == 13) {
					i = 1;
					y++;
				} else {
					m++;
				}
				
				while (length > day(i, y)) {
					if (i + 1 != 13) {
						length -= day(i, y);
						i++;
					} else {
						length -= day(i, y);
						i = 1;
						y++;
					}
				}
				
				d = length;	
				res = y + "/" + i + "/" + d;
				
				return res;										
			}
				
		} else {
			length = Math.abs(length);
			
			if (d - length >= 1) {
				d -= length;
				res = y + "/" + m + "/" + d;
				return res;
			} else {
				length -= d;
				d = 1;
				
				i = m - 1;
				if (i == 0) {
					i = 12;
					y--;
				} else {
					m--;
				}
				
				while (length > day(i, y)) {
					if (i - 1 != 0) {
						length -= day(i, y);
						i--;
					} else {
						length -= day(i, y);
						i = 12;
						y--;
					}
				}
				
				d = day(i, y) - length;	
				res = y + "/" + i + "/" + d;
				return res;		
			}	
		}
	}
	
	// Method untuk menampilkan tanggal lengkap dengan harinya.
	public static void formatDate(String date) {	
		String[] fragDate = date.split("/");
		String day = getDay(date);
		String[] mStr =
			{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Des"};
		
		int mInt = Integer.parseInt(fragDate[1]) - 1;
		
		System.out.printf("%s, %s %s %s\n", day, fragDate[2], mStr[mInt], fragDate[0]);	
	}
	
	// Method untuk menampilkan informasi lebih detail mengenai
	// perbedaan waktu yang didapatkan.
	public static void getDetails(String date1, String date2, int days) {	
		String[] fragStr1 = date1.split("/");
		String[] fragStr2 = date2.split("/");
		String tempStr;
		int leapCount, i;
		
		if (fragStr1[0].compareTo(fragStr2[0]) < 0) {
			tempStr = fragStr1[0];
			fragStr1[0] = fragStr2[0];
			fragStr2[0] = tempStr;
		}
		
		int y1 = Integer.parseInt(fragStr1[0]);
		int y2 = Integer.parseInt(fragStr2[0]);
		int m1 = Integer.parseInt(fragStr1[1]);
		int m2 = Integer.parseInt(fragStr2[1]);
		int d1 = Integer.parseInt(fragStr1[2]);
		int d2 = Integer.parseInt(fragStr2[2]);
		
		leapCount = 0;
			
		// Mencari jumlah tahun kabisat.
		for (i = y1 - 1; i > y2; i--) {
			if (i % 4 == 0) {
				leapCount += 1;
			}
		}
			
		// Mencari hari ekstra jika batas salah satu atau dua tahun
		// merupakan tahun kabisat juga.
		if (y1 % 4 == 0) {
			if (m1 >= 3) {
				leapCount += 1;
			}
		}
			
		if (y2 % 4 == 0) {
			if (m2 < 3) {
				leapCount += 1;
			}
		}
		
		int year, month, week;
		year = (int) days / 365;
		days %= 365;
		month = (int) days / 30;
		days %= 30;
		week = (int) days / 7;
		days = days% 7;
		
		System.out.println("Details :");
		if (year != 0) {
			System.out.print(year + " year(s) ");
		}
		if (month != 0) {
			System.out.print(month + " month(s) ");
		}
		if (week != 0) {
			System.out.print(week + " week(s) ");
		}
		if (days != 0) {
			System.out.print(days + " day(s)");
		}
		
		System.out.print("\n");
	}
}