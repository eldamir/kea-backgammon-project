package model.board;
import java.util.ArrayList;
import model.piece.Piece;


/**
 * @version 1.1
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
	private ArrayList[] board = new ArrayList[28];
	
	private static boolean turn = true;
	
	/**
	 * Initialize default state
	 */
	public BoardState()
	{
		for (int i = 0; i < this.board.length; i++) {
			this.board[i] = new ArrayList<Piece>();
		}
		
		for(int i = 1; i <= 2; i++){
			this.board[1].add(new Piece(false, 1));
		}
		
		for(int i = 1; i <= 5; i++){
			this.board[6].add(new Piece(true, 6));
		}
		
		for(int i = 1; i <= 3; i++){
			this.board[8].add(new Piece(true, 8));
		}
		
		for(int i = 1; i <= 5; i++){
			this.board[12].add(new Piece(false, 12));
		}
		
		for(int i = 1; i <= 5; i++){
			this.board[13].add(new Piece(true, 13));
		}
		
		for(int i = 1; i <= 3; i++){
			this.board[17].add(new Piece(false, 17));
		}
		
		for(int i = 1; i <= 5; i++){
			this.board[19].add(new Piece(false, 19));
		}
		
		for(int i = 1; i <= 2; i++){
			this.board[24].add(new Piece(true, 24));
		}
		
//		if(spike.size() == 2 && (!spike.get(0).isWhite()))
//		{
//			board[0] = spike;
//		}
//		else if(spike.size() == 2 && spike.get(0).isWhite())
//		{
//			board[23] = spike;
//		}
//		else if(board[5]== null && spike.size() == 5 && spike.get(0).isWhite())
//		{
//			board[5] = spike;
//		}
//		else if(board[18] == null && spike.size() == 5 && (!spike.get(0).isWhite()))
//		{
//			board[18] = spike;
//		}
//		else if(spike.size() == 3 && spike.get(0).isWhite())
//		{
//		   board[7] = spike;	
//		}
//		else if(spike.size() == 3 && (!spike.get(0).isWhite()))
//		{
//		   board[16] = spike;	
//		}
//		else if(spike.size() == 5 && (!spike.get(0).isWhite()))
//		{
//			board[11] = spike;
//		}
//		else if(spike.size() == 5 && spike.get(0).isWhite())
//		{
//			board[12] = spike;
//		}
	}
	
	/** 
	 * @param current boardState gets updated
	 * @param Move are used to get the piece which will be moved to a chosen spike
	 */
	public BoardState(BoardState previousBoardState, Move newMove)
	{
		/**
		 * 
		 * så pos er en bestemt spice position
		 */
		int endSpikePos = newMove.getPosition();
		Piece p = newMove.getPiece();
		int currentSpikePos = p.getBoardPlacement();
		
		
		for(int i=1; i<=24; i++)
		{
		    
			 ArrayList<Piece> spike = previousBoardState.board[i];
			 board[i].addAll(spike);
		}
		
		for(int i=0; i<board[currentSpikePos].size(); i++)
		{
			if(board[currentSpikePos].get(i).equals(p))
			{
				board[currentSpikePos].remove(i);
				board[endSpikePos].add(p);
				
				p.setBoardPlacement(endSpikePos);
			}
		}
		
		
		
      }
	
	
	
	/**
	 * 
	 * @return current state of the board until updated
	 */
	public ArrayList[] getBoard()
	{
		return this.board;
	}
	
	/**
	 * 
	 * @param Turn
	 * @return if true then user is playing, if false computer is playing
	 */
	public void shiftTurn()
	{
		this.turn = !turn;
	}
	
	public ArrayList<Piece> getSpike(int n)
	{
		ArrayList<Piece> spike = board[n];
		return spike;
	}

}
