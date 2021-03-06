package pratofiorito.components.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratofiorito.domain.User;

@Repository
public class UserDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
//	private Map<String, User> users;

	@PostConstruct
	public void init()
	{
//		this.users = new HashMap<String, User>();
//		save(new User("mario", "1234", "mario", "mille", "italia"));
		/*
		 * User u1= new User(); u1.setUsername("ciccio");
		 * u1.setPassword("ciccio"); save(u1); User u2= new User();
		 * u2.setUsername("franco"); u2.setPassword("franco"); save(u1);
		 */
	}

	public boolean save(User user) //modificare con ista
	{
		Session session = sessionFactory.openSession();
		boolean error = false;
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			session.merge(user);
//			session.saveOrUpdate(user);//aggiornare lista users
			tx.commit();

		} catch (Exception e)
		{
			tx.rollback();
			error = true;
		}
		session.close();
		return error;
	}
	
	public void update(User user) {
		Session session = sessionFactory.openSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(user);
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
		}
		session.close();
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

	public User queryUser(String username) {		
		Session openSession = sessionFactory.openSession();
		Query<User> query = openSession.createQuery("from User as us JOIN FETCH us.matches where us.username=:u ", User.class)
				.setParameter("u", username);
		
		User u = query.uniqueResult();
		openSession.close();
		return u;
	}
	
	public User getUser(String username)
	{
		//if(!users.containsKey(username)) {
		Session openSession = sessionFactory.openSession();
		Query<User> query = openSession.createQuery("from User as us where us.username=:u ", User.class)
				.setParameter("u", username);
		
		User u = query.uniqueResult();
		openSession.close();
		return u;
			//users.put(username, user);
		//}
//		return users.get(username);
	}
	
	public void register(String username, String password)
	{
		
		 User u1= new User(); 
		 u1.setUsername(username); 
		 u1.setPassword(password); 
		 save(u1);
		 
	}


}
