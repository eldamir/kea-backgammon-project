package model.board;

import model.piece.Piece;


public class Move 
{

	private Piece movedPiece;
	private int endPos;
	
	public Move(Piece piece, int diceRoll)
	{
		endPos = endPos + diceRoll;
		movedPiece = piece;
	}
	
	public int getPosition()
	{
		return endPos;
	}
	
	public Piece getPiece()
	{
		return movedPiece;
	}
}

