package pratofiorito.components.persistence;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratofiorito.domain.Match;
import pratofiorito.domain.User;

@Repository
public class MatchDAO
{

	@Autowired
	private DBManager dbManager;
	
	@PostConstruct
	public void init() {
		User master = new User("mario", "1234", "Mario", "Mille", "Italia");
		User teammate = new User("gianni", "1234", "Gianni", "Mille", "Italia");
		dbManager.save(teammate);
		Match match= new Match();
		match.setOwner(master);
		match.setTeammate(teammate.getUsername());
		match.setDate(new Date());
		match.setDifficulty(1);	
		save(match);
	}
	
	public boolean save(Match match) {
		return dbManager.save(match);
	}

	



}
