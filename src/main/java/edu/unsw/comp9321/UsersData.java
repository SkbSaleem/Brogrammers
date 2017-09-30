package edu.unsw.comp9321;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

import edu.unsw.comp9321.HibernateHelper;

public class UsersData implements Serializable {
	HibernateHelper hh = new HibernateHelper();
	Session session = null;
	
	public void createUser(HashMap items, HttpServletRequest request) {
		session = hh.getSessionFactory().openSession();
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
			hh.close();
			e1.printStackTrace();
		}
		user.setCivilStatus(items.get("civilstatus").toString());
		user.setTimeCreated(new Timestamp(System.currentTimeMillis()));
		//Banned initialized to true because the user needs to confirm the email before logging in
		user.setBanned(true);
		String token = returnUniqueToken();
		user.setUrl(token);
        user.setEmail(items.get("email").toString());
		byte[] bytes;
		FileItem file = (FileItem) items.get("profilepicture");
		try {
			InputStream is = file.getInputStream();
			bytes = IOUtils.toByteArray(is);
			user.setProfilePic(bytes);
		} catch (IOException e) {
			session.close();
		}
		try {
			session.persist(user);
			tt.commit();
			JavaMail.sendEmail(items.get("email").toString(), token);
		}
		catch(PersistenceException | ConstraintViolationException f) {
			request.setAttribute("exists", true);
		}
		finally {
			session.close();
		}
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
        return output;
   
    }
	
	private String returnUniqueToken() {
		Session session = hh.getSessionFactory().openSession();
		Transaction tt = session.beginTransaction();
		String token = generateToken();
		List urlQuery = session.createQuery("select u from UsersPojo u where u.url = :token").setParameter("token", token).list();
		while(!urlQuery.isEmpty()) {
			token = generateToken();
			urlQuery = session.createQuery("select u from UsersPojo u where u.url = :token").setParameter("token", token).list();
		}
		tt.commit();
		session.close();
		return token;
	}
	
	public void updateProfilePicture(FileItem fi, HttpServletRequest request) {
		Session session = hh.getSessionFactory().openSession();
		Transaction tt = session.beginTransaction();
		UsersPojo user = (UsersPojo) request.getSession().getAttribute("credit");
		try {
			InputStream is = fi.getInputStream();
			byte[] bytes = IOUtils.toByteArray(is);
			user.setProfilePic(bytes);
			}
		catch(IOException e) {
			
		}
		finally {
			session.merge(user);
			tt.commit();
			session.close();
		}
	}
	
	public void updateUserInfo(Map<String, String> fields, HttpServletRequest request) {
		Session session = hh.getSessionFactory().openSession();
		Transaction tt = session.beginTransaction();
		UsersPojo user = (UsersPojo) request.getSession().getAttribute("credit");
		if(fields.containsKey("mail")) {
			user.setEmail(fields.get("mail").toString());
		}
		if(fields.containsKey("firstname")) {
			user.setFirstName(fields.get("firstname").toString());
		}
		if(fields.containsKey("lastname")) {
			user.setLastName(fields.get("lastname").toString());
		}
		if(fields.containsKey("gender")) {
			user.setGender(fields.get("gender").toString());
		}
		if(fields.containsKey("civilstatus")) {
			user.setCivilStatus(fields.get("civilstatus").toString());
		}
		if(fields.containsKey("dob")) {
			DateFormat datePattern = new SimpleDateFormat("dd/MM/yyyy");
			try {
				 java.util.Date date = datePattern.parse(fields.get("dob").toString());
				 user.setDOB(new Date(date.getTime()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		if(fields.containsKey("password")) {
			user.setPassword(fields.get("password").toString());
		}
		session.merge(user);
		tt.commit();
		session.close();
	}
	
	public UsersPojo authenticate(String username, String password) {
		Session session = hh.getSessionFactory().openSession();
		Transaction tt = session.beginTransaction();
		UsersPojo result = (UsersPojo) session.get(UsersPojo.class, username);
		tt.commit();
		session.close();
		if(result!=null && result.getPassword().equals(password)) {
			return result;
		}
		return null;
	}
	
	public UsersPojo confirm(String token) {
		Session session = hh.getSessionFactory().openSession();
		Transaction tt = session.beginTransaction();
		List result = session.createQuery("select u from UsersPojo u where u.url = :token").setParameter("token", token).list();
		System.out.println(result);
		if(result.isEmpty()) {
			return null;
		}
		UsersPojo user = (UsersPojo) result.get(0);
		user.setUrl(returnUniqueToken());
		user.setBanned(false);
		tt.commit();
		session.close();
		return user;
	}
}
