package pratofiorito.components.services;

import org.springframework.stereotype.Service;

import pratofiorito.domain.Credentials;


@Service
public class LoginService
{

	public boolean loginAttempt(Credentials credentials)
	{
		//TODO: da implementare
		//1. Controlla che le credenziali siano effettivamente presenti nel database
		return true;
	}
	

	public boolean registerAttempt(Credentials credentials)
	{
		//TODO: fare registrazione
		return true;
	}


}
