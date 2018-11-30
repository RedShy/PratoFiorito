package pratofiorito.components.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pratofiorito.components.persistence.UserDAO;
import pratofiorito.components.services.UserService;
import pratofiorito.domain.User;

@Controller
public class UserController {
	
	@Autowired
	private UserDAO userDao;
	
	@GetMapping("userProfile")
	public String profile(HttpSession session, Model model) {
		if(session.getAttribute("user") != null) {
			User user = userDao.getUser((String) session.getAttribute("user"));
			if(user != null) {
				model.addAttribute("first_name", user.getFirst_name());
				model.addAttribute("last_name", user.getLast_name());
				model.addAttribute("country", user.getCountry());
				model.addAttribute("games_played", user.getGames_played());
				model.addAttribute("games_won", user.getGames_won());
				model.addAttribute("games_lost", user.getGames_lost());
				model.addAttribute("games_abandoned", user.getGames_abandoned());
				model.addAttribute("matches", userDao.getAllMatches(user.getUsername()));
			}
//			Gestire profilo non esistente
			return "user_profile";
		}
		return "redirect:/";
	}
	
}
