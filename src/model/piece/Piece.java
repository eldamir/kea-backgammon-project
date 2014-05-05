package model.piece;

public class Piece {

	private boolean colour;
	private boolean home;

	public Piece(boolean colour)
	{
		this.colour = colour;
		home = false;
	}

	public boolean isHome() {
		return home;
	}

	public void setHome(boolean home) {
		this.home = home;
	}

}
