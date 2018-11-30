package pratofiorito.components.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pratofiorito.components.persistence.MatchDAO;
import pratofiorito.components.persistence.UserDAO;

import pratofiorito.domain.User;
@Service
public class MatchService {
	
	
	public boolean newMatch(int difficulty ,User host, User guest) {
		
		
		/*
		Match match = new Match(new Date(), 1);
		host.getMatches().add(match);
		guest.getMatches().add(match);
		match.getUsers().add(host);
		match.getUsers().add(guest);
		userDao.save(host);
		userDao.save(guest);
		//matchDAO.save(match);
		 * */
		 
		return true;
	}

}
