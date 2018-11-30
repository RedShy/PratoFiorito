package pratofiorito.components.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pratofiorito.components.persistence.UserDAO;
import pratofiorito.domain.Credentials;
import pratofiorito.domain.User;


@Service
public class LoginService
{
	@Autowired
	UserDAO userDAO;
	
	private PasswordEncoder passwordEncoder = new PasswordEncoder();
	
	public boolean loginAttempt(Credentials credentials)
	{
		User user = userDAO.getUser(credentials.getUsername());
		//1. Controllo se è presente l'utente
		if(user == null)
		{
			//TODO mettere a false
			return true;
		}
		
		// controllo se la password è corretta
		if(passwordEncoder.matches(credentials.getPassword(),user.getPassword()))
		{
			return true;
		}
		//TODO mettere a false
		return true;
	}
	
	public boolean registerAttempt(Credentials credentials)
	{
		//controllo se l'utente esiste già
		User user = userDAO.getUser(credentials.getUsername());
		if(user != null)
		{
			return false;
		}
		
		user = new User();
		user.setUsername(credentials.getUsername());
		user.setPassword(passwordEncoder.encode(credentials.getPassword()));
		userDAO.save(user);
		
		return true;
	}


}
