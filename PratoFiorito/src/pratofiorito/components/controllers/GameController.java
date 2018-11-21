package pratofiorito.components.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pratofiorito.components.services.LobbyService;
import pratofiorito.domain.Game;

@Controller
public class GameController
{
	@Autowired
	LobbyService lobbyService;
	
	@GetMapping("clickLeft")
	@ResponseBody
	public String clickLeft(@RequestParam int x, @RequestParam int y, HttpSession session)
	{
		//devo aprire la cella corrispondente
		//1. prendo il gioco
		Game game = lobbyService.getLobbyByTitle((String) session.getAttribute("lobbyTitle")).getGame() ;
		
		//TODO: comunicare all'altro giocatore che hai aperto la cella
		if(game.getCell(x, y) == 0)
		{
			//TODO: comunicare alla pagina HTML che hai aperto la cella e il numero di bombe adiacenti
			//TODO: Opazionale: se la cella ha un numero di bombe adiacenti 0 apri le 8 celle adiacenti
			game.openCell();
			
			// Controlla se hai vinto
			if(game.win())
			{
				// TODO: messaggio di vincita, salvare nel database la partita finita e poi ritornare alla mainpage
				return "redirect:/mainPage";
			}
			
			
		}
		else
		{
			// è una bomba hai perso
			//TODO: messaggio di perdita, salvare nel database la partita finita e poi ritornare alla mainpage
			return "redirect:/mainPage";
		}
		return null;
	}
}
