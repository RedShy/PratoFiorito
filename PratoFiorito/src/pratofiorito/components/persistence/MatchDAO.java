package pratofiorito.components.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

//@Repository
public class MatchDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	public void save(Math match)
	{
		Session session = sessionFactory.openSession();

		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			session.save(match);
			tx.commit();

		} catch (Exception e)
		{
			tx.rollback();
		}
		session.close();
	}

}
