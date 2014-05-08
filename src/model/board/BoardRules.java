package model.board;

import java.util.ArrayList;
import java.util.List;

import Board.BoardState;
import model.piece.Piece;

/** Handles the rules of the board
 * @since 8/5
 *
 */
public class BoardRules 
{

	/** Handles the moving of a piece
	 * @param piece The game piece to move
	 * @param boardPlacement Place on board which to move the piece
	 * @return Whether the move is legal
	 */
	public boolean legalMove(Piece piece, BoardState boardState)
	{
		ArrayList<Piece> spikeState = boardState.getSpike(piece.getBoardPlacement());
		if (piece.getBoardPlacement() >= 0 && piece.getBoardPlacement() <27)
		{
			if (spikeState.isEmpty() 
				|| spikeState.get(0).isWhite() == piece.isWhite()
				|| spikeState.size() == 1)
			{
				return true;
			}
		}
		else 
		{
			return false;
		}
		
		
	}
	
	public List<Integer> legalMoves(Piece piece, int firstDiceRoll, int secondDiceRoll, List<Integer> rollOfDices, BoardState boardState)
	{
		ArrayList<Integer> legalPositions = new ArrayList<Integer>();
		for (Integer diceRoll: rollOfDices)
		{
			if (legalMove(piece, boardState))
			{
				legalPositions.add(piece.getBoardPlacement() + diceRoll);
			}
		}
		return legalPositions;
	}
	
}
