package no.hvl.dat109.helper;

import java.util.List;

// Klasse med statiske hjelpemetoder for å regne ut poengsummen
public class Helper {
	
	public static int calculatePoints(int n, List<Integer> list) {
		int result = 0;
		for (int i : list) {
			if(i == n) {
				result += i;
			}
		}
		return n;
	}
}
