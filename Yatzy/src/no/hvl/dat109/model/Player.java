package no.hvl.dat109.model;

public class Player {
	private String username;
	private String password;
	private String name;
	private int points;
	
	public Player(String username, String password, String name, int points) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.points = points;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
}
