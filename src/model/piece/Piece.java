package model.piece;

import controller.BoardController;


/** represents a piece and all needed data a piece contains, along with various methods
 * 
 *
 */
public class Piece {

	private boolean white;
	private boolean home;
	private int boardPlacement;
	private BoardController boardController;

	/**
	 * Creates a piece of a given colour and board position
	 * 
	 * @param colour The colour of the piece
	 * @param boardPlacement The board position of the piece
	 */
	public Piece(boolean colour, int boardPlacement)
	{
		this.boardPlacement = boardPlacement;
		this.white = colour;
		home = false;
	}

	/**
	 * 
	 * @return True if piece's position is in the home position, otherwise false
	 */
	public boolean isHome() {
		return home;
	}

	/**
	 * Sets the piece to the home position on the board
	 * 
	 * @param home True if piece is in the home position
	 */
	public void setHome(boolean home) {
		this.home = home;
	}

	/**
	 * 
	 * @return True if player plays the white pieces, otherwise false
	 */
	public boolean isWhite() {
		return white;
	}
	
	/**
	 * Gets the board position of the piece
	 * 
	 * @return Board position of the piece
	 */
	public int getBoardPlacement() {
		return boardPlacement;
	}

	/**
	 * Sets the board position of the piece
	 * 
	 * @param boardPlacement The board position
	 */
	public void setBoardPlacement(int boardPlacement) {
		this.boardPlacement = boardPlacement;
	}
	

}
