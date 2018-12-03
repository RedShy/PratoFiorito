package pratofiorito.components.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;



import pratofiorito.components.persistence.UserDAO;
import pratofiorito.domain.Game;
import pratofiorito.domain.Lobby;
import pratofiorito.domain.User;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LobbyService
{
	@Autowired
	EventsService eventsService;
	
	@Autowired
	UserDAO userDAO;
	
	@PostConstruct
	private void init() {
		User user1 = new User("mario", "1234", "Mario", "Mille", "Italia");
		User user2 = new User("gianni", "1234", "Gianni", "Mille", "Italia");
		userDAO.save(user1);
		userDAO.save(user2);
	}

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

	public void createGame(String lobbyTitle, String difficulty)
	{
		getLobbyByTitle(lobbyTitle).setGame(new Game(difficulty));
	}

	public void notifyEventToAllInLobby(String event, String lobbyTitle, String sender)
	{
		Lobby lobby = getLobbyByTitle(lobbyTitle);
		if(lobby == null)
		{
			return;
		}
		List<String> usernames = lobby.getUsernamePlayers();

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
	
//	@Scheduled(fixedDelay=5000)
//	public void deleteBadLobbies() {
//		System.out.println("Removing bad lobbies" + new Date());
//		ArrayList<Lobby> lobbies = new ArrayList<>(getLobbies());
//		for (Lobby lobby : lobbies) {
//			System.out.print("chec lobby: "+lobby);
//			if(lobby.getHost()==null) {
//				System.out.print("Removed");
//				removeLobby(lobby.getTitle());
//			}
//			System.out.println("...");
//		}
//	}
	
}
