package pratofiorito.domain;

import java.util.Random;

public class Game
{
	// 0 = vuota, 1 = bomba
	private int[][] cells;
	private int remainingClosedCells;
	private int bombs;

	// -1 = coperta, maggiore o uguale di 0 = numero di bombe adiacenti, -2 =
	// bandierina
	private int[][] displayCells;

	// costanti per stato interno
	private final int CLEAN = 0;
	private final int BOMB = 1;

	// costanti per stato esterno
	private final int CLOSED_CELL = -1;
	private final int FLAG = -2;
	private final int EXPLODED_BOMB = -3;
	private final int DISCOVERED_BOMB = -4;
	private final int REMOVED_DISCOVERED_BOMB = -5;

	private String turn = "host";

	// contiene la stringa JSON delle celle modificate
	private StringBuilder modifiedCellsJSON;

	public Game(int width, int height, int bombs)
	{
		init(width,height,bombs);
	}
	
	
	public Game(String difficulty)
	{
		if(difficulty.equals("beginner"))
		{
			init(9,9,10);
		}else if(difficulty.equals("intermediate"))
		{
			init(16,16,40);
		}else if(difficulty.equals("advanced"))
		{
			init(16,30,99);
		}
	}
	
	private void init(int width, int height, int bombs)
	{
		cells = new int[width][height];
		remainingClosedCells = width * height;

		this.bombs = bombs;

		distributeBombs();

		inizializeDisplayCells(width,height);

		modifiedCellsJSON = new StringBuilder();
		modifiedCellsJSON.append('[');
	}

	private void inizializeDisplayCells(int width, int height)
	{
		displayCells = new int[width][height];
		for (int i = 0; i < displayCells.length; i++)
		{
			for (int j = 0; j < displayCells[i].length; j++)
			{
				displayCells[i][j] = CLOSED_CELL;
			}
		}
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
			int y = random.nextInt(cells[x].length);

			// se questa cella non ha gi� una bomba
			if (cells[x][y] == CLEAN)
			{
				// piazza la bomba in questa cella
				cells[x][y] = BOMB;

				bombsToPlace--;
			}
		}

	}

	public int getRows()
	{
		return cells.length;
	}
	
	public int getColumns()
	{
		return cells[0].length;
	}

	public int getCell(int x, int y)
	{
		if (isGoodCoordinates(x, y))
		{
			return cells[x][y];
		}

		return 0;
	}

	public int getDisplayCell(int x, int y)
	{
		if (isGoodCoordinates(x, y))
		{
			return displayCells[x][y];
		}

		return 0;
	}

	public int getAdjacentsBombs(int x, int y)
	{
		if (!isGoodCoordinates(x, y))
		{
			return 0;
		}

		// se x = 0 non puoi controllare la cella dietro perch� usciresti dalla matrice
		int i = x - 1;
		if (i < 0)
		{
			i = 0;
		}

		int bombs = 0;
		for (; i < cells.length && i <= x + 1; i++)
		{
			int j = y - 1;
			if (j < 0)
			{
				j = 0;
			}

			for (; j < cells[i].length && j <= y + 1; j++)
			{
				bombs += cells[i][j];
			}
		}
		return bombs;
	}

	public boolean won()
	{
		return remainingClosedCells == bombs;
	}

	public void openCell(int x, int y)
	{
		if (isGoodCoordinates(x, y))
		{
			// se la cella non � chiusa non fare nulla
			if (!isClosed(x, y))
			{
				return;
			}

			// se � una bomba, segnala che � una bomba aperta
			if (isBomb(x, y))
			{
				// Scopri anche tutte le altre bombe
				discoverBombs();

				// mostra che questa bomba � esplosa
				setDisplayCellAndInsertInJSON(x, y, EXPLODED_BOMB);

				return;
			}

			setDisplayCellAndInsertInJSON(x, y, getAdjacentsBombs(x, y));

			remainingClosedCells--;

			// se hai vinto mostra le altre bombe
			if (won())
			{
				discoverBombs();
			}

			// se la cella ha 0 bombe adiacenti, apro tutte le celle adiacenti
			if (getAdjacentsBombs(x, y) == 0)
			{
				openAdjacentCells(x, y);
			}
		}
	}

	public boolean isClosed(int x, int y)
	{
		if (isGoodCoordinates(x, y))
		{
			//se la cella � diversa da qualunque cosa sia closed non � apribile direttamente
			return displayCells[x][y] == CLOSED_CELL;
		}
		return false;
	}

	public boolean isFlag(int x, int y)
	{
		if (isGoodCoordinates(x, y))
		{
			return displayCells[x][y] == FLAG;
		}
		return false;
	}

	private void setDisplayCellAndInsertInJSON(int x, int y, int value)
	{
		displayCells[x][y] = value;

		modifiedCellsJSON.append("{\"x\":" + x + ",\"y\":" + y + ",\"image\":" + value + "},");
	}

	private void openAdjacentCells(int x, int y)
	{
		for (int i = x - 1; i <= x + 1; i++)
		{
			for (int j = y - 1; j <= y + 1; j++)
			{
				openCell(i, j);
			}
		}
	}

	private void discoverBombs()
	{
		// scandaglia tutta la matrice in cerca di bombe
		for (int i = 0; i < cells.length; i++)
		{
			for (int j = 0; j < cells[i].length; j++)
			{
				// ho trovato una bomba
				if (cells[i][j] == BOMB)
				{
					// se su questa bomba era presente una bandierina segnala che la bomba � stata
					// rimossa
					if (displayCells[i][j] == FLAG)
					{
						setDisplayCellAndInsertInJSON(i, j, REMOVED_DISCOVERED_BOMB);
					} else
					{
						// se non c'era alcuna bandierina mostra semplicemente la bomba
						setDisplayCellAndInsertInJSON(i, j, DISCOVERED_BOMB);
					}
				}
			}
		}
	}

	public boolean isBomb(int x, int y)
	{
		if (isGoodCoordinates(x, y))
		{
			return cells[x][y] == BOMB;
		}

		return false;
	}

	public void placeRemoveFlag(int x, int y)
	{
		if (isGoodCoordinates(x, y))
		{
			// se la cella non � una bandiera e non � apribile, non fare nulla
			if (displayCells[x][y] != FLAG && displayCells[x][y] != CLOSED_CELL)
			{
				return;
			}

			// se la bandierina era gi� stata piazzata rimuovila
			if (displayCells[x][y] == FLAG)
			{
				setDisplayCellAndInsertInJSON(x, y, CLOSED_CELL);
			} else
			{
				setDisplayCellAndInsertInJSON(x, y, FLAG);
			}
		}
	}

	private boolean isGoodCoordinates(int x, int y)
	{
		// assicurati che la coordinata sia dentro la matrice
		return !((x < 0 || x >= cells.length) || (y < 0 || y >= cells[x].length));
	}

	public void changeTurn()
	{
		if (turn == "host")
		{
			turn = "guest";
		} else if (turn == "guest")
		{
			turn = "host";
		}
	}

	public String getTurn()
	{
		return turn;
	}

	public void setTurn(String turn)
	{
		this.turn = turn;
	}

	public boolean lost()
	{
		for (int i = 0; i < displayCells.length; i++)
		{
			for (int j = 0; j < displayCells[i].length; j++)
			{
				if (displayCells[i][j] == EXPLODED_BOMB)
				{
					return true;
				}
			}
		}
		return false;
	}

	public String getModifiedCellsJSON()
	{
		// rimuovi ultimo carattere
		modifiedCellsJSON.setLength(modifiedCellsJSON.length() - 1);

		modifiedCellsJSON.append(']');
		String result = modifiedCellsJSON.toString();

		// svuota il registro delle modifiche
		modifiedCellsJSON = new StringBuilder();
		modifiedCellsJSON.append('[');

		return result;
	}

	public boolean isEnded()
	{
		if(lost() || won())
		{
			return true;
		}
		
		return false;
	}
	
}
