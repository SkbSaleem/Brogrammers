package edu.unsw.comp9321;

import java.io.InputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PostData {
	HibernateHelper hh = new HibernateHelper();

	public void createPost(HashMap content, String username) {
		Session session = hh.getSessionFactory().openSession();
		//Transaction tt = session.beginTransaction();
		Transaction tx = null;		
		//tt.commit();
		try {
			tx = session.beginTransaction();
			PostPojo post = new PostPojo();
			post.setContent(content.get("textareapost").toString());
			post.setLikes(0);
			post.setUsername(username);
			post.setTimeposted(new Timestamp(System.currentTimeMillis()));
			if(content.get("image")!=null) {
				FileItem file = (FileItem) content.get("image");
				InputStream is = file.getInputStream();
				byte [] bytes = IOUtils.toByteArray(is);
				post.setImage(Base64.encode(bytes).toString());
			}
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
	public List<PostPojo> getProfilePost(String username) {
		Session session = hh.getSessionFactory().openSession();
		Transaction tx=null;	

		try {	
			tx = session.beginTransaction();
			Query query = session.createQuery("select p from PostPojo p where p.username = :username").setParameter("username", username);
			List <PostPojo> querylist = query.list();
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
			Query query = session.createQuery("update posts p set p.Likes = p.Likes + 1 where p.PostId = :postid").setParameter("postid", postId);

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
}
