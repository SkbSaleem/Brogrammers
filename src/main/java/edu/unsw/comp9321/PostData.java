package edu.unsw.comp9321;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.omg.PortableServer.POA;

public class PostData {
	HibernateHelper hh = new HibernateHelper();

	public void createPost(String content, String username) {
		Session session = hh.getSessionFactory().openSession();
		//Transaction tt = session.beginTransaction();
		Transaction tx = null;		
		//tt.commit();
		try {
			   tx = session.beginTransaction();
			   PostPojo post = new PostPojo();
				post.setContent(content);
				post.setLikes(0);
				//Credit credit = (Credit) request.getSession().getAttribute("credit");
				//post.setUsername(credit.getUsername());
				post.setUsername(username);
				post.setTimeposted(new Timestamp(System.currentTimeMillis()));
				session.persist(post);
			   tx.commit();
			}
			catch (Exception e) {
			   if (tx!=null) tx.rollback();
			   e.printStackTrace(); 
			}finally {
			   session.close();
			}

	}
	public void updatePost(int postId, String content) {
		Session session = hh.getSessionFactory().openSession();
		Transaction tx=null;		
		try {
			   tx = session.beginTransaction();
				//Query query = session.createSQLQuery("delete from posts where PostId = :postid").setParameter("postid", postId);
				Query query2 = session.createQuery("update PostPojo SET content= :content  where postid = :postid").
						setParameter("postid", postId).setParameter("content", content);

				query2.executeUpdate();
			   tx.commit();
			}
			catch (Exception e) {
			   if (tx!=null) tx.rollback();
			   e.printStackTrace(); 
			}finally {
			   session.close();
			}
	}
	public void deletePost(int postId) {
		Session session = hh.getSessionFactory().openSession();
		Transaction tx=null;		
		try {
			   tx = session.beginTransaction();
				//Query query = session.createSQLQuery("delete from posts where PostId = :postid").setParameter("postid", postId);
				Query query2 = session.createQuery("delete PostPojo where postid = :postid").setParameter("postid", postId);

				query2.executeUpdate();
			   tx.commit();
			}
			catch (Exception e) {
			   if (tx!=null) tx.rollback();
			   e.printStackTrace(); 
			}finally {
			   session.close();
			}

	}
	public List<Object[]> getProfilePost(String username) {
		Session session = hh.getSessionFactory().openSession();
		Transaction tx=null;	
		//System.out.println("get posts from " + username);

		try {	
			   tx = session.beginTransaction();
				Query query = session.createSQLQuery("select Content, TimePosted, PostId, Likes "
						+ "from posts where UserName = :username").setParameter("username", username);
				List <Object[]> querylist = query.list();
				for (Object [] objects : querylist) {
					//System.out.println(objects[0].toString() + " - " + objects[1].toString());
					
				}
				
				
				
			   tx.commit();
			   Collections.reverse(querylist);
			   return querylist;

			}
			catch (Exception e) {
			   if (tx!=null) tx.rollback();
			   e.printStackTrace(); 
			}finally {
			   session.close();
			}
		return null;
	}
	public void likePost(int postId) {
		Session session = hh.getSessionFactory().openSession();
		Transaction tx=null;		
		
		try {
			   tx = session.beginTransaction();
			   PostPojo post = new PostPojo();			   
				Query query = session.createSQLQuery("update posts p set p.Likes = p.Likes + 1" + "where p.PostId = postid").setParameter("postid", postId);

				query.executeUpdate();
			   tx.commit();
			}
			catch (Exception e) {
			   if (tx!=null) tx.rollback();
			   e.printStackTrace(); 
			}finally {
			   session.close();
			}
	}
	public void editPost(int postId) {
		
	}

}
