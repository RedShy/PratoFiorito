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
	public String clickLeft(@RequestParam int x, @RequestParam int y, @RequestParam String player, HttpSession session)
	{
		String lobbyTitle = (String) session.getAttribute("lobbyTitle");
		Game game = lobbyService.getLobbyByTitle(lobbyTitle).getGame();

		// se non è il turno del giocatore che ha fatto il click non fare nulla
		if (!player.equals(game.getTurn()))
		{
			return "notYourTurn";
		}

		// apro la cella
		game.openCell(x, y);

		game.changeTurn();

		String modifiedCellsJSON = game.getModifiedCellsJSON();

		String sender = (String) session.getAttribute("user");
		// invio le celle modificate all'altro giocatore
		lobbyService.notifyEventToAllInLobby(modifiedCellsJSON, lobbyTitle, sender);

		String gameStatus = "continue";
		// controllo se la partita è finita oppure no
		if (game.won())
		{
			gameStatus = "won";
			lobbyService.notifyEventToAllInLobby(Event.WON, lobbyTitle, sender);
		} else if (game.lost())
		{
			gameStatus = "lost";
			lobbyService.notifyEventToAllInLobby(Event.LOST, lobbyTitle, sender);
		}

		// al client passo due cose: la lista delle celle modificate e lo stato del
		// gioco: vinto, perso, o continua
		return "{\"cells\":" + modifiedCellsJSON + ",\"gameStatus\":\"" + gameStatus + "\"}";
	}

	@GetMapping("clickRight")
	@ResponseBody
	public String clickRight(@RequestParam int x, @RequestParam int y, @RequestParam String player, HttpSession session)
	{
		String lobbyTitle = (String) session.getAttribute("lobbyTitle");
		Game game = lobbyService.getLobbyByTitle(lobbyTitle).getGame();

		// se non è il turno del giocatore che ha fatto il click non fare nulla
		if (!player.equals(game.getTurn()))
		{
			return "notYourTurn";
		}

		game.placeRemoveFlag(x, y);

		game.changeTurn();

		String modifiedCellsJSON = game.getModifiedCellsJSON();

		// invio le celle modificate all'altro giocatore
		lobbyService.notifyEventToAllInLobby(modifiedCellsJSON, lobbyTitle, (String) session.getAttribute("user"));

		return modifiedCellsJSON;
	}

	@GetMapping("game")
	public String refreshGame(HttpSession session, Model model)
	{
		Game game = lobbyService.getLobbyByTitle((String) session.getAttribute("lobbyTitle")).getGame();
		model.addAttribute("game", game);

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
