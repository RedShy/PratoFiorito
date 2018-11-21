package pratofiorito.domain;

import java.util.Random;

public class Game
{
	// 0 = vuota, 1 = bomba
	private int[][] cells;
	private int remainingCells;
	private int bombs;

	public Game(int size, int bombs)
	{
		cells = new int[size][size];
		remainingCells = size * size;
		this.bombs = bombs;
		distributeBombs();
	}

	private void distributeBombs()
	{
		Random random = new Random();
		int bombsToPlace = bombs;
		// fintanto che hai bombe da piazzare
		while (bombsToPlace > 0)
		{
			// genera una cella random
			int x = random.nextInt(cells.length);
			int y = random.nextInt(cells[0].length);

			// se questa cella non ha già una bomba
			if (cells[x][y] == 0)
			{
				// piazza la bomba in questa cella
				cells[x][y] = 1;

				bombsToPlace--;
			}
		}

	}

	public int getSize()
	{
		return cells.length;
	}

	// TODO da aggiustare getcell
	public int getCell(int x, int y)
	{
		if (isGoodCoordinates(x,y))
		{
			return cells[x][y];
		}

		return 0;
	}

	public int getAdjacentsBombs(int x, int y)
	{
		int bombs = 0;
		for (int i = x - 1; i < x + 1; i++)
		{
			for (int j = y - 1; i < y + 1; i++)
			{
				bombs += cells[i][j];
			}
		}
		return bombs;
	}

	public boolean win()
	{
		return remainingCells == bombs;
	}
	
	public void openCell()
	{
		remainingCells--;
	}
	
	private boolean isGoodCoordinates(int x, int y)
	{
		// assicurati che la coordinata sia dentro la matrice
		return !((x < 0 || x >= cells.length) || (y < 0 || y >= cells[0].length));
	}

}
