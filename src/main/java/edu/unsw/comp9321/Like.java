package edu.unsw.comp9321;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

public class Like {

	
	HibernateHelper hh = new HibernateHelper();
	
	public void addLike (String username, int post_id) {
		
		Session session = hh.getSessionFactory().openSession();
		Transaction tt = null;
		boolean failed = false;
		try{
			Query qq = session.createSQLQuery("INSERT INTO likes (PostId, UserName) values(:postId, :username)")
					.setParameter("postId", post_id).setParameter("username", username);
			tt = session.beginTransaction();
			qq.executeUpdate();
			tt.commit();
		}
		catch(ConstraintViolationException e) {
			failed = true;
			if(tt != null) {
				tt.rollback();
			}
			
		}
		finally {
			session.close();
		}
		
		if(failed==true) {
			
			deleteLike(username, post_id);
		}
		/*Query queryResult = session.createSQLQuery("SELECT Likes from posts where PostId = :id");
		queryResult.setParameter("id", post_id);
		Integer likes = (Integer) queryResult.uniqueResult();
		tt.commit();
		System.out.println("Likes are" + likes);
		
		
		likes = likes + 1;
		tt = session.beginTransaction();
		Query query = session.createQuery("update PostPojo set likes = :likes where PostId = :id");
		query.setParameter("likes", likes);
		query.setParameter("id", post_id);
		int result = query.executeUpdate();*/
		Session session3 = hh.getSessionFactory().openSession();
		Transaction tt3 = session3.beginTransaction();
		
		Integer likes =  ((BigInteger) session3.createSQLQuery("SELECT COUNT(l.PostId) FROM likes l WHERE l.PostId = :postid")
				.setParameter("postid", post_id).uniqueResult()).intValue();
		
		PostPojo post = (PostPojo) session3.get(PostPojo.class, post_id);
		post.setLikes(likes);
		session3.merge(post);
		tt3.commit();
		session3.close();
		//return null;
		
	}
	public void deleteLike(String username, int postid) {
		Session session = hh.getSessionFactory().openSession();
		Transaction tt =session.beginTransaction();
		Query unlike = session.createSQLQuery("DELETE FROM likes WHERE PostId = :postid && UserName = :username")
				.setParameter("postid", postid).setParameter("username", username);
		unlike.executeUpdate();
		tt.commit();
		session.close();
	}
	
}
