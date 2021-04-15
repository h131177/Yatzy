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
		return result;
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
		infoList.add("Bonus");
		infoList.add("Three of a kind");
		infoList.add("Four of a kind");
		infoList.add("Full house");
		infoList.add("Small straight");
		infoList.add("Large straight");
		infoList.add("Chance");
		infoList.add("YATZY");
		infoList.add("TOTAL SCORE");
		return infoList;
	}
}
