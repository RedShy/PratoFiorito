package pratofiorito.components.services;

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

	private Map<String, User> users; //nel DAO
	
	User user1 = new User("mario", "1234", "Mario", "Mille", "Italia");
	User user2 = new User("gianni", "1234", "Gianni", "Mille", "Italia");
	User user3 = new User("pippo", "1234", "Gianni", "Mille", "Italia");
	Match match = new Match(new Date(), 1);
	Match match2 = new Match(new Date(), 2);
	
	@PostConstruct
	public void init() {
		this.users = new HashMap<String, User>();
		user1.getMatches().add(match);//prendere solo i match in cui c'è l'user
		user1.getMatches().add(match2);
		user2.getMatches().add(match);
		user2.getMatches().add(match2);
		user3.getMatches().add(match);
		match.getUsers().add(user1);
		match.getUsers().add(user2);
		match.getUsers().add(user3);
		match2.getUsers().add(user1);
		match2.getUsers().add(user2);
		userDAO.save(user1);
		userDAO.save(user2);
		userDAO.save(user3);
		matchDAO.save(match);
		matchDAO.save(match2);
	}
	
	public User getUser(String username) {
		if(!users.containsKey(username)) {
			
			User user = userDAO.getUser(username);
			users.put(username, user);
		}
		return users.get(username);
	}

}
