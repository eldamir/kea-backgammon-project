package model.board;

import java.util.ArrayList;

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
	public static boolean legalMove(Piece piece, int boardPlacement, BoardState boardState)
	{
		ArrayList<Piece> spikeState = boardState.getSpike(boardPlacement);
		if (boardPlacement >= 0 && boardPlacement <27)
		{
			if (spikeState.isEmpty() 
				|| spikeState.get(0).isWhite() == piece.isWhite())
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

}
