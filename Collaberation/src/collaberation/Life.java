/*
*     __       __   _______  _______ 
*    |  |     |  | |   ____||   ____|
*    |  |     |  | |  |__   |  |__   
*    |  |     |  | |   __|  |   __|  
*    |  `----.|  | |  |     |  |____ 
*    |_______||__| |__|     |_______|
*                                                                       
* Nicholas Rempel
* Zachary Nickel
* Oct 14, 2022
* a version of Conway's game of life.
*/

package collaberation;

public class Life {
	
	int cellStateSizeX;
	int cellStateSizeY;
	boolean[][] cellState;
	boolean[][] newCellState;
	
	public Life()
	{
		cellStateSizeX = 20;
		cellStateSizeY = 20;
		cellState = new boolean[cellStateSizeX][cellStateSizeY];
		newCellState = new boolean[cellStateSizeX][cellStateSizeY];
		for(int i = 0; i < cellState.length; i++)
		{
			for(int j = 0; j < cellState[0].length; j++)
			{
				cellState[i][j] = false;
				newCellState[i][j] = false;
			}
		}
	}
	
	public Life(int x, int y)
	{
		cellStateSizeX = x;
		cellStateSizeY = y;
		cellState = new boolean[cellStateSizeX][cellStateSizeY];
		newCellState = new boolean[cellStateSizeX][cellStateSizeY];
		for(int i = 0; i < cellState.length; i++)
		{
			for(int j = 0; j < cellState[0].length; j++)
			{
				cellState[i][j] = false;
				newCellState[i][j] = false;
			}
		}
	}
	
	//true = alive, false = dead
	public boolean checkCellState(int x, int y)
	{
		return cellState[x][y];
	}
	
	//make cell dead
	public void killCell(int x, int y)
	{
		cellState[x][y] = false;
	}
	
	//make cell alive
	public void aliveCell(int x, int y)
	{
		cellState[x][y] = true;
	}
	
	//changes cell states based on requirements
	public void updateCellStates()
	{
		int neighbours = 0;
		for(int i = 0; i < cellState.length; i++)
		{
			for(int j = 0; j < cellState[0].length; j++)
			{
				//if the cell is alive
				if(cellState[i][j])
				{
					neighbours = 0;
					for(int a = -1; a <= 1; a++)
					{
						for(int b = -1; b <= 1; b++)
						{
							if(a == 0 && b == 0)
							{
								//void
							}
							else if(i + a >= 0 && j + b >= 0 && i + a < cellStateSizeX && j + b < cellStateSizeY)
							{
								if(cellState[i + a][j + b])
								{
									neighbours ++;
								}
							}
						}
					}
					//checks if there are two or three neighbours
					if(neighbours == 2)
					{
						newCellState[i][j] = true;
					}
					else if(neighbours == 3)
					{
						newCellState[i][j] = true;
					}
					else
					{
						newCellState[i][j] = false;
					}
				}
				//if the cell is not alive
				else
				{
					neighbours = 0;
					for(int a = -1; a <= 1; a++)
					{
						for(int b = -1; b <= 1; b++)
						{
							if(a == 0 && b == 0)
							{
								//void
							}
							else if(i + a >= 0 && j + b >= 0 && i + a < cellStateSizeX && j + b < cellStateSizeY)
							{
								if(cellState[i + a][j + b])
								{
									neighbours ++;
								}
							}
						}
					}
					if(neighbours == 3)
					{
						newCellState[i][j] = true;
					}
					else
					{
						newCellState[i][j] = false;
					}
				}
			}
		}
		
		//sets the cellState to the new one
		for(int i = 0; i < cellState.length; i++)
		{
			for(int j = 0; j < cellState[0].length; j++)
			{
				cellState[i][j] = newCellState[i][j];
			}
		}
	}
	
	//makes all cells false
	public void resetCells()
	{
		for(int i = 0; i < cellState.length; i++)
		{
			for(int j = 0; j < cellState[0].length; j++)
			{
				cellState[i][j] = false;
			}
		}
	}
}