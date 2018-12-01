package pratofiorito.components.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pratofiorito.components.persistence.MatchDAO;
import pratofiorito.components.persistence.UserDAO;
import pratofiorito.domain.Match;
import pratofiorito.domain.User;

@Service
public class MatchService {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private MatchDAO matchDAO;
	
	public void saveMatch(String host, String guest) {
 
		User user1 = userDAO.getUser(host);
		User user2 = userDAO.getUser(guest);
		
		Match match = new Match(new Date(), 1);
		match.addUsers(user1, user2);
//		match.getUsers().add(user1);
//		match.getUsers().add(user2);
		
//		user1.getMatches().add(match);
//		user2.getMatches().add(match);
		
		matchDAO.save(match);
		userDAO.save(user1);
		userDAO.save(user2);
	}
}
