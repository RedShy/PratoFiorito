package pratofiorito.components.controllers;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pratofiorito.components.services.Event;
import pratofiorito.components.services.EventsService;
import pratofiorito.components.services.LobbyService;
import pratofiorito.domain.Game;

@Controller
public class GameController
{
	@Autowired
	LobbyService lobbyService;

	@Autowired
	EventsService eventsService;

	@GetMapping("clickLeft")
	@ResponseBody
	public String clickLeft(@RequestParam int x, @RequestParam int y, @RequestParam String player,Model model, HttpSession session)
	{
		
		String lobbyTitle = (String) session.getAttribute("lobbyTitle");
		// TODO: spostare logica dal controller ad un game service
		Game game = lobbyService.getLobbyByTitle(lobbyTitle).getGame();
		
		//se non è il turno del giocatore che ha fatto il click non fare nulla
		if(!player.equals(game.getTurn()))
		{
			return "notYourTurn";
		}
		
		// apro la cella
		game.openCell(x, y);

		game.changeTurn();

		// TODO evento da aggiustare
		// inserisco l'evento per tutti gli utenti della lobby
		lobbyService.notifyEventToAllInLobby(Event.CLICK_LEFT, lobbyTitle, (String) session.getAttribute("user"));

		return "clickLeftDone";
	}

	@GetMapping("clickRight")
	@ResponseBody
	public String clickRight(@RequestParam int x, @RequestParam int y, @RequestParam String player, Model model, HttpSession session)
	{
		String lobbyTitle = (String) session.getAttribute("lobbyTitle");
		// TODO: spostare logica dal controller ad un game service
		Game game = lobbyService.getLobbyByTitle(lobbyTitle).getGame();
		
		//se non è il turno del giocatore che ha fatto il click non fare nulla
		if(!player.equals(game.getTurn()))
		{
			return "notYourTurn";
		}

		game.placeRemoveFlag(x, y);

		game.changeTurn();

		// TODO evento da aggiustare
		// inserisco l'evento per tutti gli utenti della lobby
		lobbyService.notifyEventToAllInLobby(Event.CLICK_RIGHT, lobbyTitle, (String) session.getAttribute("user"));

		return "clickRightDone";
	}

	@GetMapping("game")
	public String refreshGame(HttpSession session, Model model)
	{
		Game game = lobbyService.getLobbyByTitle((String) session.getAttribute("lobbyTitle")).getGame();

		model.addAttribute("game", game);
		
		// TODO: si potrebbe migliorare. passo anche se è un host oppure no, per gestire
		// il cambio turno
		model.addAttribute("playerType", (String) session.getAttribute("playerType"));

		// controllo se il gioco è finito
		if (game.won())
		{
			// TODO: messaggio di vincita, salvare nel database la partita finita e poi
			// ritornare alla mainpage
			model.addAttribute("gameEnded", "win");
		} else if (game.lost())
		{
			// TODO: messaggio di perdita, salvare nel database la partita finita e poi
			// ritornare alla mainpage
			model.addAttribute("gameEnded", "lose");
		}

		return "game";
	}

	@GetMapping("exitGame")
	public String exitGame()
	{
		// TODO: da controllare
		// se sono host ritorno alla lobby

		// se sono guest ritorno alla main page

		return "redirect:/mainPage";
	}
}
