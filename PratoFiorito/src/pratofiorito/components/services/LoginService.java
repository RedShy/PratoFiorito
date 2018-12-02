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
			return false;
		}
		
		// controllo se la password è corretta
		if(passwordEncoder.matches(credentials.getPassword(),user.getPassword()))
		{
			return true;
		}
		
		return false;
	}
	
	public boolean registerAttempt(Credentials credentials, String name, String surname, String city)
	{
		//controllo se l'utente esiste già
		User user = userDAO.getUser(credentials.getUsername());
		if(user != null)
		{
			return false;
		}
		
		user = new User(credentials.getUsername(), passwordEncoder.encode(credentials.getPassword()), name, surname, city);
		userDAO.save(user);
		return true;
	}

}
