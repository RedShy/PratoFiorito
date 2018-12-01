package pratofiorito.components.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pratofiorito.domain.Game;
import pratofiorito.domain.Lobby;
import pratofiorito.domain.Match;

@Service
public class LobbyService
{
	@Autowired
	EventsService eventsService;

	// Le chiavi sono i nomi delle lobby che si assumono essere unici nel server
	private ConcurrentMap<String, Lobby> lobbies = new ConcurrentHashMap<>();

	public Collection<Lobby> getLobbies()
	{
		return lobbies.values();
	}

	public boolean joinLobbyAsGuest(String lobbyTitle, String username)
	{
		return getLobbyByTitle(lobbyTitle).joinGuest(username);
	}

	public boolean joinLobbyAsHost(String lobbyTitle, String username)
	{
		return getLobbyByTitle(lobbyTitle).joinHost(username);
	}

	public boolean createLobby(String lobbyTitle)
	{
		if (getLobbyByTitle(lobbyTitle) == null)
		{
			lobbies.put(lobbyTitle, new Lobby(lobbyTitle));

			return true;
		}
		return false;
	}

	public Lobby getLobbyByTitle(String lobbyTitle)
	{
		return lobbies.get(lobbyTitle);
	}

	public void removeGuestFromLobby(String lobbyTitle)
	{
		getLobbyByTitle(lobbyTitle).removeGuest();
	}

	public void removeLobby(String lobbyTitle)
	{
		lobbies.remove(lobbyTitle);
	}

	public void createGame(String lobbyTitle, int size, int bombs,Match match)
	{
		Lobby l = getLobbyByTitle(lobbyTitle);
		l.setGame(new Game(size, bombs));
		l.setMatch(match);
	}

	public void notifyEventToAllInLobby(String event, String lobbyTitle, String sender)
	{
		List<String> usernames = getLobbyByTitle(lobbyTitle).getUsernamePlayers();

		for (String player : usernames)
		{
			try
			{
				// non inviare l'evento anche a chi l'ha generato
				if (!player.equals(sender))
				{
					eventsService.insertEvent(player, event);
				}
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

}
