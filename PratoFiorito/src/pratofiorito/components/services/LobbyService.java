package pratofiorito.components.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pratofiorito.domain.Lobby;

@Service
public class LobbyService
{
	// TODO fare locking concorrente
	private List<Lobby> lobbies = new ArrayList<>();

	public List<Lobby> getLobbies()
	{
		// TODO
		ArrayList<Lobby> list = new ArrayList<>();
		list.add(new Lobby("pierpaolo"));
		list.add(new Lobby("mastro"));
		list.add(new Lobby("provoeltta mastra"));
		return list;
	}

}
