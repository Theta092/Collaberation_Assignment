/*
*     __       __   _______  _______ 
*    |  |     |  | |   ____||   ____|
*    |  |     |  | |  |__   |  |__   
*    |  |     |  | |   __|  |   __|  
*    |  `----.|  | |  |     |  |____ 
*    |_______||__| |__|     |_______|
*                                                                       
*/

package collaberation;
import java.util.ArrayList;

public class Life {
	
	int gridSizeX;
	int gridSizeY;
	boolean[][] cellState;
	boolean[][] newCellState;
	
	public Life()
	{
		gridSizeX = 20;
		gridSizeY = 20;
		cellState = new boolean[gridSizeX][gridSizeY];
		newCellState = new boolean[gridSizeX][gridSizeY];
		for(int i = 0; i < cellState.length; i++)
		{
			for(int j = 0; j < cellState[0].length; j++)
			{
				cellState[i][j] = false;
				newCellState[i][j] = cellState[i][j];
			}
		}
	}
	
	public Life(int x, int y)
	{
		gridSizeX = x;
		gridSizeY = y;
		cellState = new boolean[gridSizeX][gridSizeY];
		newCellState = new boolean[gridSizeX][gridSizeY];
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
				//
				//if the cell is alive
				//
				if(cellState[i][j])
				{
					neighbours = 0;
					for(int a = -1; a <= 1; a++)
					{
						for(int b = -1; b <= 1; b++)
						{
							if(a != 0 && b != 0)
							{
								if(i + a >= 0 && j + b >= 0 && i + a < gridSizeX && j + b < gridSizeY)
								{
									if(cellState[i + a][j + b])
									{
										neighbours ++;
									}
								}
							}
						}
					}
					//checks if there are X number of neighbours
					if(neighbours == 3)
					{
						newCellState[i][j] = true;
					}
					else if(neighbours == 2)
					{
						newCellState[i][j] = true;
					}
					else
					{
						newCellState[i][j] = false;
					}
				}
				//
				//if the cell is not alive
				//
				else
				{
					neighbours = 0;
					//check neighbours
					for(int a = -1; a <= 1; a++)
					{
						for(int b = -1; b <= 1; b++)
						{
							if(a != 0 && b != 0)
							{
								if(i + a >= 0 && j + b >= 0 && i + a < gridSizeX && j + b < gridSizeY)
								{
									if(cellState[i + a][j + b])
									{
										neighbours ++;
									}
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
				//sets the grid to the new one
				cellState[i][j] = newCellState[i][j];
			}
		}
	}
}