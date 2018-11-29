package pratofiorito.components.persistence;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratofiorito.domain.Match;
import pratofiorito.domain.User;

@Repository
public class MatchDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	public void save(Match match)
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
	
	/*public List<Match> getMatches()
	{
		Session session = sessionFactory.openSession();
	      Transaction tx = null;
	      List<Match> matches = null;
	      try {
	         tx = session.beginTransaction();
	         matches = (List<Match>) session.createQuery("from Match as m JOIN FETCH m.users", Match.class).list(); 
	         tx.commit();
	         session.close(); 
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }
	      return matches;
	}*/


}
