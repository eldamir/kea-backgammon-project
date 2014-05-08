package model.piece;

import controller.BoardController;

public class Piece {

	private boolean white;
	private boolean home;
	private BoardController boardController;
	

	public Piece(boolean colour)
	{
		home = false;
	}

	public boolean isHome() {
		return home;
	}

	public void setHome(boolean home) {
		this.home = home;
	}

}
