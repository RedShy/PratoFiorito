package pratofiorito.components.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pratofiorito.components.persistence.UserDAO;

import pratofiorito.domain.User;


@Service
public class LoginService
{
	@Autowired
	private UserDAO userDAO;

	public boolean loginAttempt(User user)
	{
		return userDAO.exists(user);
	}
	

	public boolean registerAttempt(String username, String password)
	{
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		return userDAO.save(u);
	}


}
