package model.board;
import java.util.ArrayList;
import model.piece.Piece;


/**
 * @author Rami Muhammedbrhan and Max Jensen
 * @version 1.0
 * @since 2014-05-08
 */
public class BoardState 
{
	private static BoardState BoardState;
	/**
	 * this is an arraylist that contains the state of the board
	 * 
	 * the index 0 is reserved for the white home position
	 * and the rest of the fields get the rest of the 
	 * indexes counter-clockwise. 
	 * 
	 * the field in the middle is separated in two fields one for the user and
	 * the other for the computer.
	 */
	private static ArrayList<ArrayList<Piece>> boardState = new ArrayList<ArrayList<Piece>>(29);
	
	private static boolean turn = true;
	
	public BoardState(ArrayList<ArrayList<Piece>> Boardstate)
	{
		boardState = Boardstate;
	}
	
	public BoardState(BoardState previous, Move newMove)
	{
		int pos = newMove.getPosition();
		Piece p = newMove.getPiece();
		BoardRules rules = new BoardRules();
		
		if(rules.isLegalMove){
		for(int i=0; i<boardState.size(); i++)
		{
			ArrayList<Piece> spike = boardState.get(i);
			for(int j=0; j<spike.size(); j++)
			{
				if(spike.get(j).equals(p))
				{
					spike.set(pos, p);
					j = spike.size() - 1;
				}
			}
			boardState.set(boardState.indexOf(spike), spike);
		}
		}
	}
	
	/**
	 * 
	 * @return current state of the board until updated
	 */
	public static ArrayList<ArrayList<Piece>> getBoardState()
	{
		return boardState;
	}
	
	/**
	 * 
	 * @param board gets updated so the current board state changes
	 */
	public static void updateBoardState(ArrayList<ArrayList<Piece>> boardstate)
	{
		boardState = boardstate;
	}
	
	/**
	 * 
	 * @param Turn
	 * @return if true then user is playing, if false computer is playing
	 */
	public static boolean shiftTurn(boolean Turn)
	{
		turn = Turn;
		return turn;
	}
	
	public static ArrayList<Piece> getSpike(int n)
	{
		ArrayList<Piece> spike = boardState.get(n);
		return spike;
	}

}
