package pratofiorito.components.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import pratofiorito.components.services.LobbyService;

@Controller
public class LobbyController
{
	@Autowired
	LobbyService lobbyService;

	@GetMapping("startGame")
	public String startGame(@RequestParam int size, @RequestParam int bombs, @RequestParam String color, @RequestParam String lobbyTitle, Model model)
	{
		lobbyService.createGame(lobbyTitle, size, bombs);
		model.addAttribute("game", lobbyService.getLobbyByTitle(lobbyTitle).getGame());
		
		return "game";
	}

	@GetMapping("exitLobby")
	public String exitLobby(@RequestParam String playerType, @RequestParam String lobbyTitle)
	{
		// 1. Devo capire se sono host o sono guest e fare l'azione opportuna
		if (playerType.equals("host"))
		{
			//Se sono host, rimuovo la lobby
			//TODO: comunicare ad un eventuale altro player che la lobby è stata distrutta
			lobbyService.removeLobby(lobbyTitle);
		} else
		{
			//TODO: comunicare all'host della lobby che il guest ha abbandonato la lobby
			lobbyService.removeGuestFromLobby(lobbyTitle);
		}

		// 2. Ritorno alla mainpage
		return "redirect:/mainPage";
	}
}
