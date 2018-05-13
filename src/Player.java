public class Player 
{
	private int id;
	private int stones;
	private String stonesType;
	
	public Player(int id, int stones, String stonesType)
	{
		this.id = id;
		this.stones = stones;
		this.stonesType = stonesType;
	}
	public void DropStone()
	{
		this.stones--;
	}
	public int GetStones()
	{
		return this.stones;
	}
	public int getID()
	{
		return this.id;
	}
	public String getStonesType()
	{
		return this.stonesType;
	}
}
