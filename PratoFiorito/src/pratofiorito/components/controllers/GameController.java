package pratofiorito.components.controllers;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

//	@GetMapping("clickLeft2")
//	public String clickLeft2(@RequestParam int x, @RequestParam int y, HttpSession session)
//	{
//		// devo aprire la cella corrispondente
//		// 1. prendo il gioco
//		Game game = lobbyService.getLobbyByTitle((String) session.getAttribute("lobbyTitle")).getGame();
//
//		// TODO: comunicare all'altro giocatore che hai aperto la cella
//		if (game.getCell(x, y) == 0)
//		{
//			// TODO: comunicare alla pagina HTML che hai aperto la cella e il numero di
//			// bombe adiacenti
//			// TODO: Opazionale: se la cella ha un numero di bombe adiacenti 0 apri le 8
//			// celle adiacenti
////			game.openCell();
//
//			// Controlla se hai vinto
//			if (game.win())
//			{
//				// TODO: messaggio di vincita, salvare nel database la partita finita e poi
//				// ritornare alla mainpage
//				return "redirect:/mainPage";
//			}
//
//		} else
//		{
//			// è una bomba hai perso
//			// TODO: messaggio di perdita, salvare nel database la partita finita e poi
//			// ritornare alla mainpage
//			return "redirect:/mainPage";
//		}
//		return null;
//	}

	@GetMapping("clickLeft")
	public String clickLeft(@RequestParam int x, @RequestParam int y, Model model, HttpSession session)
	{
		Game game = lobbyService.getLobbyByTitle((String) session.getAttribute("lobbyTitle")).getGame();
		//TODO: spostare logica dal controller ad un game service
		
		// apro la cella
		game.openCell(x, y);
		
		game.changeTurn();
		
		// è una bomba? se sì ho perso, se no, controllo se ho vinto
		if (game.isBomb(x, y))
		{
			// ho perso
			// TODO: messaggio di perdita, salvare nel database la partita finita e poi
			// ritornare alla mainpage
			model.addAttribute("gameEnded","lose" );
//			return "redirect:/mainPage";
		} else
		{
			// controllo se ho vinto
			if (game.win())
			{
				// ho vinto
				// TODO: messaggio di vincita, salvare nel database la partita finita e poi
				// ritornare alla mainpage
				model.addAttribute("gameEnded","win" );
//				return "redirect:/mainPage";
			}
		}

		//TODO: gestire eventi
		try
		{
			//inserisco l'evento per tutti gli utenti della lobby
			for (String player : lobbyService.getLobbyByTitle((String) session.getAttribute("lobbyTitle")).getNamePlayers())
			{
				//tranne se stesso
				if(!player.equals((String) session.getAttribute("user")))
				{
					eventsService.insertEvent(player, "refreshPage");
				}
			}
			
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/game";
	}

	@GetMapping("clickRight")
	public String clickRight(@RequestParam int x, @RequestParam int y, Model model, HttpSession session)
	{
		Game game = lobbyService.getLobbyByTitle((String) session.getAttribute("lobbyTitle")).getGame();

		game.placeRemoveFlag(x, y);
		
		game.changeTurn();

		return "redirect:/game";
	}

	@GetMapping("game")
	public String refreshGame(HttpSession session, Model model)
	{
		Game game = lobbyService.getLobbyByTitle((String) session.getAttribute("lobbyTitle")).getGame();

		model.addAttribute("game", game);
		model.addAttribute("playerType",(String) session.getAttribute("playerType"));
		
		//TODO: faccio così perché l'altro giocatore sa se la partita è finita oppure no
		if(game.win())
		{
			model.addAttribute("gameEnded", "win");
		}
		else if(game.lost())
		{
			model.addAttribute("gameEnded", "lose");
		}
		
		return "game";
	}
	
	@GetMapping("exitGame")
	public String exitGame()
	{
		//TODO: da controllare
		//se sono host ritorno alla lobby
		
		//se sono guest ritorno alla main page
		
		return "redirect:/mainPage";
	}
}
