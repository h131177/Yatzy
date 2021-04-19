package no.hvl.dat109.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import no.hvl.dat109.model.Dice;

// Klasse med statiske hjelpemetoder for å regne ut poengsummen
public class Helper {
	
	//Metode som alltid blir kalt i Servlet, den finner riktig metode basert på hvilken rad det er
	public static void calculate(int row, List<Dice> list) {
		if(row <= 6 || row == 16) {
			calculatePoints(row, list);
		} else {
			//TODO
			switch(row) {
			case 9:
				calculatePoints(1, list);
				break;
			}
		}
	}
	
	public static int calculatePoints(int n, List<Dice> list) {
		int result = 0;
		for (Dice d : list) {
			if(d.getValue() == n) {
				result += d.getValue();
			}
			//Sjanse, alle tall legges sammen
			if(n == 16) {
				result += d.getValue();
			}
		}
		return result;
	}
	
	public static int checkThree(List<Dice> list) {
		int result = 0;
		int counter = 0;
		int mostfrequent = 6;
		
			for(int i = 1; i <= 6; i++) {
				for (Dice d:list) {
					if (d.getValue() == (6 - i)) {
					mostfrequent = d.getValue();
					counter ++;
					}
				}
			if (counter >= 3) {
				return (mostfrequent * 3); 
			}
			
		}
	
		return result;
	}
	
	//Sjekker både liten og stor straight
	public static int checkStraight(int n, List<Dice> list) {
		int result = 15;
		//Collections.sort(list, (d1, d2) -> Integer.compare(d1.getValue(), d1.getValue()));
		Collections.sort(list, (d1, d2) -> Integer.valueOf(d1.getValue()).compareTo(Integer.valueOf(d2.getValue())));
		int i = 1;
		if(n == 2) {
			result = 20;
			i++;
		}
		for (Dice d : list) {
			System.out.println(d.getValue());
			if(d.getValue() != i) {
				return 0;
			}
			i++;
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
		infoList.add("One pair");
		infoList.add("Two pairs");
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
