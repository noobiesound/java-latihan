/* Date Calculator by AFP.
 * Made on Dec 21st, 2025, written in Java.
 */
import java.lang.Math;

public class DateCalcNew {
	// Main method (for demo).
	public static void main(String[] args) {
		// A. Calculating the gap of two dates.
		title("Gap Between Two Dates");
		String date1 = "23/10/2018";
		formatDate(date1);
		String date2 = "21/12/2025";
		formatDate(date2);
		
		int gap = getGap(date1, date2);
		addSpace(1);
		System.out.printf("Gap = %,d day(s)\n", gap);
		detailedInfo(gap);
		addSpace(4);
		
		// B. Jumping into a new date.
		title("Jump Forward/Backward");
		String date3 = "15/06/2002";
		formatDate(date3);
		int jump = 8590;
		System.out.printf("Jump = %,d day(s)\n", jump);
		String date4 = dateJump(date3, jump);
		formatDate(date4);
	}
	
	// Method for checking whether it's a leap year or not.
	private static boolean leapConf(int year) {
		boolean conf = false;
		if (year % 400 == 0) conf = true;
		else if (year % 100 != 0) {
			if (year % 4 == 0) conf = true;
		}
		return conf;
	}
	
	// Method for getting the number of days.
	private static int getMaxDays(int month, int year) {
		switch (month) {
			case 1: case 3: case 5: case 7: case 8: case 10: case 12: return 31;
			case 4: case 6: case 9: case 11: return 30;
			default: 
				if (leapConf(year)) return 29;
				else return 28;
		}
	}
	
	// Method for calculating sum of days in a certain range.
	private static int getDaySum(int m1, int m2, int y) {
		int res = 0;
		if (m1 == m2) return getMaxDays(m1, y);
		else {
			for (int i = m1; i <= m2; i++) res += getMaxDays(i, y);
			return res;
		}
	}
	
	// Method for parsing the strings.
	private static int[] parseDate(String date) {
		int i;
		int[] res = new int[3];
		String[] dateStr = date.split("/");
		
		for (i = 0; i < 3; i++) {
			res[i] = Integer.parseInt(dateStr[i]);
		}
		return res;
	}
	
	// Method for calculating the gap, given two dates.
	private static int getGap(String date1, String date2) {
		int[] parsedDate1 = parseDate(date1);
		int[] parsedDate2 = parseDate(date2);
		if (parsedDate1[2] > parsedDate2[2]) {
			int[] temp = new int[3];
			temp = parsedDate1;
			parsedDate1 = parsedDate2;
			parsedDate2 = temp;
		}
		int d1 = parsedDate1[0]; int d2 = parsedDate2[0];
		int m1 = parsedDate1[1]; int m2 = parsedDate2[1];
		int y1 = parsedDate1[2]; int y2 = parsedDate2[2];
		int t1 = 0; int t2 = 0; int t3 = 0; int res;
		int i;
		
		if (y1 == y2) {
			if (m1 == m2) return res = Math.abs(d2 - d1);
			else if (m2 - m1 == 1) return res = (getMaxDays(m1, y1) - d1) + d2;
			else {
				res = (getMaxDays(m1, y1) - d1) + getDaySum(m1 + 1, m2 - 1, y1) + d2;
				return res;
			}
		} else {
			t1 = (getMaxDays(m1, y1) - d1) + getDaySum(m1 + 1, 12, y1);
			
			for (i = y1 + 1; i < y2; i++) {
				if (leapConf(i)) t2 += 366;
				else t2 += 365;
			}
			
			t3 = d2 + getDaySum(1, m2 - 1, y2);
		}

		res = t1 + t2 + t3;
		return res;
	}
	
	// Method for finding determining the day.
	private static String getDay(String date) {
		String seed = "1/1/2023";
		String[] seedStr = seed.split("/");
		int ySeed = Integer.parseInt(seedStr[2]);
		String[] dateStr = date.split("/");
		int yDate = Integer.parseInt(dateStr[2]);
		int gap = getGap(seed, date);
		
		if (ySeed > yDate) {
			switch (gap % 7) {
				case 0: return "Sunday";
				case 1: return "Saturday";
				case 2: return "Friday";
				case 3: return "Thursday";
				case 4: return "Wednesday";
				case 5: return "Tuesday";
				case 6: return "Monday";
				default: return null;
			}
		} else {
			switch (gap % 7) {
				case 0: return "Sunday";
				case 1: return "Monday";
				case 2: return "Tuesday";
				case 3: return "Wednesday";
				case 4: return "Thursday";
				case 5: return "Friday";
				case 6: return "Saturday";
				default: return null;
			}
		}
	}
	
	// Method for getting more detailed info.
	private static void detailedInfo (int days) {
		int y; int m; int w; int d;
		y = days / 365;
		m = (days - (y * 365)) / 30;
		w = (days - (y * 365) - (m * 30)) / 7;
		d = (days - (y * 365) - (m * 30) - (w * 7));
		
		if (y != 0) System.out.printf("%d year(s) ", y);
		if (m != 0) System.out.printf("%d month(s) ", m);
		if (w != 0) System.out.printf("%d week(s) ", w);
		if (d != 0) System.out.printf("%d day(s)", d);
	}
	
	// Method for formatting a date.
	private static void formatDate(String date) {
		String[] dateStr = date.split("/");
		int d = Integer.parseInt(dateStr[0]);
		int m = Integer.parseInt(dateStr[1]);
		int y = Integer.parseInt(dateStr[2]);
		
		String day = getDay(date);
		if ((d == 1) || (d == 21) || (d == 31)) {
			dateStr[0] = dateStr[0].concat("st");
		} else if ((d == 2) || (d == 22)) {
			dateStr[0] = dateStr[0].concat("nd");
		} else if ((d == 3) || (d == 23)) {
			dateStr[0] = dateStr[0].concat("rd");
		} else dateStr[0] = dateStr[0].concat("th");
		
		switch (m) {
			case 1: dateStr[1] = "January"; break;
			case 2: dateStr[1] = "February"; break;
			case 3: dateStr[1] = "March"; break;
			case 4: dateStr[1] = "April"; break;
			case 5: dateStr[1] = "May"; break;
			case 6: dateStr[1] = "June"; break;
			case 7: dateStr[1] = "July"; break;
			case 8: dateStr[1] = "August"; break;
			case 9: dateStr[1] = "September"; break;
			case 10: dateStr[1] = "October"; break;
			case 11: dateStr[1] = "November"; break;
			case 12: dateStr[1] = "December"; break;
		}
		
		System.out.printf("%s, %s %s, %d\n", day, dateStr[1], dateStr[0], y);
	}
	
	// Method for jumping into a new date.
	private static String dateJump(String date, int days) {
		if (days == 0) return date;
		
		int[] parsedDate = parseDate(date);
		int d1 = parsedDate[0]; int d2 = d1;
		int m1 = parsedDate[1]; int m2 = m1;
		int y1 = parsedDate[2]; int y2 = y1;
		String newDate = null;
		boolean forward = true;
		
		if (days < 0) {
			days = Math.abs(days);
			forward = false;
		}
		
		if (forward) {
			if (days <= (getMaxDays(m1, y1) - d1)) d2 = d1 + days;
			else {
				days -= getMaxDays(m2, y2) - d2;
				d2 = 1;
				if ((m2 + 1) > 12) {
					y2++;
					m2 = 1;
				} else m2++;
				
				while (days > getMaxDays(m2, y2)) {
					days -= getMaxDays(m2, y2);
					if ((m2 + 1) > 12) {
						y2++;
						m2 = 1;
					} else m2++;
				}
				
				d2 = days;
			}
		} else {
			if (days < d1) d2 = d1 - days;
			else {
				days -= d2;
				
				if ((m2 - 1) < 1) {
					y2--;
					m2 = 12;
				} else m2--;
				d2 = getMaxDays(m2, y2);
				
				while (days >= getMaxDays(m2, y2)) {
					days -= getMaxDays(m2, y2);
					
					if ((m2 - 1) < 1) {
						y2--;
						m2 = 12;
					} else m2--;
				}
				
				d2 = getMaxDays(m2, y2) - days;
			}
		}
		
		newDate = d2 + "/" + m2 + "/" + y2;
		return newDate;
	}
	
	// Optional: Method for making a title.
	private static void title(String text) {
		int len = text.length();
		
		System.out.println(text);
		for (int i = 0; i < len; i++) {
			System.out.print("=");
		}
		System.out.print("\n");
	}
	
	// Optional: Method for adding some space.
	private static void addSpace(int x) {
		for (int i = 0; i < x; i++) System.out.print("\n");
	}
}
