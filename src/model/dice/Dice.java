package model.dice;


/**
 * 
 * @version 1.0
 * @since 10:41:26 AM May 1, 2014
 * 
 *        A simple class that simulates a D6 dice.
 */
public class Dice
{
	private int rollValue = -1;

	public Dice()
	{
	}
	
	public Dice(int knownValue)
	{
		rollValue = knownValue;
	}
	
	/** Simulates a D6 roll.
	 * @since 1.0
	 * @return The face value of the D6 roll.
	 */
	public int roll()
	{
		rollValue = (int)(Math.random() * 6) + 1;
		return rollValue;
	}

	public int getValue()
	{
		return rollValue;
	}

	public void setValue(int value)
	{
		this.rollValue = value;
	}
	
	/** For use during a roll of identical values on both dice
	 * @return A new dice with same roll value as source
	 */
	public Dice copyOfDice()
	{
		Dice copyDice = new Dice();
		copyDice.setValue(rollValue);
		return copyDice;
	}
}
