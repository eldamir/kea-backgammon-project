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
	private ArrayList[] board = new ArrayList[28];
	
	private static boolean turn = true;
	
	/**
	 * så man er nød til at kalde boardstate 8 gange
	 * før alle brikkerne er sat på deres start pladser
	 * @param spice
	 */
	public BoardState(ArrayList<Piece> spice)
	{
		if(spice.size() == 2 && (!spice.get(0).isWhite()))
		{
			board[0] = spice;
		}
		else if(spice.size() == 2 && spice.get(0).isWhite())
		{
			board[23] = spice;
		}
		else if(board[5]== null && spice.size() == 5 && spice.get(0).isWhite())
		{
			board[5] = spice;
		}
		else if(board[5] == null && spice.size() == 5 && (!spice.get(0).isWhite()))
		{
			board[18] = spice;
		}
		else if(spice.size() == 3 && spice.get(0).isWhite())
		{
		   board[7] = spice;	
		}
		else if(spice.size() == 3 && (!spice.get(0).isWhite()))
		{
		   board[16] = spice;	
		}
		else if(spice.size() == 5 && (!spice.get(0).isWhite()))
		{
			board[11] = spice;
		}
		else if(spice.size() == 5 && spice.get(0).isWhite())
		{
			board[12] = spice;
		}
	}
	
	/** 
	 * @param current boardState gets updated
	 * @param Move are used to get the piece which will be moved to a chosen spike
	 */
	public void updateBoardState(BoardState previousBoardState, Move newMove)
	{
		/**
		 * 
		 * så pos er en bestemt spice position
		 */
		int spikePos = newMove.getPosition();
		Piece p = newMove.getPiece();
		
		
		for(int i=0; i<=23; i++)
		{
		    
			 ArrayList<Piece> spike = previousBoardState.board[i];
			 for(int j=0; j<spike.size(); j++)
			 { 
				if(spike.get(j).equals(p))
				{
					spike.remove(spike.indexOf(spike.get(j)));
					
					previousBoardState.board[i] = spike;
					
					spike = previousBoardState.board[spikePos]; 
					
					spike.add(p);
					
					previousBoardState.board[spikePos] = spike;
					
					j = spike.size() - 1;
					
					i = 23; 
				}
			
			}
		 }
		
		BoardState = previousBoardState;
      }
	
	
	
	/**
	 * 
	 * @return current state of the board until updated
	 */
	public BoardState getBoardState()
	{
		return BoardState;
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
	
	public ArrayList<Piece> getSpike(int n)
	{
		ArrayList<Piece> spike = board[n];
		return spike;
	}

}
