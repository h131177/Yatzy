package no.hvl.dat109.model;

// Klassen brukes i Servleten for å holde styr på framgangen i spillet
public class Position {
	
	private int row;
	private int player;
	
	public Position(int row, int player) {
		this.row = row;
		this.player = player;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}
}
