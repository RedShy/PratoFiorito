package pratofiorito.components.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	
	private Map<String, Long> matches = new HashMap<String, Long>();
	
	public void saveMatch(List<String> users, String lobby, String difficulty) {
 
		List<User> players = new ArrayList<User>();
		
		for (String user : users) {
			players.add(userDAO.getUser(user));
		}
		
		Match match = new Match(new Date(), difficulty);
		match.addUsers(players);
		
//		match.getUsers().add(user1);
//		match.getUsers().add(user2);
		
//		user1.getMatches().add(match);
//		user2.getMatches().add(match);
		
		matchDAO.save(match);
		
		for (User user : players) {
			userDAO.save(user);
		}
		
		matches.put(lobby, match.getId());
	}
	
	public void updateMatch(String lobby, Date date, String result) {
		
		Match match = matchDAO.getMatch(matches.get(lobby));
		
		match.setMatchTime(date);
		match.setResult(result);
		
		Set<User> users = match.getUsers();
		for (User user : users) {
			user.getMatches().add(match);
			user.setGames_played(user.getGames_played() + 1);
			if(match.getResult().equals("WON")) {
				user.setGames_won(user.getGames_won() + 1);
			}
			else if(match.getResult().equals("LOST")) {
				user.setGames_lost(user.getGames_lost() + 1);
			}
			else if(match.getResult().equals("ABANDONED")) {
				user.setGames_abandoned(user.getGames_abandoned() + 1);
			}
			userDAO.save(user);
		}
		
		matchDAO.save(match);
	}
}
