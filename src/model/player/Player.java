package model.player;

import model.board.BoardRules;

/**
 * Represents a backgammon player
 * @since 8/5 - 2014
 *
 */
public class Player 
{
	private boolean human;
	private boolean white;


	/**
	 * constructs a player 
	 * 
	 * @param human True if the player is human, otherwise false
	 * @param white True if the player plays the white pieces, otherwise false
	 */
	public Player(boolean human, boolean white)
	{
		this.human = human;
		this.white = white;
	}
	
	/**
	 * 
	 * @return True if player plays the white pieces, otherwise false
	 */
	public boolean isWhite() 
	{
		return white;
	}
	
	/**
	 * 
	 * @return True if player is human, otherwise false
	 */
	public boolean isHuman() 
	{
		return human;
	}
}
