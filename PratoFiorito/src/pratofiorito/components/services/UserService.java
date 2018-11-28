package pratofiorito.components.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pratofiorito.components.persistence.UserDAO;
import pratofiorito.domain.User;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	public User getUser(String username) {
		User user = userDAO.getUser(username);
		return user;
	}
}
