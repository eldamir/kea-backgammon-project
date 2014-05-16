package model.piece;

import controller.BoardController;


/** represents a piece and all needed data a piece contains, along with varios methods
 * 
 *
 */
public class Piece {

	private boolean white;
	private boolean home;
	private int boardPlacement;
	private BoardController boardController;

	public Piece(boolean colour, int boardPlacement)
	{
		this.boardPlacement = boardPlacement;
		this.white = colour;
		home = false;
	}

	public boolean isHome() {
		return home;
	}

	public void setHome(boolean home) {
		this.home = home;
	}

	public boolean isWhite() {
		return white;
	}

	public int getBoardPlacement() {
		return boardPlacement;
	}

	public void setBoardPlacement(int boardPlacement) {
		this.boardPlacement = boardPlacement;
	}
	

}
