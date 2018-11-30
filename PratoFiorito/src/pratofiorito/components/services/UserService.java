package pratofiorito.components.services;

import org.springframework.stereotype.Service;


import java.util.Date;



import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pratofiorito.components.persistence.MatchDAO;
import pratofiorito.components.persistence.UserDAO;
import pratofiorito.domain.Match;
import pratofiorito.domain.User;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private MatchDAO matchDAO;

	private Map<String, User> users;
	
	User user1 = new User("mario", "1234", "Mario", "Mille", "Italia");
	User user2 = new User("gianni", "1234", "Gianni", "Mille", "Italia");
	
	@PostConstruct
	public void init() {
		this.users = new HashMap<String, User>();
		Match match = new Match(new Date(), 1);
		user1.getMatches().add(match);

		user2.getMatches().add(match);

		match.getUsers().add(user1);
		match.getUsers().add(user2);
		userDAO.save(user1);
		userDAO.save(user2);
		matchDAO.save(match);
	}
	
	public User getUser(String username) {
		User user = userDAO.getUser(username);
		return user;
		/*
		if(!users.containsKey(username)) {
			
			User user = userDAO.getUser(username);
			user.setMatches(matchDAO.getMatches());
			users.put(username, user);
		}
		return users.get(username);
		*/
	}


}
