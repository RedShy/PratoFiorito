package pratofiorito.components.services;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pratofiorito.components.persistence.UserDAO;
import pratofiorito.domain.User;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	private Map<String, User> users;
	
	@PostConstruct
	public void init() {
		this.users = new HashMap<String, User>();
	}
	
	public User getUser(String username) {
		if(!users.containsKey(username)) {
			User user = userDAO.getUser(username);
			users.put(username, user);
		}
		return users.get(username);
	}

}
