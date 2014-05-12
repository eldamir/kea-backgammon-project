package model.player;


import model.board.BoardRules;;

/** Represents a player
 * @since 8/5
 *
 */
public class Player 
{
	private boolean human;
	private boolean white;


	public Player(boolean human, boolean white)
	{
		this.human = human;
		this.white = white;
	}
	
	
	public boolean isWhite() 
	{
		return white;
	}
	
	public boolean isHuman() 
	{
		return human;
	}
}
