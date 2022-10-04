package collaberation;
import java.util.ArrayList;

public class Life {
	
	ArrayList<Integer> aliveNeighbors = new ArrayList<Integer>();
	ArrayList<Integer> deadNeighbors = new ArrayList<Integer>();
	
	int gridSizeX;
	int gridSizeY;
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
					for(int h = 0; h < aliveNeighbors.size(); h++ )
					{
						if(neighbours == aliveNeighbors.get(h))
						{
							newCellState[i][j] = true;
						}
						else
						{
							newCellState[i][j] = false;
						}
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
					for(int h = 0; h < deadNeighbors.size(); h++ )
					{
						if(neighbours == deadNeighbors.get(h))
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
		}
		//sets the grid to the new one
		for(int i = 0; i < cellState.length; i++)
		{
			for(int j = 0; j < cellState.length; j++)
			{
				cellState[i][j] = newCellState[i][j];
			}
		}
	}
	
	//add options for cells becoming alive
	public void addAliveRequirements(int surroundingCellCount)
	{
		aliveNeighbors.add(surroundingCellCount);
	}
	
	//remove options for cells becoming alive
	public void removeAliveRequirements(int surroundingCellCount)
	{
		aliveNeighbors.remove(surroundingCellCount);
	}
	
	//reset options for cells becoming alive to null
	public void resetAliveRequirements(int surroundingCellCount)
	{
		aliveNeighbors.replaceAll(null);
	}
	
	//add options for cells becoming dead
	public void addDeadRequirements(int surroundingCellCount)
	{
		deadNeighbors.add(surroundingCellCount);
	}
	
	//remove options for cells becoming dead
	public void removeDeadRequirements(int surroundingCellCount)
	{
		deadNeighbors.remove(surroundingCellCount);
	}
	
	//reset options for cells becoming dead
	public void resetDeadRequirements(int surroundingCellCount)
	{
		deadNeighbors.replaceAll(null);
	}
}
