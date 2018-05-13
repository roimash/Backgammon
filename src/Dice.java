import java.util.Random;
public class Dice 
{
	private int number;
	
	public void Roll()
	{
		int result;
		Random rand = new Random();
		
		result = rand.nextInt(6)+1;
		this.number = result;
	}
	public int getNumber()
	{
		return this.number;
	}
	public void setNumber(int num)
	{
		this.number = num;
	}
	public void emptyDice()
	{
		this.number = 0;
	}
}
