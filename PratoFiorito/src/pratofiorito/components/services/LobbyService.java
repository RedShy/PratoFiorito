package pratofiorito.components.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pratofiorito.domain.Lobby;


@Service
public class LobbyService
{
	//TODO fare locking concorrente
	private List<Lobby> lobbies = new ArrayList<>();
	
	public List<Lobby> getLobbies()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
