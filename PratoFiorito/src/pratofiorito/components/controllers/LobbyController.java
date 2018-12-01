package pratofiorito.components.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pratofiorito.components.persistence.MatchDAO;
import pratofiorito.components.services.Event;
import pratofiorito.components.services.EventsService;
import pratofiorito.components.services.LobbyService;
import pratofiorito.components.services.MatchService;
import pratofiorito.components.services.UserService;
import pratofiorito.domain.Lobby;
import pratofiorito.domain.Match;

@Controller
public class LobbyController
{
	@Autowired
	LobbyService lobbyService;
	
	@Autowired
	EventsService eventsService;
	
	@Autowired
	MatchDAO matchDAO;
	
	@Autowired
	UserService userService;

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
		Lobby currentLobby= lobbyService.getLobbyByTitle(lobbyTitle);
		if (currentLobby.getGuest() == null)
		{
			// errore non puoi iniziare il gioco se non � presente il guest
			model.addAttribute("noGuest", "error");

			// rimani nella lobby
			return "forward:/lobby";
		}


		Match m = new Match();
		m.setDate(new Date());
		m.setDifficulty(1);
		m.setOwner(userService.getUser(currentLobby.getHost()));
		m.setTeammate(currentLobby.getGuest());
		boolean can_create_match = matchDAO.save(m);
		
		if(can_create_match) {
			lobbyService.createGame(lobbyTitle, size, bombs , m);
			lobbyService.notifyEventToAllInLobby(new Event(Event.GAME_STARTED).toJSON(), lobbyTitle, (String) session.getAttribute("user"));
		}else {
			// errore non puoi iniziare il gioco se non posso salvare il match
			model.addAttribute("noGuest", "error");
			// rimani nella lobby
			return "forward:/lobby";
		}
		return "redirect:/game";
	}

	@GetMapping("exitLobby")
	public String exitLobby(HttpSession session)
	{
		String lobbyTitle = (String) session.getAttribute("lobbyTitle");
		String playerType = (String) session.getAttribute("playerType");
		String sender = (String) session.getAttribute("user");

		// 1. Devo capire se sono host o sono guest e fare l'azione opportuna
		if (playerType.equals("host"))
		{
			lobbyService.notifyEventToAllInLobby(new Event(Event.HOST_LEAVED).toJSON(), lobbyTitle, sender);

			// Se sono host, rimuovo la lobby
			lobbyService.removeLobby(lobbyTitle);
			
			//TODO eventi mainPage
			try {
				for(String user : eventsService.getUsers()) {
					if(user != sender)
						eventsService.insertEvent(user, new Event(Event.REMOVED_LOBBY,lobbyTitle).toJSON());
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
		{
			lobbyService.notifyEventToAllInLobby(new Event(Event.GUEST_LEAVED).toJSON(), lobbyTitle,sender);
			lobbyService.removeGuestFromLobby(lobbyTitle);
		}

		// 2. Ritorno alla mainpage

		// TODO: rimuovo la lobby e il tipo di giocatore dalla sessione
		session.setAttribute("lobbyTitle", null);
		session.setAttribute("playerType", null);

		return "redirect:/mainPage";
	}
}
