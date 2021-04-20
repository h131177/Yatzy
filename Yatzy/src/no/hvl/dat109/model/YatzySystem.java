package no.hvl.dat109.model;

import java.util.List;

// Klasse som holder styr på alle registrerte brukere og spill
// Foreløpig ikkje i bruk
public class YatzySystem {
	
	private List<Player> players;
	private List<Game> games;
	
	public YatzySystem(List<Player> players, List<Game> games) {
		this.players = players;
		this.games = games;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
}
