package model.board;

import java.util.ArrayList;
import java.util.List;

import model.dice.Dice;
import model.piece.Piece;
import model.player.Player;

public class Game
{
	
	Player player1;
	Player player2;
	Player activePlayer;
	BoardRules rules;
	ArrayList<BoardState> boardStates; 
	Dice dice1;
	Dice dice2;
	private List<Dice> currentDiceRolls;
	
	
	public Game()
	{
		rules = new BoardRules();
		boardStates = new ArrayList<BoardState>();
		boardStates.add(new BoardState());
		
		player1 = new Player(false, true);
		player2 = new Player(true, false);
		
		dice1 = new Dice();
		dice2 = new Dice();
		
	}
	
	public void move(Piece piece, int selectedSpike)
	{
		Move move = new Move(piece, currentDiceRolls, selectedSpike, boardStates.get(boardStates.size())); 
		if (move.isValidMove())
		{
			BoardState previousState = this.boardStates.get(this.boardStates.size() - 1); // Why the this? Are there any other boardState lists that could conflict?
			this.boardStates.add(new BoardState(previousState, move));

			// No time left: Smelly backward hack that removes a used Dice from list so it can't be reused TODO improve to decency
			int estValue = piece.isWhite() ? selectedSpike - piece.getBoardPlacement() : piece.getBoardPlacement() - selectedSpike;
			for (int i =0; i<currentDiceRolls.size(); i++)
			{
				if (currentDiceRolls.get(i).getValue() == estValue)
				{
					currentDiceRolls.remove(i);
					break;
				}
			}
			
			piece.setBoardPlacement(selectedSpike);
		}
	}
	
	/** Handles actions to take place at the beginning of a turn
	 */
	public void initiateTurn()
	{
		// TODO active player of turn
		currentDiceRolls = rollDices();
	}
	
	/** Rolls and returns a list of 2 dice. If both dice has identical value, 2 extra copies are added to list
	 * @return A list of rolled dice
	 */
	private List<Dice> rollDices()
	{
		Dice diceOne = new Dice();
		Dice diceTwo = new Dice();
		diceOne.roll();
		diceTwo.roll();
//		overlayController.setDice(diceOne, diceTwo);  // TODO figure out the best way to pass this to controller layer	
		List<Dice> rollOfDices = new ArrayList<Dice>();

		if (diceOne.getValue() != diceTwo.getValue())
		{
			rollOfDices.add(diceOne);
			rollOfDices.add(diceTwo);
		}
		else
		{
			rollOfDices.add(diceOne);
			rollOfDices.add(diceOne.copyOfDice());
			rollOfDices.add(diceTwo);
			rollOfDices.add(diceTwo.copyOfDice());
		}
		return rollOfDices;
	}
	
}
