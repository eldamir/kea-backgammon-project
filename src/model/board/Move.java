package model.board;

import java.util.List;

import model.piece.Piece;
import model.board.BoardRules;
import model.dice.Dice;


/**
 * 
 * Class that represents a single move of a piece on the game board
 *
 */
public class Move 
{

	private Piece movedPiece;
	private int endPosition;
	private boolean validMove;
	
	/**
	 * Checks if the selected end position of the piece is a valid move 
	 * according to the possible moves given from the rolled die values.
	 * Sets a new end position for the piece if the move is valid.
	 * 
	 * @param piece The piece to be moved
	 * @param availableDiceValues The die values from the rolled die
	 * @param selectedEndPosition The board position where the dice is to be moved
	 * @param boardState The state of the board prior to the move
	 */
	public Move(Piece piece, List<Dice> availableDiceValues, int selectedEndPosition, BoardState boardState)
	{
		movedPiece = piece;
		validMove = false;
		endPosition = piece.getBoardPlacement();
		List<Integer> availablePositions = BoardRules.legalMoves(piece, availableDiceValues, boardState);
		if (availablePositions.contains(selectedEndPosition))
		{
			validMove = true;
			endPosition = selectedEndPosition;
		}	
	}
	
	
	public int getPosition()
	{
		return endPosition;
	}
	
	public Piece getPiece()
	{
		return movedPiece;
	}

	/**
	 * Returns the value of the validMove field
	 *  
	 * @return True if the move is valid, otherwise false
	 */
	public boolean isValidMove() {
		return validMove;
	}
}

