package pratofiorito.components.persistence;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import pratofiorito.domain.Match;
import pratofiorito.domain.User;

@Repository
public class UserDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DBManager dbManger;

	public boolean save(User user)
	{
		return dbManger.save(user);
	}

	public boolean exists(User user)
	{
		Session openSession = sessionFactory.openSession();
		Query<User> query = openSession
				.createQuery("from User  where us.username=:u and us.password=:p", User.class)
				.setParameter("u", user.getUsername()).setParameter("p", user.getPassword());

		boolean result = query.uniqueResult() != null;
		openSession.close();
		return result;
	}

	public User getUser(String username)
	{
		Session openSession = sessionFactory.openSession();
		Query<User> query = openSession.createQuery("from User as us where us.username=:u ", User.class)
				.setParameter("u", username);
		return query.uniqueResult();
	}
	
	public User getUserJoined(String username)
	{
		Session openSession = sessionFactory.openSession();
		Query<User> query = openSession.createQuery("from User as us JOIN FETCH us.matches where us.username=:u ", User.class)
				.setParameter("u", username);
		return query.uniqueResult();
	}
	
	
	
	public List<Match> getAllMatches(String username) {
		Session session = sessionFactory.openSession();
		//Lazy loading issue:
		//To initialize a list of a relation use on of the following
		// - JOIN FETCH,
		// - Hibernate.initialize(list)
		// - call any list method		
		Query<Match> select = session.createQuery("FROM Match as m JOIN FETCH m.owner where m.owner=:o or m.teammate=:t", Match.class)
				.setParameter("o", getUserJoined(username)).setParameter("t", username);
		List<Match> list = select.list();
		
		session.close(); 
		return list;

	}
	
	public void register(String username, String password)
	{
		
		 User u1= new User(); 
		 u1.setUsername(username); 
		 u1.setPassword(password); 
		 save(u1);
		 
	}


}
