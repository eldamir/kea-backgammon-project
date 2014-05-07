package test;


import static org.junit.Assert.fail;
import model.dice.Dice;

import org.junit.Test;


public class DiceTest
{

	@Test
	public void test()
	{
		Dice dice = new Dice();
		int sum = 0;

		for (int i = 0; i < 10000000; i++)
		{
			int value = dice.roll();
			if (value < 1 || value > 6)
			{
				fail("dice roll not valid D6 value");
			}
			sum += value;
		}
		double average = sum / 10000000.0;
		if (average < 3 || average > 4)
		{
			fail("average dice roll not valid D6 value");
		}
	}

}
