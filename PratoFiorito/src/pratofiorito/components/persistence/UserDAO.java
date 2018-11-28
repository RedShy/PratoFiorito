package pratofiorito.components.persistence;

import javax.annotation.PostConstruct;

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

	@PostConstruct
	public void init()
	{
		save(new User("mario1000", "1234", "mario", "mille", "italia"));
		/*
		 * User u1= new User(); u1.setUsername("ciccio");
		 * u1.setPassword("ciccio"); save(u1); User u2= new User();
		 * u2.setUsername("franco"); u2.setPassword("franco"); save(u1);
		 */
	}

	public boolean save(User user)
	{
		Session session = sessionFactory.openSession();
		boolean error = false;
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();

		} catch (Exception e)
		{
			tx.rollback();
			error = true;
		}
		session.close();
		return error;
	}

	public boolean exists(User user)
	{
		Session openSession = sessionFactory.openSession();
		Query<User> query = openSession
				.createQuery("from User as us where us.username=:u and us.password=:p", User.class)
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
		System.out.println("UTENTE: " + query.uniqueResult().getFirst_name() + " " +  query.uniqueResult().getLast_name() + " " +  query.uniqueResult().getCountry());
		return query.uniqueResult();
	}

}