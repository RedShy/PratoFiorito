package pratofiorito.components.controllers;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pratofiorito.components.persistence.UserDAO;
import pratofiorito.domain.Credentials;
import pratofiorito.domain.User;



@Controller
public class RegistrationController {
	@Autowired
	UserDAO userManager;

	@GetMapping("register")
	public String registerAttempt(@RequestParam String username, @RequestParam String password, Model model, HttpSession session)
	{
		User u = userManager.getUser(username);
		if(u==null) {
			session.setAttribute("user", username);
			userManager.register(username, password);
			return "redirect:/mainPage";
		}
		model.addAttribute("error","Utente già esistente");
		
		//registrazione fallita
		//TODO inviare un messaggio di errore all'utente
		return "registration";
	}
	
	@GetMapping("check")
	@ResponseBody
	public String check(@RequestParam String username)
	{
		User u = userManager.getUser(username);
		if(u==null) {
			return "OK";
		}
		return "KO";
		
	}
	
}
