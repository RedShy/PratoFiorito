package pratofiorito.components.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pratofiorito.components.services.EventsService;
import pratofiorito.components.services.LoginService;
import pratofiorito.domain.Credentials;

@Controller
public class LoginController
{
	@Autowired
	private LoginService loginService;

	@GetMapping("/")
	public String home(HttpSession session)
	{
		if (session.getAttribute("user") == null)
		{
			//Utente non presente, fallo loggare o registrare
			return "login";
		}
		//Utente presente vai alla mainPage
		return "redirect:/mainPage";
		
	}

	@GetMapping("login")
	public String loginAttempt(@RequestParam String username, @RequestParam String password, Model model, HttpSession session)
	{
		if(loginService.loginAttempt(new Credentials(username,password)))
		{
			session.setAttribute("user", username);
			return "redirect:/";
		}
		//login fallito
		//TODO inviare un messaggio di errore all'utente
		return "login";
	}
	
	
	@GetMapping("registration")
	public String registration()
	{
		//Utente presente vai alla mainPage
		return "registration";
		
	}

	
	
}
