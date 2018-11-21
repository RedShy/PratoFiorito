package pratofiorito.components.controllers;

import javax.servlet.http.HttpSession;

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
	
	@GetMapping("lobby")
	public String lobby(Model model, HttpSession session)
	{
		model.addAttribute("lobbyTitle",(String) session.getAttribute("lobbyTitle"));
		model.addAttribute("playerType",(String) session.getAttribute("playerType"));
		//TODO: da aggiustare
		model.addAttribute("lobby",lobbyService.getLobbyByTitle((String) session.getAttribute("lobbyTitle")));
		
		return "lobby";
	}

	@GetMapping("startGame")
	public String startGame(@RequestParam int size, @RequestParam int bombs, @RequestParam String color, Model model, HttpSession session)
	{
		String lobbyTitle = (String) session.getAttribute("lobbyTitle");
		if(lobbyService.getLobbyByTitle(lobbyTitle).getGuest() == null)
		{
			//TODO: messaggio errore non puoi iniziare il gioco se non è presente l'altro giocatore
			
			//rimani nella lobby
			model.addAttribute("noGuest", "error");
			return "forward:/lobby";
		}
		
		lobbyService.createGame(lobbyTitle, size, bombs);
//		model.addAttribute("game", lobbyService.getLobbyByTitle(lobbyTitle).getGame());
		
		return "redirect:/game";
	}

	@GetMapping("exitLobby")
	public String exitLobby(HttpSession session)
	{
		String lobbyTitle = (String) session.getAttribute("lobbyTitle");
		String playerType = (String) session.getAttribute("playerType");
		
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
		//TODO: rimuovo la lobby e il tipo di giocatore dalla sessione
		session.setAttribute("lobbyTitle", null);
		session.setAttribute("playerType", null);
		return "redirect:/mainPage";
	}
}
