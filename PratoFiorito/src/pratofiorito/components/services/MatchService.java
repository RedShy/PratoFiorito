package pratofiorito.components.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
	
	public void saveMatch(List<String> users) {
 
		List<User> players = new ArrayList<User>();
		
		for (String user : users) {
			players.add(userDAO.getUser(user));
		}
		
		Match match = new Match(new Date(), 1);
		match.addUsers(players);
		
//		match.getUsers().add(user1);
//		match.getUsers().add(user2);
		
//		user1.getMatches().add(match);
//		user2.getMatches().add(match);
		
		matchDAO.save(match);
		
		for (User user : players) {
			userDAO.save(user);
		}
	}
}
