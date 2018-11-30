package pratofiorito.components.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DBManager {

	@Autowired
	private SessionFactory sessionFactory;

	public boolean save(Object obj) {
		Session session = sessionFactory.openSession();
		boolean saved=true;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.merge(obj);
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			saved=false;
			e.printStackTrace();
		}
		session.close();
		return saved;
	}
}
