package collaberation;

public class Life {
	
	int gridSizeX;
	int gridSizeY;
	int aliveNeighbors = 2;
	int deadNeighbors;
	boolean[][] cellState;
	boolean[][] newCellState;
	
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
				newCellState[i][j] = cellState[i][j];
			}
		}
	}
	
	//true = alive, false = dead
	public boolean checkCellState(int x, int y)
	{
		return cellState[x][y];
	}
	
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
							if(a != 0 && b != 0)
							{
								if(i + a >= 0 && j + b >= 0 && i + a < gridSizeX && j + b < gridSizeY)
								{
									neighbours ++;
								}
							}
						}
					}
					//checks if there are two or three neighbours
					if(neighbours == aliveNeighbors)
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
					
				}
			}
		}
	}
	
	public void changeAliveRequirements(int surroundingCellCount)
	{
		
	}
	
	public void changeDeadRequirements(int surroundingCellCount)
	{
		
	}
}
