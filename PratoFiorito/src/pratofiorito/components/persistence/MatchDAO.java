package pratofiorito.components.persistence;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratofiorito.domain.User;

@Repository
public class MatchDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(Math match) {
		Session session = sessionFactory.openSession();

		Transaction tx = null;
		try {
			tx= session.beginTransaction();
			session.save(match);
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
		}
		session.close();
	}


}
