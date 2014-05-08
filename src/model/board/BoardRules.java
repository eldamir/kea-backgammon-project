package model.board;

import java.util.ArrayList;
import java.util.List;

import model.piece.Piece;

/** Handles the rules of the board
 * @since 8/5
 *
 */
public class BoardRules 
{

	
	/** Checks if a move of a game piece is legal
	 * @param piece The game piece in the move
	 * @param boardState The state of the board to check on
	 * @param diceRoll Dice roll used for this move
	 * @return Whether the move is legal
	 */
	public boolean isLegalMove(Piece piece, BoardState boardState, int diceRoll)
	{
		int boardPosToCheck = piece.getBoardPlacement() + diceRoll;
		ArrayList<Piece> spikeState = boardState.getSpike(boardPosToCheck); // So will boardState be an object or not?
		if (boardPosToCheck >= 0 && boardPosToCheck < 27)	// 27 = 24 spikes, 1 prison, 1 blackHome and 1 whiteHome
		{
			if (spikeState.isEmpty() 
				|| spikeState.get(0).isWhite() == piece.isWhite()
				|| spikeState.size() == 1)
			{
				return true;
			}
		}
		return false;
	}
	
	
	/** Returns a list of legal positions for the current piece
	 * @param piece The piece to check moves for
	 * @param rollOfDices A list of dice rolls
	 * @param boardState The state of the board to check on
	 * @return A list of legal positions for the piece
	 */
	public List<Integer> legalMoves(Piece piece, List<Integer> rollOfDices, BoardState boardState)
	{
		ArrayList<Integer> legalPositions = new ArrayList<Integer>();
		for (Integer diceRoll: rollOfDices)
		{
			if (isLegalMove(piece, boardState, diceRoll))
			{
				legalPositions.add(piece.getBoardPlacement() + diceRoll);
			}
		}
		return legalPositions;
	}
	
	
}
