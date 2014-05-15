package model.board;

import java.util.List;

import model.piece.Piece;
import model.board.BoardRules;
import model.dice.Dice;



public class Move 
{

	private Piece movedPiece;
	private int endPosition;
	private boolean validMove;
	
	
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


	public boolean isValidMove() {
		return validMove;
	}
}

