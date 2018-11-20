package pratofiorito.components.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pratofiorito.components.services.LobbyService;
import pratofiorito.domain.Lobby;

@Controller
public class MainPageController
{
	@Autowired
	LobbyService lobbyService;

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

			model.addAttribute("lobbyTitle",lobbyTitle);
			model.addAttribute("playerType","host");
			
			//TODO URL sporco
			return "lobby";
			
		}
		// TODO non è possibile creare una lobby con questo nome, comunicare errore
		return "redirect:/mainPage";
	}

	@GetMapping("joinLobby")
	public String joinLobby(@RequestParam String lobbyTitle, Model model, HttpSession session)
	{
		String username = (String) session.getAttribute("user");

		// prova ad entrare in una lobby
		if (lobbyService.joinLobbyAsGuest(lobbyTitle, username))
		{
			// sono entrato nella lobby
			
			model.addAttribute("lobbyTitle",lobbyTitle);
			model.addAttribute("playerType","guest");
			
			//TODO URL sporco
			return "lobby";
		}

		// TODO comunica messaggio di errore lobby piena non puoi entrare
		return "redirect:/mainPage";
	}
}
