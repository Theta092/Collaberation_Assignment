package collaberation;
import java.util.Scanner;
public class Life {
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		final int GridSize = 20;
		boolean[][] grid = new boolean[GridSize][GridSize];
		boolean[][] newGrid = new boolean[GridSize][GridSize];
		boolean choice = true;
		String continu;
		int x,y;
		int startingCells;
		int neighbours = 0;

		//initial cells
		
		System.out.print("How many cells: ");
		startingCells = input.nextInt();
		
		for(int i = 0; i < startingCells; i++)
		{
			System.out.print("Enter an x coordinate from 1 to " + grid.length + " for a cell: ");
			x = input.nextInt() - 1;
			System.out.print("Enter an y coordinate from 1 to " + grid.length + " for a cell: ");
			y = input.nextInt() - 1;
			grid[y][x] = true;
			System.out.println();
		}
		
		input.nextLine();
		while(choice)
		{
			showGrid(grid);
			
			for(int i = 0; i < grid.length; i++)
			{
				for(int j = 0; j < grid[0].length; j++)
				{
					//if the cell is alive
					if(grid[i][j])
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
								else if(i + a >= 0 && j + b >= 0 && i + a < GridSize && j + b < GridSize)
								{
									if(grid[i + a][j + b])
									{
										neighbours ++;
									}
								}
							}
						}
						//checks if there are two or three neighbours
						if(neighbours == 2)
						{
							newGrid[i][j] = true;
						}
						else if(neighbours == 3)
						{
							newGrid[i][j] = true;
						}
						else
						{
							newGrid[i][j] = false;
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
								else if(i + a >= 0 && j + b >= 0 && i + a < GridSize && j + b < GridSize)
								{
									if(grid[i + a][j + b])
									{
										neighbours ++;
									}
								}
							}
						}
						if(neighbours == 3)
						{
							newGrid[i][j] = true;
						}
						else
						{
							newGrid[i][j] = false;
						}
					}
				}
			}
			
			//sets the grid to the new one
			for(int i = 0; i < grid.length; i++)
			{
				for(int j = 0; j < grid.length; j++)
				{
					grid[i][j] = newGrid[i][j];
				}
			}

			
			//ask for loop
			System.out.print("run again? (Y/N): ");
			continu = input.nextLine();
			if(continu.equalsIgnoreCase("n"))
			{
				choice = false;
			}
		}
	}
	
	//diplays the gird
	public static void showGrid(boolean[][] grid)
	{
		//displays grid
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid.length; j++)
			{
				if(grid[i][j])
				{
					System.out.print(" X ");
				}
				else
				{
					System.out.print(" - ");
				}
			}
			System.out.println();
		}
	}
}
