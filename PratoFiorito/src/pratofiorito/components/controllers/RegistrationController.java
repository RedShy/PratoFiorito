package pratofiorito.components.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pratofiorito.components.persistence.UserDAO;
import pratofiorito.components.services.LoginService;
import pratofiorito.components.services.UserService;
import pratofiorito.domain.Credentials;
import pratofiorito.domain.User;

@Controller
public class RegistrationController
{
	@Autowired
	UserService userService;

	@Autowired
	private LoginService loginService;

	@GetMapping("registration")
	public String registration()
	{
		return "registration";
	}

	@GetMapping("register")
	public String registerAttempt(@RequestParam String username, @RequestParam String password,
			@RequestParam String name, @RequestParam String surname, @RequestParam String city, Model model,
			HttpSession session)
	{
		if (loginService.registerAttempt(new Credentials(username, password),name,surname,city))
		{
			session.setAttribute("user", username);
			return "redirect:/";
		}
		// registrazione fallita
		model.addAttribute("error", "Utente già esistente");
		return "registration";
	}

	@GetMapping("check")
	@ResponseBody
	public String check(@RequestParam String username)
	{
		User u = userService.getUser(username);
		if (u == null)
		{
			return "OK";
		}
		return "KO";
	}

}
