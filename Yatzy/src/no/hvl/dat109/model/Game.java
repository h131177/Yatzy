package no.hvl.dat109.model;

import java.util.List;

public class Game {
	private List<Player> players;
	private List<List<Integer>> points;
	
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
	
}
