import java.applet.*;
import java.awt.*;
import java.awt.Event;
public class Stake extends Applet
{
	private int xLocation;
	private int yLocation;
	private int id;
	private int stones;
	private String stonesType;
	
	public Stake(int xLocation, int yLocation, int id, int stones, String stonesType)
	{
		this.xLocation = xLocation;
		this.yLocation = yLocation;	
		this.id = id;
		this.stones = stones;
		this.stonesType = stonesType;
	}
	public void RemoveStone()
	{
		this.stones--;
	}
	public void AddStone()
	{
		this.stones++;
	}
	public void ChangeStonesType(String type)
	{
		this.stonesType = type;
	}
	public String GetStonesType()
	{
		return this.stonesType;
	}
	public int GetStones()
	{
		return this.stones;
	}
	public int GetxLocation()
	{
		return this.xLocation;
	}
	public int GetyLocation()
	{
		return this.yLocation;
	}
	public void DrawStake(Graphics g, Image redStoneImg, Image blackStoneImg)
	{
		if(this.id > 12)
		{
			if(this.stonesType == "Red")
				for(int i=0; i<this.stones; i++)
				{
					if(i < 5)
						g.drawImage(redStoneImg, this.xLocation, this.yLocation-(i*35), this);
					else if((i >= 5) && (i < 10))
						g.drawImage(redStoneImg, this.xLocation-4, this.yLocation-((i-5)*35+4), this);
					else
						g.drawImage(redStoneImg, this.xLocation-8, this.yLocation-((i-10)*35+8), this);
				}
			else if(this.stonesType == "Black")
				for(int i=0; i<this.stones; i++)
				{
					if(i < 5)
						g.drawImage(blackStoneImg, this.xLocation, this.yLocation-(i*35), this);
					else if((i >= 5) && (i < 10))
						g.drawImage(blackStoneImg, this.xLocation-7, this.yLocation-((i-5)*35+4), this);
					else
						g.drawImage(blackStoneImg, this.xLocation-14, this.yLocation-((i-10)*35+8), this);
				}
		}
		else
		{
			if(this.stonesType == "Red")
				for(int i=0; i<this.stones; i++)
				{
					if(i < 5)
						g.drawImage(redStoneImg, this.xLocation, this.yLocation+(i*35), this);
					else if((i >= 5) && (i < 10))
						g.drawImage(redStoneImg, this.xLocation-4, this.yLocation+((i-5)*35+4), this);
					else
						g.drawImage(redStoneImg, this.xLocation-8, this.yLocation+((i-10)*35+8), this);
				}
			else if(this.stonesType == "Black")
				for(int i=0; i<this.stones; i++)
				{
					if(i < 5)
						g.drawImage(blackStoneImg, this.xLocation, this.yLocation+(i*35), this);
					else if((i >= 5) && (i < 10))
						g.drawImage(blackStoneImg, this.xLocation-7, this.yLocation+((i-5)*35+4), this);
					else
						g.drawImage(blackStoneImg, this.xLocation-14, this.yLocation+((i-10)*35+8), this);
				}
		}
		repaint();
	}
}