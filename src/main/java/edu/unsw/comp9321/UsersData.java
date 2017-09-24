package edu.unsw.comp9321;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Blob;

import edu.unsw.comp9321.HibernateHelper;

public class UsersData {
	HibernateHelper hh = new HibernateHelper();
	
	public void createUser(HashMap items) {
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
		user.setBanned(false);
		//TODO Create unique URL
		user.setUrl("http://localhost:8080/0101");
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
		catch(PersistenceException e) {
			System.out.println("Username already exists");
			//TODO Return error message
			return;
		}
		finally {
			session.close();
		}
	}
	
	public boolean authenticateUser(String username, String password) {
		Session session = hh.getSessionFactory().openSession();
		Transaction tt = session.beginTransaction();
		List userNames = session.createSQLQuery("SELECT UserName from users").list();
		for(Object user:session.createSQLQuery("SELECT UserName from users").list()) {
			if(user.toString().equals(username)) {
				String userpassword = session.createSQLQuery("SELECT Password from users WHERE UserName='"+username+"'").uniqueResult().toString();
				//TODO Handle potential SQL injection
				if(userpassword.equals(password)) {
					session.close();
					return true;
				}
				session.close();
				return false;
			}
		}
		session.close();
		return false;
	}
}
