package pratofiorito.components.controllers;

import java.util.Date;

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
import pratofiorito.components.services.MatchService;
import pratofiorito.domain.Game;

@Controller
public class GameController
{
	@Autowired
	LobbyService lobbyService;

	@Autowired
	EventsService eventsService;
	
	@Autowired
	MatchService matchService;

	@GetMapping("click")
	@ResponseBody
	public String clickLeftRight(@RequestParam int x, @RequestParam int y, @RequestParam String click,
			@RequestParam String player, HttpSession session)
	{
		String lobbyTitle = (String) session.getAttribute("lobbyTitle");
		Game game = lobbyService.getLobbyByTitle(lobbyTitle).getGame();

		// se non è il turno del giocatore che ha fatto il click non fare nulla
		if (!player.equals(game.getTurn()))
		{
			return "notYourTurn";
		}

		if (click.equals("clickLeft"))
		{
			// apri la cella solo se era chiusa
			if (game.isClosed(x, y))
			{
				game.openCell(x, y);
			} else
			{
				return "cannotOpenCell";
			}
		} else if (click.equals("clickRight"))
		{
			// piazzo o rimuovo una bandiera solo se la cella era chiusa oppure era già una
			// bandiera
			if (game.isClosed(x, y) || game.isFlag(x, y))
			{
				game.placeRemoveFlag(x, y);
			} else
			{
				return "cannotPlaceFlag";
			}
		}

		game.changeTurn();

		// invio le celle modificate all'altro giocatore
		String sender = (String) session.getAttribute("user");
		String modifiedCellsJSON = game.getModifiedCellsJSON();
		lobbyService.notifyEventToAllInLobby(new Event(Event.CLICK_LEFT, modifiedCellsJSON).toJSON(), lobbyTitle,
				sender);

		// controllo se la partita è finita oppure no
		String gameStatus = "continue";
		if (game.won())
		{
			gameStatus = "won";
			lobbyService.notifyEventToAllInLobby(new Event(Event.WON).toJSON(), lobbyTitle, sender);
			matchService.updateMatch(lobbyTitle, new Date(), "WON");
		} else if (game.lost())
		{
			gameStatus = "lost";
			lobbyService.notifyEventToAllInLobby(new Event(Event.LOST).toJSON(), lobbyTitle, sender);
			matchService.updateMatch(lobbyTitle, new Date(), "LOST");
		}

		// al client passo: la lista delle celle modificate e lo stato del
		// gioco: vinto, perso, o continua
		return "{\"cells\":" + modifiedCellsJSON + ",\"gameStatus\":\"" + gameStatus + "\"}";
	}

	@GetMapping("game")
	public String refreshGame(HttpSession session, Model model)
	{
		model.addAttribute("lobby", lobbyService.getLobbyByTitle((String) session.getAttribute("lobbyTitle")));

		return "game";
	}

	@GetMapping("exitGame")
	public String exitGame(HttpSession session)
	{
		String playerType = (String) session.getAttribute("playerType");
		String lobbyTitle = (String) session.getAttribute("lobbyTitle");
		String sender = (String) session.getAttribute("user");

		matchService.updateMatch(lobbyTitle, new Date(), "ABANDONED");
		// se sono host ritorno alla lobby
		if (playerType.equals("host"))
		{
			// invio evento che l'host ha abbandonato
			lobbyService.notifyEventToAllInLobby(new Event(Event.HOST_LEAVED).toJSON(), lobbyTitle, sender);
			return "redirect:/lobby";
		}

		// se sono guest ritorno alla main page
		if (playerType.equals("guest"))
		{
			// invio evento che guest ha abbandonato
			lobbyService.notifyEventToAllInLobby(new Event(Event.GUEST_LEAVED).toJSON(), lobbyTitle, sender);

			// rimuovo il guest dalla lobby
			lobbyService.getLobbyByTitle(lobbyTitle).removeGuest();
		}

		
		return "redirect:/mainPage";
	}

	@GetMapping("ping")
	@ResponseBody
	public String ping(HttpSession session)
	{
		String lobbyTitle = (String) session.getAttribute("lobbyTitle");
		String sender = (String) session.getAttribute("user");

		lobbyService.notifyEventToAllInLobby(new Event(Event.PING).toJSON(), lobbyTitle, sender);

		return "helloFromServer";
	}

	@GetMapping("otherPlayerLostConnection")
	@ResponseBody
	public String otherPlayerLostConnection(HttpSession session)
	{
		String lobbyTitle = (String) session.getAttribute("lobbyTitle");
		String playerType = (String) session.getAttribute("playerType");

		String otherPlayer = "host";
		if (playerType.equals("host"))
		{
			otherPlayer = "guest";
		}

		if (otherPlayer.equals("host"))
		{
			// l'host ha perso la connessione
			lobbyService.removeLobby(lobbyTitle);
			return "mainPage";
		}

		// il guest ha perso la connessione
		lobbyService.removeGuestFromLobby(lobbyTitle);
		return "lobby";
	}
}
