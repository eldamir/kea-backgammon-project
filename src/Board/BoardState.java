package Board;
import java.util.ArrayList;

import model.piece.Piece;


/**
 * @author Rami Muhammedbrhan and Max Jensen
 * @version 1.0
 * @since 2014-05-08
 */
public class BoardState 
{
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
	
			
	
	
	

}
