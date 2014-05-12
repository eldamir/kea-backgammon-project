package model.board;

import java.util.ArrayList;
import java.util.List;

import controller.BoardController;
import model.dice.Dice;
import model.piece.Piece;
import model.player.Player;

/** Handles the rules of the board
 * @since 8/5
 *
 */
public class BoardRules 
{
	/** Checks if a move of a game piece is legal
	 * @param piece The game piece in the move
	 * @param boardState The state of the board to check on
	 * @param moveDistance Dice roll used for this move
	 * @return Whether the move is legal
	 */
	public static boolean isLegalMove(Piece piece, BoardState boardState, Dice diceRoll)
	{
		int	boardPosToCheck = piece.getBoardPlacement() + diceRoll.getValue();
		ArrayList<Piece> spikeState = boardState.getSpike(boardPosToCheck); // So will boardState be an object or not?
		if (boardPosToCheck >= BoardController.FIRST_SPIKE_NR && boardPosToCheck < BoardController.FIRST_SPIKE_NR)
		{
			if (
				spikeState.isEmpty() 
				|| spikeState.get(0).isWhite() == piece.isWhite()
				|| spikeState.size() == 1
				// TODO add gate for forward move only (in accordance to piece colour)
				)
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
	public static List<Integer> legalMoves(Piece piece, List<Dice> rollOfDices, BoardState boardState)
	{
		ArrayList<Integer> legalPositions = new ArrayList<Integer>();
		for (Dice diceRoll: rollOfDices)
		{
			if (isLegalMove(piece, boardState, diceRoll))
			{
				int diceValue = piece.isWhite() ? diceRoll.getValue() : diceRoll.getValue() * -1; // If the piece is black, the value of a move is made negative
				legalPositions.add(piece.getBoardPlacement() + diceValue);
			}
		}
		return legalPositions;
	}
	
	/* Is it overkill having this as a method? It is in fact a rule of the game that you
	 * can only move a piece of your own colour. */
	/**
	 * @param activePlayer Player whose turn it is
	 * @return Whether the piece belongs to the active player
	 */
	public static boolean isPieceOfActivePlayer(Player activePlayer, Piece piece)
	{
		return activePlayer.isWhite() == piece.isWhite();
	}
}
