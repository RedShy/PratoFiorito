package pratofiorito.domain;

import java.util.ArrayList;
import java.util.List;

public class Lobby
{
	private String title;
	private String host;
	private String guest;
	private Game game;
	private int capacity;

	public Lobby(String title)
	{
		super();
		this.title = title;
		capacity = 2;
	}

	public String getHost()
	{
		return host;
	}

	public void setHost(String host)
	{
		this.host = host;
	}

	public String getGuest()
	{
		return guest;
	}

	public void setGuest(String guest)
	{
		this.guest = guest;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}
	
	

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((guest == null) ? 0 : guest.hashCode());
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lobby other = (Lobby) obj;
		if (guest == null)
		{
			if (other.guest != null)
				return false;
		} else if (!guest.equals(other.guest))
			return false;
		if (host == null)
		{
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		if (title == null)
		{
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public boolean isFull()
	{
		return host != null && guest != null;
	}
	
	public boolean joinGuest(String username)
	{
		if(isFull())
		{
			return false;
		}
		
		guest=username;
		return true;
	}
	
	public boolean joinHost(String username)
	{
		if(isFull())
		{
			return false;
		}
		
		host=username;
		return true;
	}

	public void removeGuest()
	{
		guest = null;
	}

	public void setGame(Game game)
	{
		this.game=game;
	}

	public Game getGame()
	{
		return game;
	}

	public int getCapacity()
	{
		return capacity;
	}

	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}

	//TODO: da aggiustare
	public int getNumberPlayersInside()
	{
		int sum = 0;
		if(host != null)
		{
			sum++;
		}
		
		if(guest != null)
		{
			sum++;
		}
		
		return sum;
	}

	public List<String> getUsernamePlayers()
	{
		List<String> players = new ArrayList<>();
		players.add(host);
		players.add(guest);
		
		return players;		
	}

	
	
	
	
	
}
