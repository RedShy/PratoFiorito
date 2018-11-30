package pratofiorito.components.services;

import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.Date;



import java.util.HashMap;
import java.util.List;
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
