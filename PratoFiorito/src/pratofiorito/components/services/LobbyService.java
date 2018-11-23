package pratofiorito.components.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import pratofiorito.domain.Game;
import pratofiorito.domain.Lobby;

@Service
public class LobbyService
{
	// TODO fare locking concorrente
	private List<Lobby> lobbies = new ArrayList<>();

	
	@PostConstruct
	public void init() {
		/*
		Lobby l = new Lobby("room1");
		l.setHost("Mico");
		lobbies.add(l);
		*/
	}
	
	
	public List<Lobby> getLobbies()
	{
		return lobbies;
	}

	public boolean joinLobbyAsGuest(String lobbyTitle, String username)
	{
		Lobby lobbyToJoin = getLobbyByTitle(lobbyTitle);

		return lobbyToJoin.joinGuest(username);
	}

	public boolean joinLobbyAsHost(String lobbyTitle, String username)
	{
		Lobby lobbyToJoin = getLobbyByTitle(lobbyTitle);
		return lobbyToJoin.joinHost(username);
	}

	public boolean createLobby(String lobbyTitle)
	{
		if (getLobbyByTitle(lobbyTitle) == null)
		{
			lobbies.add(new Lobby(lobbyTitle));
			System.out.println("can create lobby");
			return true;
		}
		System.out.println("can't create lobby");
		return false;
	}

	public Lobby getLobbyByTitle(String lobbyTitle)
	{
		for (Lobby lobby : lobbies)
		{
			System.out.println(lobby);
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
