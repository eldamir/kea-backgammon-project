package model.board;

import java.util.ArrayList;

import model.dice.Dice;
import model.piece.Piece;
import model.player.Player;

public class Game {
	Player player1;
	Player player2;
	ArrayList<BoardState> boardStates; 
	Dice dice1;
	Dice dice2;
	
	public Game(){
		boardStates = new ArrayList<BoardState>();
		boardStates.add(new BoardState());
		
		player1 = new Player(false, null, new BoardRules());
		player2 = new Player(true, null, new BoardRules());
		
		dice1 = new Dice();
		dice2 = new Dice();
	}
	
	public void move(Piece piece, Dice dice){
		BoardState previousState = this.boardStates.get(this.boardStates.size() - 1);
		Move move = new Move(piece, dice.getValue());
		
		this.boardStates.add(new BoardState(previousState, move));
	}
	
}
