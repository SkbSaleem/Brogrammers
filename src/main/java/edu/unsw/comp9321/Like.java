package edu.unsw.comp9321;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Like {

	
	HibernateHelper hh = new HibernateHelper();
	
	public String addLike (String like_value, String post_id) {
		
		Session session = hh.getSessionFactory().openSession();
		Transaction tt = session.beginTransaction();
		
		
		
		Query queryResult = session.createSQLQuery("SELECT Likes from posts where PostId = :id");
		System.out.println(post_id);
		queryResult.setParameter("id", post_id);
		Integer likes = (Integer) queryResult.uniqueResult();
		tt.commit();
		System.out.println("Likes are" + likes);
		
		
		likes = likes + 1;
		tt = session.beginTransaction();
		Query query = session.createQuery("update PostPojo set likes = :likes where PostId = :id");
		query.setParameter("likes", likes);
		query.setParameter("id", post_id);
		int result = query.executeUpdate();
		tt.commit();
		
		
		session.close();
		return null;
		
		
		
		
		
	}
	
}
