package in.demo.util;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import in.demo.model.Courses;

public class HibernateUtil{
	 public static org.hibernate.SessionFactory sessionFactory =null;
	 public static Session session =null;
	static {
		sessionFactory = new Configuration().configure().addAnnotatedClass(Courses.class).buildSessionFactory();
	}
	
	public static Session getSession() {
		if(session == null)
			return sessionFactory.openSession();
		else 
			return session;
	}
	
	public static void closeSession(Session session) {
		if(session!=null)
			session.close();
	}
	public static void closeSessionFactory() {
		if(sessionFactory!=null)
			sessionFactory.close();
	}
	
	
}