package pratofiorito.components.controllers;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import pratofiorito.components.services.EventsService;
import pratofiorito.components.services.LobbyService;
import pratofiorito.domain.Lobby;

@Controller
public class MainPageController
{
	@Autowired
	LobbyService lobbyService;

	@Autowired
	EventsService eventsService;

	@GetMapping("mainPage")
	public String goMainPage(Model model)
	{
		List<Lobby> lobbies = lobbyService.getLobbies();
		model.addAttribute("lobbies", lobbies);

		return "mainPage";
	}

	@GetMapping("mainPage/updateLobbies")
	public String updateLobbies(Model model)
	{
		// TODO si potrebbe fare come richiesta AJAX anziché ricaricare la pagina
		return "redirect:/mainPage";
	}

	@GetMapping("createLobby")
	public String createLobby(@RequestParam String lobbyTitle, Model model, HttpSession session)
	{
		// TODO: potrebbe essere utile inserire l'host o guest come stato della sessione
		String username = (String) session.getAttribute("user");
		// controlla se puoi creare una lobby con questo nome
		if (lobbyService.createLobby(lobbyTitle))
		{
			// è possibile crearla, quindi entraci come host e vai alla lobby page
			lobbyService.joinLobbyAsHost(lobbyTitle, username);

			session.setAttribute("lobbyTitle", lobbyTitle);
			// TODO: uso eccessivo della sessione?
			session.setAttribute("playerType", "host");

			return "redirect:/lobby";

		}
		// TODO non è possibile creare una lobby con questo nome, comunicare errore
		model.addAttribute("titleTaken", "error");
		return "forward:/mainPage";
	}

	@GetMapping("joinLobby")
	public String joinLobby(@RequestParam String lobbyTitle, Model model, HttpSession session)
	{
		String username = (String) session.getAttribute("user");

		// controlla se la lobby esiste
		if (lobbyService.getLobbyByTitle(lobbyTitle) == null)
		{
			// lobby non esistente
			model.addAttribute("noLobby", "error");
			return "forward:/mainPage";
		}

		// controlla se la lobby è piena
		if (lobbyService.getLobbyByTitle(lobbyTitle).isFull())
		{
			// lobby piena
			model.addAttribute("fullLobby", "error");
			return "forward:/mainPage";
		}

		// entra nella lobby
		lobbyService.joinLobbyAsGuest(lobbyTitle, username);

		session.setAttribute("lobbyTitle", lobbyTitle);
		// TODO: uso eccessivo della sessione?
		session.setAttribute("playerType", "guest");
		
		//TODO da aggiustare
		//inserisco l'evento per tutti gli utenti della lobby
		for (String player : lobbyService.getLobbyByTitle((String) session.getAttribute("lobbyTitle")).getNamePlayers())
		{
			//tranne se stesso
			if(!player.equals((String) session.getAttribute("user")))
			{
				try
				{
					eventsService.insertEvent(player, "refreshPage");
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return "redirect:/lobby";
	}

	@GetMapping("exitMainPage")
	public String exitMainPage(HttpSession session)
	{
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("getEvents")
	@ResponseBody
	public DeferredResult<String> getEvents(HttpSession session)
	{
		DeferredResult<String> output = new DeferredResult<>();
		ForkJoinPool.commonPool().submit(() -> {
			try
			{
				System.out.println("SONO "+(String)session.getAttribute("user")+" CERCO DI PRENDERE EVENTO");
				
				output.setResult(eventsService.nextEvent((String)session.getAttribute("user")));
				
				System.out.println("SONO "+(String)session.getAttribute("user")+" EVENTO PRESO EVENTO: "+output);
			} catch (InterruptedException e)
			{
				output.setResult("An error occurred during event retrieval");
			}
		});

		return output;
	}
}
