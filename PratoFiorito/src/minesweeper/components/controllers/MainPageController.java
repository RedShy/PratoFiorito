package minesweeper.components.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import minesweeper.components.domain.Lobby;
import minesweeper.components.services.LobbyService;

@Controller
@RequestMapping("/mainPage")
public class MainPageController
{
	@Autowired
	LobbyService lobbyService;
	
	@GetMapping("/")
	public String goMainPage(Model model)
	{
		List<Lobby> lobbies = lobbyService.getLobbies();
		model.addAttribute("lobbies", lobbies);
		return "mainPage";
	}
	
	//TODO controllare se funziona il path relativo
	@GetMapping("/updateLobbies")
	public String updateLobbies(Model model)
	{
		//TODO fare come richiesta AJAX oppure non è necessario perché riupadte la pagina
		return "redirect:/mainPage";
	}
	
	@GetMapping("/createLobbies")
	public String createLobby(@RequestParam String lobbyTitle,Model model)
	{
		//1. controlla se puoi creare una lobby con questo nome
		return "mainPage";
	}
}
