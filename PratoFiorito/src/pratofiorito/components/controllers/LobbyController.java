package pratofiorito.components.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pratofiorito.components.services.Event;
import pratofiorito.components.services.LobbyService;

@Controller
public class LobbyController
{
	@Autowired
	LobbyService lobbyService;

	@GetMapping("lobby")
	public String lobby(Model model, HttpSession session)
	{
//		model.addAttribute("playerType", session.getAttribute("playerType"));
		model.addAttribute("lobby", lobbyService.getLobbyByTitle((String) session.getAttribute("lobbyTitle")));

		return "lobby";
	}

	@GetMapping("startGame")
	public String startGame(@RequestParam int size, @RequestParam int bombs, @RequestParam String color, Model model,
			HttpSession session)
	{
		String lobbyTitle = (String) session.getAttribute("lobbyTitle");
		if (lobbyService.getLobbyByTitle(lobbyTitle).getGuest() == null)
		{
			// TODO: errore non puoi iniziare il gioco se non è presente il
			// guest
			model.addAttribute("noGuest", "error");

			// rimani nella lobby
			return "forward:/lobby";
		}

		lobbyService.createGame(lobbyTitle, size, bombs);

		// TODO evento da aggiustare
		// inserisco l'evento per tutti gli utenti della lobby
		lobbyService.notifyEventToAllInLobby(new Event(Event.GAME_STARTED).toJSON(), lobbyTitle, (String) session.getAttribute("user"));

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
			// TODO evento da aggiustare
			// inserisco l'evento per tutti gli utenti della lobby
			lobbyService.notifyEventToAllInLobby(new Event(Event.HOST_LEAVED).toJSON(), lobbyTitle, (String) session.getAttribute("user"));

			// Se sono host, rimuovo la lobby
			lobbyService.removeLobby(lobbyTitle);

		} else
		{
			//TODO
			lobbyService.notifyEventToAllInLobby(new Event(Event.GUEST_LEAVED).toJSON(), lobbyTitle, (String) session.getAttribute("user"));
			lobbyService.removeGuestFromLobby(lobbyTitle);
			
			// TODO evento da aggiustare
			// inserisco l'evento per tutti gli utenti della lobby

		}

		// 2. Ritorno alla mainpage

		// TODO: rimuovo la lobby e il tipo di giocatore dalla sessione
		session.setAttribute("lobbyTitle", null);
		session.setAttribute("playerType", null);

		return "redirect:/mainPage";
	}
}
