package edu.unsw.comp9321;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class HibernateHelper
{
	private static final SessionFactory sessionFactory = create_sessionFactory();
	
	public Configuration Configuration_method()
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		return cfg;
	}
	private static SessionFactory create_sessionFactory()
	{
		 //SessionFactory sf= Configuration_method().buildSessionFactory();
		 return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void close() {
		sessionFactory.close();
	}
}