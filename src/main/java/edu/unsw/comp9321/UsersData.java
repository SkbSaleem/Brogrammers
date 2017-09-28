package edu.unsw.comp9321;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.sql.Blob;

import edu.unsw.comp9321.HibernateHelper;

public class UsersData {
	HibernateHelper hh = new HibernateHelper();
	
	public void createUser(HashMap items, HttpServletRequest request) {
		Session session = hh.getSessionFactory().openSession();
		Transaction tt = session.beginTransaction();
		
		UsersPojo user=new UsersPojo();
		user.setUserName(items.get("username").toString());
		user.setPassword(items.get("password").toString());
		user.setFirstName(items.get("firstname").toString());
		user.setLastName(items.get("lastname").toString());
		user.setGender(items.get("gender").toString());
		DateFormat datePattern = new SimpleDateFormat("dd/MM/yyyy");

		try {
			 java.util.Date date = datePattern.parse(items.get("dob").toString());
			 user.setDOB(new Date(date.getTime()));
		} catch (ParseException e1) {
			session.close();
			e1.printStackTrace();
		}
		user.setCivilStatus(items.get("civilstatus").toString());
		user.setTimeCreated(new Timestamp(System.currentTimeMillis()));
		//Banned initialized to true because the user needs to confirm the email before logging in
		user.setBanned(true);
		String token = returnUniqueToken(generateToken());
		user.setUrl(token);
		JavaMail.sendEmail(items.get("email").toString(), token);
		
        user.setEmail(items.get("email").toString());
		byte[] bytes;
		FileItem file = (FileItem) items.get("profilepicture");
		try {
			InputStream is = file.getInputStream();
			bytes = IOUtils.toByteArray(is);
			Blob blob = Hibernate.getLobCreator(session).createBlob(bytes);
			user.setProfilePic(blob);
		} catch (IOException e) {
			//TODO create default Blob image here in case of error
			session.close();
		}
		try {
			session.persist(user);
			tt.commit();
		}
		catch(PersistenceException | ConstraintViolationException f) {
			request.setAttribute("exists", true);
		}
		finally {
			session.close();
		}
	}
	
	public Credit authenticateUser(String username, String password) {
		Session session = hh.getSessionFactory().openSession();
		Transaction tt = session.beginTransaction();
		//TODO Change to WHERE statement
		List userNames = session.createSQLQuery("SELECT UserName from users").list();
		for(Object user:session.createSQLQuery("SELECT UserName from users").list()) {
			if(user.toString().equals(username)) {
				//This should handle any potential SQL injection
				Query passwordquery = session.createSQLQuery("SELECT * from users WHERE UserName=:user").setParameter("user", username);
				List<Object[]> userDetails = passwordquery.list();
				if(userDetails.get(0)[1].equals(password) && Boolean.parseBoolean(userDetails.get(0)[8].toString())==false) {
					session.close();
					System.out.println("Work work work work work work");
					//byte [] b = (byte[]) userDetails.get(0)[6];
					//String encodedImage= Base64.encode(b);
					return new Credit(username, true, 
							(Date)userDetails.get(0)[2], 
							userDetails.get(0)[3].toString(),
							 userDetails.get(0)[4].toString(), userDetails.get(0)[5].toString(), 
							 Base64.encode((byte[]) userDetails.get(0)[6]).toString(),
							 userDetails.get(0)[7].toString());
				}
				session.close();
				return new Credit(username,false);
			}
		}
		session.close();
		return new Credit(username,false);
	}
	
	public Credit confirmUser(String token) {
		Session session = hh.getSessionFactory().openSession();
		Transaction tt = session.beginTransaction();
		String newToken = returnUniqueToken(generateToken());
		String username = session.createSQLQuery("SELECT UserName from users WHERE URL=:token").setParameter("token", newToken).uniqueResult().toString();
		List<Object[]> userDetails = session.createSQLQuery("SELECT * from users WHERE UserName = :username").setParameter("username", username).list();
		session.createSQLQuery("UPDATE users SET URL=:token, Banned=0 WHERE UserName=:username").setParameter("username", username).setParameter("token", newToken);
		session.close();
		return new Credit(username, true, (Date) userDetails.get(0)[2], userDetails.get(0)[3].toString(),
				 userDetails.get(0)[4].toString(),  userDetails.get(0)[5].toString(), Base64.encode((byte[]) userDetails.get(0)[6]).toString(),  userDetails.get(0)[7].toString());
	}
	
	private String generateToken() {
        char[] chars = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        System.out.println(output);
        return output;
   
    }
	
	private String returnUniqueToken(String token) {
		Session session = hh.getSessionFactory().openSession();
		Transaction tt = session.beginTransaction();
		List urlQuery = session.createSQLQuery("SELECT URL FROM users WHERE URL=:token").setParameter("token", token).list();
		while(!urlQuery.isEmpty()) {
			token = generateToken();
			urlQuery = session.createSQLQuery("SELECT URL FROM users WHERE URL=:token").setParameter("token", token).list();
		}
		return token;
	}
}
