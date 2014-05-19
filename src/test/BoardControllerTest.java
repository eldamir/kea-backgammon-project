package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controller.BoardController;

public class BoardControllerTest
{
	BoardController boardController;
	
	@Before
	public void setUp() throws Exception
	{
		boardController = new BoardController();
	}

	@Test
	public void testAddPieceToField()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testInitialize()
	{
		System.out.println("test");
		System.out.println("countOfSpikes: " + boardController.getBoardSpikes().size());
		boardController.initialize();
		System.out.println("countOfSpikes: " + boardController.getBoardSpikes().size());
		int countOfSpikes = boardController.getBoardSpikes().size();
		
		assertEquals(24, countOfSpikes);
	}

}
