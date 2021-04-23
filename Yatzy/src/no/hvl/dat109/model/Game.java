package no.hvl.dat109.model;

import java.util.ArrayList;
import java.util.List;

// Klasse for å lagre opplysninger om spillet
// Tenker i utgangspunktet å ha terningene i Servleten
public class Game {
	// Liste med spillere som er med i spillet
	private List<Player> players;
	// Liste med lister med poeng for alle spillere i hver runde (Kunne muligens brukt todimensjonal tabell)
	private List<List<Integer>> points;
	private Player host;
	private boolean started;
	private boolean finished;
	
	public Game(List<Player> players, List<List<Integer>> points) {
		this.players = players;
		this.points = points;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<List<Integer>> getPoints() {
		return points;
	}
	public void setPoints(List<List<Integer>> points) {
		this.points = points;
	}
	public Player getHost() {
		return host;
	}

	public void setHost(Player host) {
		this.host = host;
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public void addPlayer(Player player) {
		players.add(player);
	}
	//Legger til poeng for alle spillere den runden
	public void addPoints(int row, List<Integer> list) {
		List<Integer> numbersList = new ArrayList<>();
		int j = 0;
		for (Integer i : list) {
			numbersList.add(list.get(j));
			j++;
		}
		points.set(row, numbersList);
	}
	
	public List<Integer> getList(int row) {
		return points.get(row);
	}
}
