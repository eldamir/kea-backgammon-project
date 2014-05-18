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
		boardController.initialize();
		int countOfSpikes = boardController.getBoardSpikes().size();
		assertEquals(24, countOfSpikes);
	}

}
