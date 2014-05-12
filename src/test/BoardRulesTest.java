package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import model.board.BoardRules;
import model.board.BoardState;
import model.dice.Dice;
import model.piece.Piece;

import org.junit.Before;
import org.junit.Test;

// 8/5 BoardState unable to get a board state at this point, so tests will fail until then
public class BoardRulesTest
{

	BoardState boardState;
	BoardRules boardRules;
	
	@Before
	public void setUp() throws Exception
	{
		boardRules = new BoardRules();
	}

	
	// isLegalMove
	@Test
	public void testIsLegalMove_Bounds()
	{
		BoardState emptyBoard = new BoardState(); // To fix: Mimic instantiating until boardState is done
		Piece piecePos20 = new Piece(true, 20);
		assertTrue(boardRules.isLegalMove(piecePos20, emptyBoard, new Dice(3)));
		assertFalse(boardRules.isLegalMove(piecePos20, emptyBoard, new Dice(10)));
	}
	
	@Test
	public void testIsLegalMove_ColoursWhiteOntoBlack()
	{
		Piece whitePiecePos10 = new Piece(true, 10);
		
		BoardState loneBlackAtPos14Board = new BoardState(); // :To fix 
		assertTrue(boardRules.isLegalMove(whitePiecePos10, loneBlackAtPos14Board, new Dice(4)));
		
		BoardState twoBlackAtPos14Board = new BoardState(); // :To fix 
		BoardState threeBlackAtPos14Board = new BoardState(); // :To fix 
		BoardState tenBlackAtPos14Board = new BoardState(); // :To fix 
		assertFalse(boardRules.isLegalMove(whitePiecePos10, twoBlackAtPos14Board, new Dice(4)));
		assertFalse(boardRules.isLegalMove(whitePiecePos10, threeBlackAtPos14Board, new Dice(4)));
		assertFalse(boardRules.isLegalMove(whitePiecePos10, tenBlackAtPos14Board, new Dice(4)));
	}
	
	@Test
	public void testIsLegalMove_ColoursBlackOntoWhite()
	{
		Piece blackPiecePos10 = new Piece(true, 10);
		
		BoardState loneWhiteAtPos14Board = new BoardState(); // :To fix 
		assertTrue(boardRules.isLegalMove(blackPiecePos10, loneWhiteAtPos14Board, new Dice(4)));
		
		BoardState twoWhiteAtPos14Board = new BoardState(); // :To fix 
		BoardState threeWhiteAtPos14Board = new BoardState(); // :To fix 
		BoardState tenWhiteAtPos14Board = new BoardState(); // :To fix 
		assertFalse(boardRules.isLegalMove(blackPiecePos10, twoWhiteAtPos14Board, new Dice(4)));
		assertFalse(boardRules.isLegalMove(blackPiecePos10, threeWhiteAtPos14Board, new Dice(4)));
		assertFalse(boardRules.isLegalMove(blackPiecePos10, tenWhiteAtPos14Board, new Dice(4)));
	}
	
	

	
	// legalMoves
	@Test
	public void testLegalMoves_NormalRoll()
	{
		// Piece at pos 2, rolls of 3 and 6 at empty board
		List<Dice> rollOfDices = Arrays.asList(new Dice(3), new Dice(6));
		Piece piecePos2 = new Piece(true, 2);
		List<Integer> expectedList;
		
		BoardState emptyBoard = new BoardState(); // :To fix
		expectedList = Arrays.asList(5,8);
		assertEquals(expectedList, boardRules.legalMoves(piecePos2, rollOfDices, emptyBoard));
		
		BoardState pos5Blocked = new BoardState(); // :To fix
		expectedList = Arrays.asList(8);
		assertEquals(expectedList, boardRules.legalMoves(piecePos2, rollOfDices, pos5Blocked));
		
		BoardState pos5and8Blocked = new BoardState(); // :To fix
		expectedList = Arrays.asList();
		assertEquals(expectedList, boardRules.legalMoves(piecePos2, rollOfDices, pos5and8Blocked));
	}
	
	@Test
	public void testLegalMoves_DoubleRoll()
	{
		// Piece at pos 6, rolls of 4,4,4 and 4 at empty board (Only one move of 4 is possible)
		List<Dice> rollOfDices = Arrays.asList(new Dice(4),new Dice(4),new Dice(4),new Dice(4));
		Piece piecePos6 = new Piece(true, 6);
		List<Integer> expectedList;
		
		BoardState emptyBoard = new BoardState(); // :To fix
		expectedList = Arrays.asList(10);
		assertEquals(expectedList, boardRules.legalMoves(piecePos6, rollOfDices, emptyBoard));
		
		BoardState pos10Blocked = new BoardState(); // :To fix
		expectedList = Arrays.asList();
		assertEquals(expectedList, boardRules.legalMoves(piecePos6, rollOfDices, pos10Blocked));
	}

}
