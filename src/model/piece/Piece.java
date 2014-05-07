package model.piece;

public class Piece {

	private boolean white;
	private boolean home;

	public Piece(boolean colour)
	{
		this.white = colour;
		home = false;
		
	}

	public boolean isHome() {
		return home;
	}

	public void setHome(boolean home) {
		this.home = home;
	}

}
