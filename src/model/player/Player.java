package model.player;

import controller.OverlayController;
import model.dice.Dice;
import model.piece.Piece;

/** Represents a player
 * @since 8/5
 *
 */
public class Player 
{
	boolean human;
	boolean white;
	Dice dice1; 
	Dice dice2; 
	OverlayController overlayController;
	private int secondDiceRoll;
	private int firstDiceRoll; 
	
	
	
	public Player(boolean human, OverlayController overlayController)
	{
		this.human = human;
		this.white = white;
		dice1 = new Dice(); 
		dice2 = new Dice();
		this.overlayController = overlayController; 
	}
	
	/** Method GUI calls to start a turn for a player
	 * 
	 */
	public void startTurn()
	{
		firstDiceRoll = dice1.roll();
		secondDiceRoll = dice2.roll();
		overlayController.setDice(firstDiceRoll, secondDiceRoll);
	}
	
	public void movePiece(Piece piece)
	{
		
	}
}
