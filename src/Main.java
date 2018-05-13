/****************************************************************************
 * This code programmed by me (Roi Mashiah) in 2010
*****************************************************************************/

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
public class Main extends Applet implements Runnable, KeyListener, MouseListener
{
	Thread th;
	Stake[] Stake;
	Player Player1, Player2;
	Dice Dice1, Dice2;
	Image menuImg, optionsImg, creditsImg, redEndGameImg, blackEndGameImg;
	Image board, stock, redStoneImg, blackStoneImg, redTurnImg, blackTurnImg;
	Image dice1Img, dice2Img, dice3Img, dice4Img, dice5Img, dice6Img;
	final static long serialVersionUID = 0;
	boolean dice1 = true, dice2 = true;
	int MouseSelectStake = -1, MouseMoveStake = -1;
	int turn = 1, rolls = 1;
	int mode = 0;
	
    public void init()
    {
    	this.setBackground(Color.decode("#293847"));
    	Player1  = new Player(0, 15, "Red");
    	Player2  = new Player(1, 15, "Black");
    	Dice1    = new Dice();
    	Dice2    = new Dice();
    	Stake    = new Stake[28];
    	Stake[0] = new Stake(325,  85,  0,  0, "Red"  ); //The Red stones graveyard stake(in middle of the board)
    	Stake[1] = new Stake(632,   9,  1,  2, "Red"  );
   		Stake[2] = new Stake(578,   9,  2,  0, "na"   );
   		Stake[3] = new Stake(523,   9,  3,  0, "na"   );
   		Stake[4] = new Stake(471,   9,  4,  0, "na"   );
   		Stake[5] = new Stake(418,   9,  5,  0, "na"   );
   		Stake[6] = new Stake(363,   9,  6,  5, "Black");
   		Stake[7] = new Stake(283,   9,  7,  0, "na"   );
   		Stake[8] = new Stake(230,   9,  8,  3, "Black");
   		Stake[9] = new Stake(177,   9,  9,  0, "na"   );
   		Stake[10]= new Stake(123,   9, 10,  0, "na"   );
   		Stake[11]= new Stake( 69,   9, 11,  0, "na"   );
   		Stake[12]= new Stake( 17,   9, 12,  5, "Red"  );
   		Stake[13]= new Stake( 17, 440, 13,  5, "Black");
   		Stake[14]= new Stake( 70, 440, 14,  0, "na"   );
   		Stake[15]= new Stake(123, 440, 15,  0, "na"   );
   		Stake[16]= new Stake(176, 440, 16,  0, "na"   );
   		Stake[17]= new Stake(229, 440, 17,  3, "Red"  );
   		Stake[18]= new Stake(282, 440, 18,  0, "na"   );
   		Stake[19]= new Stake(364, 440, 19,  5, "Red"  );
   		Stake[20]= new Stake(417, 440, 20,  0, "na"   );
   		Stake[21]= new Stake(470, 440, 21,  0, "na"   );
   		Stake[22]= new Stake(523, 440, 22,  0, "na"   );
   		Stake[23]= new Stake(576, 440, 23,  0, "na"   );
   		Stake[24]= new Stake(631, 440, 24,  2, "Black");
   		Stake[25]= new Stake(325, 370, 25,  0, "Black"); //The Black stones graveyard stake(in middle of the board)
   		Stake[26]= new Stake(698, 143, 26,  0, "Black");//the black trash stake
   		Stake[27]= new Stake(698, 452, 27,  0, "Red"  );//the red trash stake
   		menuImg         = getImage(getCodeBase(), "style_images/menu.png"           );
   		optionsImg      = getImage(getCodeBase(), "style_images/options.png"        );
   		creditsImg      = getImage(getCodeBase(), "style_images/credits.png"        );
   		redEndGameImg   = getImage(getCodeBase(), "style_images/red_endgame.png"    );
   		blackEndGameImg = getImage(getCodeBase(), "style_images/black_endgame.png"  );
   		board           = getImage(getCodeBase(), "style_images/board.png"          );
    	stock           = getImage(getCodeBase(), "style_images/stock.png"          );
		redStoneImg     = getImage(getCodeBase(), "style_images/red_stone.png"      );
		blackStoneImg   = getImage(getCodeBase(), "style_images/black_stone.png"    );
		redTurnImg      = getImage(getCodeBase(), "style_images/red_turn.png"       );
		blackTurnImg    = getImage(getCodeBase(), "style_images/black_turn.png"     );
		dice1Img        = getImage(getCodeBase(), "style_images/dice1.png"          );
		dice2Img        = getImage(getCodeBase(), "style_images/dice2.png"          );
		dice3Img        = getImage(getCodeBase(), "style_images/dice3.png"          );
		dice4Img        = getImage(getCodeBase(), "style_images/dice4.png"          );
		dice5Img        = getImage(getCodeBase(), "style_images/dice5.png"          );
		dice6Img        = getImage(getCodeBase(), "style_images/dice6.png"          );
		
    	this.setSize(750,500);
    	addKeyListener(this);
    	addMouseListener(this);
    	this.setFocusable(true);
		this.setLayout(new FlowLayout());
		this.EndTurn();
    }
    public void start() 
    {
		th = new Thread(this);
		th.start();
    }
    public void paint(Graphics g)
    {
    	if(mode == 0)
        	g.drawImage(menuImg, 0, 0, this);
    	else if(mode == 2)
        	g.drawImage(optionsImg, 0, 0, this);
    	else if(mode == 3)
        	g.drawImage(creditsImg, 0, 0, this);
    	else if(mode == 4)
        	g.drawImage(redEndGameImg, 0, 0, this);
    	else if(mode == 5)
        	g.drawImage(blackEndGameImg, 0, 0, this);
    	else
    	{
    		g.drawImage(board, 0, 0, this);
    		g.drawImage(stock, 699, 0, this);
    		for(int i=0; i<Stake.length; i++)
    			Stake[i].DrawStake(g, redStoneImg, blackStoneImg);
    	
    		switch(Dice1.getNumber())
    		{
    			case 1:
    				g.drawImage(dice1Img, 131, 225, this);
    				break;
    			case 2:
    				g.drawImage(dice2Img, 131, 225, this);
    				break;
    			case 3:
    				g.drawImage(dice3Img, 131, 225, this);
    				break;
    			case 4:
    				g.drawImage(dice4Img, 131, 225, this);
    				break;
    			case 5:
    				g.drawImage(dice5Img, 131, 225, this);
    				break; 
    			case 6:
    				g.drawImage(dice6Img, 131, 225, this);
    				break;
    		}
    		switch(Dice2.getNumber())
    		{
    			case 1:
    				g.drawImage(dice1Img, 226, 225, this);
    				break;
    			case 2:
    				g.drawImage(dice2Img, 226, 225, this);
    				break;
    			case 3:
    				g.drawImage(dice3Img, 226, 225, this);
    				break;
    			case 4:
    				g.drawImage(dice4Img, 226, 225, this);
    				break;
    			case 5:
    				g.drawImage(dice5Img, 226, 225, this);
    				break; 
    			case 6:
    				g.drawImage(dice6Img, 226, 225, this);
    				break;
    		}
    		if(turn == 0)
    			g.drawImage(redTurnImg, 450, 220, this);
    		else
    			g.drawImage(blackTurnImg, 440, 220, this);
    		//g.drawString("Stake Selected:"+MouseSelectStake, 50, 230);
    		//g.drawString("Stake to Move:"+MouseMoveStake, 50, 250);  	
    	}
    }
	public void keyPressed(KeyEvent e)
	{
		int Key = e.getKeyCode();
		switch (Key)
		{
			case KeyEvent.VK_C:
				Dice1.setNumber(6);
				Dice2.setNumber(6);
				rolls = 2;
				break;
			case KeyEvent.VK_Z:
				this.EndTurn();
				break;
		}
		repaint();
	}
	public boolean isChosedHisStone()
	{
		if(Player1.getID() == turn)
			if(Stake[MouseSelectStake].GetStonesType() != Player1.getStonesType())	
				return false;
		if(Player2.getID() == turn)
			if(Stake[MouseSelectStake].GetStonesType() != Player2.getStonesType())
				return false;
		return true;
	}
	public boolean isPlayerMoveValid()
	{
		if(MouseMoveStake == 0 || MouseMoveStake == 25 || MouseMoveStake == 26 || MouseMoveStake == 27)
			return false;
		if(Stake[MouseMoveStake].GetStonesType() == "Red" && Stake[MouseMoveStake].GetStones() > 1)
		{
			if(turn == 1)
				return false;
		}
		else if(Stake[MouseMoveStake].GetStonesType() == "Black" && Stake[MouseMoveStake].GetStones() > 1)
		{
			if(turn == 0)
				return false;
		}
		return true;
	}
	public boolean isPlayerStuck()
	{
		if((turn == 0) && (this.isRedUntilStake(19) == true))
		{
			for(int i=1; i<24; i++)
			{
				if(Stake[i].GetStonesType() == "Red")
				{
					if((dice1 == true) && (Dice1.getNumber()+i < 25))
					{
						if((Stake[i+Dice1.getNumber()].GetStonesType() != "Black") || (Stake[i+Dice1.getNumber()].GetStones() <= 1))
						{
							return false;
						}
					}
					if((dice2 == true) && (Dice2.getNumber()+i < 25))
					{
						if((Stake[i+Dice2.getNumber()].GetStonesType() != "Black") || (Stake[i+Dice2.getNumber()].GetStones() <= 1))
						{
							return false;
						}
					}
				}
			}
		}
		else if((turn == 1) && (this.isBlackUntilStake(6) == true))
		{
			for(int i=24; i>1; i--)
			{
				if(Stake[i].GetStonesType() == "Black")
				{
					if((dice1 == true) && (i-Dice1.getNumber() > 1))
					{
						if((Stake[i-Dice1.getNumber()].GetStonesType() != "Red") || (Stake[i-Dice1.getNumber()].GetStones() <= 1))
							return false;
					}
					if((dice2 == true) && (i-Dice2.getNumber() > 1))
					{
						if((Stake[i-Dice2.getNumber()].GetStonesType() != "Red") || (Stake[i-Dice2.getNumber()].GetStones() <= 1))
							return false;
					}
				}
			}
		}
		if(dice1 == false && dice2 == false)
			return false;
		return true;
	}
	public boolean isStuckAtGraveyard()
	{
		if(turn == 0)
		{
			if(Stake[0].GetStones() == 0)	{
				return false;
			}
			else
			{
				if(dice1 == true && dice2 == true)
				{
					if((Stake[Dice1.getNumber()].GetStonesType() == "Black") && (Stake[Dice1.getNumber()].GetStones() > 1))
					{
						if((Stake[Dice2.getNumber()].GetStonesType() == "Black") && (Stake[Dice2.getNumber()].GetStones() > 1))
						{
							System.out.println("ID = 1");
							return true;
						}
					}
				}
				else if(dice1 == true && dice2 == false)
				{
					if(Stake[25].GetStones() == 0)
						return false;
					if((Stake[Dice1.getNumber()].GetStonesType() == "Black") && (Stake[Dice1.getNumber()].GetStones() > 1))
					{
						System.out.println("ID = 2");
						return true;
					}	
				}
				else if(dice1 == false && dice2 == true)
				{
					if(Stake[25].GetStones() == 0)
						return false;
					if((Stake[Dice2.getNumber()].GetStonesType() == "Black") && (Stake[Dice2.getNumber()].GetStones() > 1))
					{
						System.out.println("ID = 3");
						return true;
					}
				}
			}
		}
		else if(turn == 1)
		{
			if(Stake[25].GetStones() == 0)	{
				return false;
			}
			else
			{
				if(dice1 == true && dice2 == true)
				{
					if((Stake[25-Dice1.getNumber()].GetStonesType() == "Red") && (Stake[25-Dice1.getNumber()].GetStones() > 1))
					{
						if((Stake[25-Dice2.getNumber()].GetStonesType() == "Red") && (Stake[25-Dice2.getNumber()].GetStones() > 1))
						{
							System.out.println("ID = 4");
							return true;
						}
					}
				}
				else if(dice1 == true && dice2 == false)
				{
					if(Stake[25].GetStones() == 0)
						return false;
					if((Stake[25-Dice1.getNumber()].GetStonesType() == "Red") && (Stake[25-Dice1.getNumber()].GetStones() > 1))
					{
						System.out.println("ID = 5");
						return true;
					}
				}
				else if(dice1 == false && dice2 == true)
				{
					if(Stake[25].GetStones() == 0)
						return false;
					if((Stake[25-Dice2.getNumber()].GetStonesType() == "Red") && (Stake[25-Dice2.getNumber()].GetStones() > 1))
					{
						System.out.println("ID = 6");
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean isRedAtGraveyard()
	{
		if(Stake[0].GetStones() > 0)
			return true;
		return false;
	}
	public boolean isBlackAtGraveyard()
	{
		if(Stake[25].GetStones() > 0)
			return true;
		return false;
	}
	public boolean isRedUntilStake(int stake)
	{
		for(int i=0; i<stake; i++)
			if(Stake[i].GetStonesType() == "Red" && Stake[i].GetStones() > 0)
				return true;
		return false;
	}
	public boolean isBlackUntilStake(int stake)
	{
		for(int i=25; i>stake; i--)
			if(Stake[i].GetStonesType() == "Black" && Stake[i].GetStones() > 0)
				return true;
		return false;
	}
	public boolean MoveByDices()
	{
		if(turn == 0)
		{
			if((MouseMoveStake - MouseSelectStake == Dice1.getNumber()) && (dice1 == true))
			{
				dice1 = false;
				return true;
			}
			else if((MouseMoveStake - MouseSelectStake == Dice2.getNumber()) && (dice2 == true))
			{
				dice2 = false;
				return true;
			}
			else if(((MouseMoveStake - MouseSelectStake) == (Dice1.getNumber()+Dice2.getNumber())) && ((dice1 == true) && (dice2 == true)))
			{
				if((Stake[MouseSelectStake + Dice1.getNumber()].GetStones() > 1 && Stake[MouseSelectStake + Dice1.getNumber()].GetStonesType() == "Black") && (Stake[MouseSelectStake + Dice2.getNumber()].GetStones() > 1 && Stake[MouseSelectStake + Dice2.getNumber()].GetStonesType() == "Black"))
					return false;
				dice1 = false;
				dice2 = false;
				return true;
			}
		}
		else if(turn == 1)
		{
			if((MouseSelectStake - MouseMoveStake == Dice1.getNumber()) && (dice1 == true))
			{
				dice1 = false;
				return true;
			}
			else if((MouseSelectStake - MouseMoveStake == Dice2.getNumber()) && (dice2 == true))
			{
				dice2 = false;
				return true;
			}
			else if(((MouseSelectStake - MouseMoveStake) == (Dice1.getNumber()+Dice2.getNumber())) && ((dice1 == true) && (dice2 == true)))
			{
				if((Stake[MouseSelectStake - Dice1.getNumber()].GetStones() > 1 && Stake[MouseSelectStake - Dice1.getNumber()].GetStonesType() == "Red") && (Stake[MouseSelectStake - Dice2.getNumber()].GetStones() > 1 && Stake[MouseSelectStake - Dice2.getNumber()].GetStonesType() == "Red"))
					return false;
				dice1 = false;
				dice2 = false;
				return true;
			}
		}
		return false;
	}
	
	public void ResetMouseVars()
	{
		MouseSelectStake = -1;
		MouseMoveStake = -1;
	}
	public void EndRoll()
	{
		dice1 = true;
		dice2 = true;
		rolls--;
	}
	public void EndTurn()
	{
		if(turn == 1)
			turn--;
		else if(turn == 0)
			turn++;
		dice1 = true;
		dice2 = true;
		Dice1.Roll();
		Dice2.Roll();
		if(Dice1.getNumber() == Dice2.getNumber())
			rolls = 2;
		else
			rolls = 1;
	}
	public void mouseClicked(MouseEvent mouse)
	{
		int x, y;
		
		x = mouse.getX();
		y = mouse.getY();
		System.out.println("x,y("+x+", "+y+")");
		
		//Main Menu mode
		if(mode == 0)	{
			if((mouse.getX() > 257 && mouse.getX() < 509) && (mouse.getY() > 233 && mouse.getY() < 279))
				mode = 1;
			else if((mouse.getX() > 257 && mouse.getX() < 509) && (mouse.getY() > 335 && mouse.getY() < 382))
				mode = 2;
			else if((mouse.getX() > 257 && mouse.getX() < 509) && (mouse.getY() > 430 && mouse.getY() < 476))
				mode = 3;
		}
		//Options mode
		else if(mode == 2)	{
			if((mouse.getX() > 135 && mouse.getX() < 257) && (mouse.getY() > 255 && mouse.getY() < 288))	{
				turn = 0;
				mode = 0;
			}
			else if((mouse.getX() > 495 && mouse.getX() < 652) && (mouse.getY() > 255 && mouse.getY() < 288))	{
				turn = 1;
				mode = 0;
			}
		}
		//Credits mode
		else if(mode == 3)	{
			if((mouse.getX() > 274 && mouse.getX() < 526) && (mouse.getY() > 428 && mouse.getY() < 479))
				mode = 0;
		}
		else {
		for(int i=0; i<Stake.length; i++) //Check each stake
		{
			//Locate the horizontal point of the stake
			if(((Stake[i].GetxLocation() - x < 0) && (Stake[i].GetxLocation() - x > -50)))
			{
				//Locate the vertical point of the stake
				if(((Stake[i].GetyLocation() - y < 150) && (Stake[i].GetyLocation() - y > -180)))
				{
					//Is stake to take stone from selected already?
					if((MouseSelectStake != -1) && (MouseMoveStake == -1))
					{
						//######################
						//Start Game Conditions
						//######################
						
						MouseMoveStake = i;
						
						//Is the player chosed his own stone type?
						if(this.isChosedHisStone() == false)
						{
							this.ResetMouseVars();
							break;
						}
						
						//Is the player have stones at the graveyard?
						if(this.isRedAtGraveyard() == true)	
						{
							if(turn == 0)
							{
								if(MouseSelectStake != 0)
								{
									this.ResetMouseVars();
									break;
								}
							}
						}
						if(this.isBlackAtGraveyard() == true)	
						{
							if(turn == 1)
							{
								if(MouseSelectStake != 25)
								{
									this.ResetMouseVars();
									break;
								}
							}
						}
						
						//Is all Red's at home?
						if(this.isRedUntilStake(19) == false)
						{
							if(turn == 0)
							{	
								if(MouseMoveStake == 27)
								{
									if((25 - MouseSelectStake == Dice1.getNumber() || (this.isRedUntilStake(25 - Dice1.getNumber()) == false) && (this.isRedUntilStake(MouseSelectStake)) == false) && (dice1 == true))
									{
										if(Stake[MouseSelectStake].GetStones() > 0)
										{
											Stake[MouseSelectStake].RemoveStone();
											Stake[27].AddStone();
											Player1.DropStone();
											dice1 = false;
											if(Stake[MouseSelectStake].GetStones() == 0)
												Stake[MouseSelectStake].ChangeStonesType("na");
										}
									}
									else if((25 - MouseSelectStake == Dice2.getNumber() || (this.isRedUntilStake(25 - Dice2.getNumber()) == false) && (this.isRedUntilStake(MouseSelectStake)) == false) && (dice2 == true))
									{
										if(Stake[MouseSelectStake].GetStones() > 0)
										{
											Stake[MouseSelectStake].RemoveStone();
											Stake[27].AddStone();
											Player1.DropStone();
											dice2 = false;
											if(Stake[MouseSelectStake].GetStones() == 0)
												Stake[MouseSelectStake].ChangeStonesType("na");
										}
									}
									this.ResetMouseVars();
									if(dice1 == false && dice2 == false)
										this.EndRoll();
									if(rolls == 0)
										this.EndTurn();
									break;
								}
							}
						}
						
						//Is all Black's at home?
						if(this.isBlackUntilStake(6) == false)
						{
							if(turn == 1)
							{	
								if(MouseMoveStake == 26)
								{
									if((MouseSelectStake == Dice1.getNumber() || (this.isBlackUntilStake(Dice1.getNumber()) == false) && (this.isBlackUntilStake(MouseSelectStake) == false)) && (dice1 == true))
									{
										if(Stake[MouseSelectStake].GetStones() > 0)
										{
											Stake[MouseSelectStake].RemoveStone();
											Stake[26].AddStone();
											Player2.DropStone();
											dice1 = false;
											if(Stake[MouseSelectStake].GetStones() == 0)
												Stake[MouseSelectStake].ChangeStonesType("na");
										}
									}
									else if((MouseSelectStake == Dice2.getNumber() || (this.isBlackUntilStake(Dice2.getNumber()) == false) && (this.isBlackUntilStake(MouseSelectStake) == false)) && (dice2 == true))
									{
										if(Stake[MouseSelectStake].GetStones() > 0)
										{
											Stake[MouseSelectStake].RemoveStone();
											Stake[26].AddStone();
											Player2.DropStone();
											dice2 = false;
											if(Stake[MouseSelectStake].GetStones() == 0)
												Stake[MouseSelectStake].ChangeStonesType("na");
										}
									}
									this.ResetMouseVars();
									if(dice1 == false && dice2 == false)
										this.EndRoll();
									if(rolls == 0)
										this.EndTurn();
									break;
								}
							}
						}
						
						//Is the player moves to a valid stake?
						if(this.isPlayerMoveValid() == false)
						{
							this.ResetMouseVars();
							break;
						}
						
						//Is the player moves to stake valid for the dices?
						if(this.MoveByDices() == false)
						{
							this.ResetMouseVars();
							break;
						}
						
						//Return to currect stake option:If the player has returned the stone back to the selected stake.
						if(MouseSelectStake == MouseMoveStake)
							this.ResetMouseVars();
						
						//Eat option:If stone colors are different but the stake we gonna move to has only 1 stone
						else if((Stake[MouseSelectStake].GetStonesType() != Stake[MouseMoveStake].GetStonesType()) && (Stake[MouseMoveStake].GetStones() == 1))
						{
							//Be sure the selected stake has at least 1 stone.
							if(Stake[MouseSelectStake].GetStones() > 0)
							{
								if(Stake[MouseMoveStake].GetStonesType() == "Red")
								{
									Stake[0].AddStone();
									Stake[MouseMoveStake].ChangeStonesType("Black");
									Stake[MouseSelectStake].RemoveStone();
								}
								else if(Stake[MouseMoveStake].GetStonesType() == "Black")
								{
									Stake[25].AddStone();
									Stake[MouseMoveStake].ChangeStonesType("Red");
									Stake[MouseSelectStake].RemoveStone();
								}
								if(Stake[MouseSelectStake].GetStones() == 0)
									if((MouseSelectStake != 0) && (MouseSelectStake != 25))
										Stake[MouseSelectStake].ChangeStonesType("na");
								this.ResetMouseVars();
								if(dice1 == false && dice2 == false)
									this.EndRoll();
								if(rolls == 0)
									this.EndTurn();
							}
						}
						
						//Run Home option:If the stakes have the same color
						else if((Stake[MouseSelectStake].GetStonesType() == Stake[MouseMoveStake].GetStonesType()))
						{
							//Be sure the selected stake has at least 1 stone.
							if(Stake[MouseSelectStake].GetStones() > 0)
							{
								Stake[MouseSelectStake].RemoveStone();
								Stake[MouseMoveStake].AddStone();
								if(Stake[MouseSelectStake].GetStones() == 0)
									if((MouseSelectStake != 0) && (MouseSelectStake != 25))
										Stake[MouseSelectStake].ChangeStonesType("na");
								this.ResetMouseVars();
								if(dice1 == false && dice2 == false)
									this.EndRoll();
								if(rolls == 0)
									this.EndTurn();
							}
						}
						
						//Clear stake move option: If the stake to move to is empty
						else if(Stake[MouseMoveStake].GetStonesType() == "na")
						{
							if(Stake[MouseSelectStake].GetStones() > 0)
							{
								Stake[MouseSelectStake].RemoveStone();
								Stake[MouseMoveStake].ChangeStonesType(Stake[MouseSelectStake].GetStonesType());
								Stake[MouseMoveStake].AddStone();
								if(Stake[MouseSelectStake].GetStones() == 0)
									if((MouseSelectStake != 0) && (MouseSelectStake != 25))
										Stake[MouseSelectStake].ChangeStonesType("na");
								this.ResetMouseVars();
								if(dice1 == false && dice2 == false)
									this.EndRoll();
								if(rolls == 0)
									this.EndTurn();
							}
						}
						//######################
						//End Game Conditions
						//######################
					}
					//If the stake to take stone from isnt selected, select.
					else if(MouseSelectStake == -1)
						MouseSelectStake = i;
					else
						this.ResetMouseVars();
				}
			}
		}
		} //close the else tag above the for loop.
		repaint();
	}
    public void run() 
    {
    	while(true)
    	{
    		//Main Menu mode
    		if(mode == 1)
    		{
    			//Is the player stucks at field (can't move)?
    			/*
    			 if(this.isPlayerStuck() == true)
    			 {
    			 	System.out.println("Stucked at Field! Dice1:"+Dice1.getNumber()+", Dice2:"+Dice2.getNumber());
    			 	this.EndTurn();
    			 }
    			 */
    			//Is the player stucks at the graveyard(cant move)?
    			if(this.isStuckAtGraveyard() == true)
    			{
    				System.out.println("Stucked at graveyard!  Dice1:"+Dice1.getNumber()+", Dice2:"+Dice2.getNumber());
    				this.EndTurn();
    			}
    		
    			if(Player1.GetStones() == 0)	{
    				mode = 4;
    				break;
    			}
    			if(Player2.GetStones() == 0)	{
    				mode = 5;
    				break;
    			}
    		}
    	}
    } 
	//Auto generated functions
    public void stop() {}
    public void destroy() {} 
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {} 
}