package pratofiorito.domain;

import java.util.Random;

public class Game
{
	// 0 = vuota, 1 = bomba
	private int[][] cells;
	
	public Game(int size, int bombs)
	{
		cells = new int[size][size];
		distributeBombs(bombs);
	}
	
	private void distributeBombs(int bombs)
	{
		Random random = new Random();
		
		//fintanto che hai bombe da piazzare
		while(bombs > 0)
		{
			// genera una cella random
			int x = random.nextInt(cells.length);
			int y = random.nextInt(cells[0].length);
			
			//se questa cella non ha già una bomba
			if(cells[x][y] == 0)
			{
				// piazza la bomba in questa cella
				cells[x][y] = 1;
				
				bombs--;
			}
		}

	}
	
	public int getSize()
	{
		return cells.length;
	}
	
	//TODO da aggiustare getcell
	public int getCell(int x, int y)
	{
		if(x<cells.length && y<cells[0].length)
		{
			return cells[x][y];
		}
		
		return 0;
	}

}
