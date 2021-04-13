package no.hvl.dat109.helper;

import java.util.ArrayList;
import java.util.List;

import no.hvl.dat109.model.Dice;

// Klasse med statiske hjelpemetoder for å regne ut poengsummen
public class Helper {
	
	public static int calculatePoints(int n, List<Dice> list) {
		int result = 0;
		for (Dice d : list) {
			if(d.getValue() == n) {
				result += d.getValue();
			}
		}
		return n;
	}
	
	public static List<String> getInfo() {
		List<String> infoList = new ArrayList<>();
		infoList.add("Ones");
		infoList.add("Twos");
		infoList.add("Threes");
		infoList.add("Fours");
		infoList.add("Fives");
		infoList.add("Sixes");
		infoList.add("Sum");
		return infoList;
	}
}
