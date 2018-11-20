package pratofiorito.components.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import pratofiorito.domain.Game;
import pratofiorito.domain.Lobby;

@Service
public class LobbyService
{
	// TODO fare locking concorrente
	private List<Lobby> lobbies = new ArrayList<>();

	public List<Lobby> getLobbies()
	{
		// TODO
		if (lobbies.isEmpty())
		{
			Random rand = new Random();
			ArrayList<Lobby> list = new ArrayList<>();
			list.add(new Lobby("pierpaolo" + rand.nextInt(10)));
			list.add(new Lobby("mastro" + rand.nextInt(10)));
			list.add(new Lobby("provoeltta mastra" + rand.nextInt(10)));
			return list;
		}
		return lobbies;
	}

	public boolean joinGuest(String lobbyTitle, String username)
	{
		Lobby lobbyToJoin = getLobbyByTitle(lobbyTitle);

		return lobbyToJoin.joinGuest(username);
	}

	public boolean joinHost(String lobbyTitle, String username)
	{
		Lobby lobbyToJoin = getLobbyByTitle(lobbyTitle);

		return lobbyToJoin.joinHost(username);
	}

	public boolean createLobby(String lobbyTitle)
	{
		if (getLobbyByTitle(lobbyTitle) == null)
		{
			lobbies.add(new Lobby(lobbyTitle));

			return true;
		}
		return false;
	}

	public Lobby getLobbyByTitle(String lobbyTitle)
	{
		for (Lobby lobby : lobbies)
		{
			if (lobby.getTitle().equals(lobbyTitle))
			{
				return lobby;
			}
		}

		return null;
	}

	public void removeGuestFromLobby(String lobbyTitle)
	{
		getLobbyByTitle(lobbyTitle).removeGuest();
	}

	public void removeLobby(String lobbyTitle)
	{
		lobbies.remove(getLobbyByTitle(lobbyTitle));
	}

	public void createGame(String lobbyTitle, int size, int bombs)
	{
		getLobbyByTitle(lobbyTitle).setGame(new Game(size,bombs));
	}

}
