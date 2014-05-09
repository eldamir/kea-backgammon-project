package model.player;

import java.util.ArrayList;
import java.util.List;

import controller.OverlayController;
import model.board.BoardRules;
import model.dice.Dice;
import model.piece.Piece;

/** Represents a player
 * @since 8/5
 *
 */
public class Player 
{
	private boolean human;
	private boolean white;
	private Dice dice1; 
	private Dice dice2; 
	private OverlayController overlayController;
	private int secondDiceRoll;
	private int firstDiceRoll; 
	private BoardRules boardRules;
	private List<Integer> rollOfDices;
	
	
	
	public Player(boolean human, OverlayController overlayController, BoardRules boardRules)
	{
		this.human = human;
		this.white = white;
		dice1 = new Dice(); 
		dice2 = new Dice();
		this.overlayController = overlayController; 
		this.boardRules = boardRules;
	}
	
	/** Method GUI calls to start a turn for a player
	 * 
	 */
	public void startTurn()
	{
		dice1.roll();
		dice2.roll();
		overlayController.setDice(dice1, dice2);
		rollOfDices = new ArrayList<Integer>();

		if (firstDiceRoll != secondDiceRoll)
		{
			rollOfDices.add(firstDiceRoll);
			rollOfDices.add(secondDiceRoll);
		}
		else
		{
			rollOfDices.add(firstDiceRoll);
			rollOfDices.add(firstDiceRoll);
			rollOfDices.add(secondDiceRoll);
			rollOfDices.add(secondDiceRoll);
		}
	}
	
	public void movePiece(Piece piece)
	{
//		Once BoardState is done, next line can be activated
//		boardRules.legalMoves(piece, rollOfDices, BoardState.getBoardState());
	}
}