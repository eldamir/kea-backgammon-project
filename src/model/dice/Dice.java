package model.dice;


/**
 * 
 * @author Thor-Bjørn Böhme & Ulrik Christensen
 * @version 1.0
 * @since 10:41:26 AM May 1, 2014
 * 
 *        A simple class that simulates a D6 dice.
 */
public class Dice
{
	private int value = -1;

	/**
	 * Simulates a D6 roll.
	 * 
	 * @since 1.0
	 * @return The face value of the D6 roll.
	 */
	public int roll()
	{

		value = (int)(Math.random() * 6) + 1;
		return value;
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}
}
