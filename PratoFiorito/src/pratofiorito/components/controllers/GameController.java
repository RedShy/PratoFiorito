package pratofiorito.components.controllers;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pratofiorito.components.persistence.DBManager;
import pratofiorito.components.services.Event;
import pratofiorito.components.services.EventsService;
import pratofiorito.components.services.LobbyService;
import pratofiorito.domain.Game;
import pratofiorito.domain.Lobby;
import pratofiorito.domain.Match;

@Controller
public class GameController
{
	@Autowired
	LobbyService lobbyService;

	@Autowired
	EventsService eventsService;
	
	@Autowired
	DBManager dbmanager;

	@GetMapping("click")
	@ResponseBody
	public String clickLeftRight(@RequestParam int x, @RequestParam int y, @RequestParam String click,
			@RequestParam String player, HttpSession session)
	{
		String lobbyTitle = (String) session.getAttribute("lobbyTitle");
		Lobby l = lobbyService.getLobbyByTitle(lobbyTitle);
		Game game = l.getGame();

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
			saveTimePassed(l);
			gameStatus = "won";
			lobbyService.notifyEventToAllInLobby(new Event(Event.WON).toJSON(), lobbyTitle, sender);
		} else if (game.lost())
		{
			saveTimePassed(l);
			gameStatus = "lost";
			lobbyService.notifyEventToAllInLobby(new Event(Event.LOST).toJSON(), lobbyTitle, sender);
		}

		// al client passo: la lista delle celle modificate e lo stato del
		// gioco: vinto, perso, o continua
		return "{\"cells\":" + modifiedCellsJSON + ",\"gameStatus\":\"" + gameStatus + "\"}";
	}

	@GetMapping("game")
	public String refreshGame(HttpSession session, Model model)
	{
		Game game = lobbyService.getLobbyByTitle((String) session.getAttribute("lobbyTitle")).getGame();
		model.addAttribute("game", game);

		return "game";
	}

	@GetMapping("exitGame")
	public String exitGame(HttpSession session)
	{
		String playerType = (String) session.getAttribute("playerType");
		String lobbyTitle = (String) session.getAttribute("lobbyTitle");
		String sender = (String) session.getAttribute("user");
		saveTimePassed(lobbyService.getLobbyByTitle(lobbyTitle));
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
	
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
	
	void saveTimePassed(Lobby l) {
		Match m = l.getMatch();
		long minute_passed=getDateDiff(m.getDate(),new Date(),TimeUnit.MINUTES);
		m.setMatchTime((int)minute_passed);
		dbmanager.save(m);
	}
	
	
}
