/* Clash of Clans Fireball + Zapquake DMG Calculator
   by Hikarin
*/
import java.lang.Math;

public class Main {
	public static void main(String[] args) {
		// Fireball Calculator
		FireballCalc(3650, 2000, 0.29, 3, 9600);
		
		addSpace(2);
		// Zapquake Calculator
		Zapquake(600, 0.29, 5050);
	}
	
	// Method for calculating fireball total damage.
	private static void FireballCalc(int dmgFB, int dmgGA, double dmgE, int countE, double hp) {
		title("Fireball Calculator");
		double dmgTotal = dmgFB + eq(dmgE, countE, hp) + dmgGA;
		
		if (dmgTotal >= hp) {
			System.out.printf("Can be destroyed. (DMG = %,.0f)", dmgTotal);
		} else {
			System.out.printf("Can not be destroyed. (DMG = %,.0f)", dmgTotal);
		}
	}
	
	// Earthquake formula for any buildings excluding walls.
	private static double eq(double p, int count, double hp) {
		double dmg = 0f;
		
		for (int i = 1; i <= count; i++){
			dmg += (((double) 1/(2*i - 1)) * p) * hp;
		}
		return dmg;
	}
	
	// Earthquake formula exclusive for walls.
	private static double eqWall(double p, int count, double hp) {
		double dmg = 0f; double exDmg = 0f; double dmgTotal = 0f;
		
		for (int i = 1; i <= count; i++){
			dmg = (double) 1/i * p * hp;
			exDmg = (double) (5 * Math.pow((i-1), 2)) / 100 * hp;
			dmgTotal += dmg + exDmg;
		}
		return dmgTotal;
	}
	
	// Method for showing the result from zapquake calculator.
	private static void Zapquake(int dmgL, double dmgE, int hp) {
		title("Zapquake Calculator");
		int[] spells = zapQ(dmgL, dmgE, hp);
		double totalDmg = (spells[0] * dmgL) + eq(dmgE, spells[1], hp);
		
		System.out.printf("Recommended -> %dL + %dE ", spells[0], spells[1]);
		System.out.printf("(DMG = %,.0f)", totalDmg);
	}
	
	// Method for calculating the least amount of zapquake spells.
	private static int[] zapQ(int light, double pEQ, int hp) {
		int[] finalCombo = new int[2];
		int[] zapOnly = new int[2];
		int n = 1; int i; int countL = 0; int countE = 0;
		double dmgL = light; double dmgE = 0f;
		double hpTemp = 0f;
		
		countL = (int) (hp / (double) dmgL);
		if ((countL * dmgL) < hp) countL++;
		zapOnly[0] = countL; zapOnly[1] = 0;
		
		do {
			dmgE = (double) 1/n * pEQ * hp;
			if (dmgE <= dmgL) break;
			else n++;
		} while (dmgE > dmgL);
		
		int[][] combo = new int [n][2];
		for (i = 1; i <= n; i++) {
			hpTemp = hp;
			countE = i;
			hpTemp -= eq(pEQ, countE, hpTemp);
			countL = (int) (hpTemp / (double)dmgL);
			if ((countL * dmgL) < hpTemp) countL++;
			
			combo[i-1][0] = countL;
			combo[i-1][1] = countE;
		}
		
		int min = 0;
		i = 0;
		boolean compareQty;
		boolean compareDmg;
		while (i < n-1) {
			compareQty = combo[i+1][0] + combo[i+1][1] <= combo[min][0] + combo[min][1];
			compareDmg = (combo[i+1][0] * dmgL) + eq(pEQ, combo[i+1][1], hp) < (combo[min][0] * dmgL) + eq(pEQ, combo[min][1], hp);
			if (compareQty && compareDmg) min = i + 1;
			i++;
		}
		
		if (zapOnly[0] < (combo[min][0] + combo[min][1])) finalCombo = zapOnly;
		else finalCombo = combo[min];
		
		return finalCombo;
	}
	
	// Optional: Method for making a title.
	private static void title(String text) {
		int len = text.length();
		
		System.out.println(text);
		for (int i = 0; i < len; i++) System.out.print("=");
		System.out.print("\n");
	}
	
	// Optional: Method for adding some space.
	private static void addSpace(int x) {
		for (int i = 0; i < x; i++) System.out.print("\n");
	}
}