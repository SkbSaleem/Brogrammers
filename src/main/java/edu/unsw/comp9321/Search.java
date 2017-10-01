package edu.unsw.comp9321;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Search {
	
	HibernateHelper hh = new HibernateHelper();
	
	public void findSearch(HashMap items, HttpServletRequest request) {
		
	SearchBean search = new SearchBean(null);
	search.setSearch(items.get("search").toString());
	
	
	}
	
	public List<UsersPojo> searchResult(String user_input) {
		
		Session session = hh.getSessionFactory().openSession();
		Transaction tt = session.beginTransaction();
		
		//Query queryResult = session.createSQLQuery("SELECT * from users WHERE UserName=:search_text OR FirstName=:search_text OR LastName=:search_text").setParameter("search_text", user_input);
		List<UsersPojo> userDetails = (List<UsersPojo>) session.createQuery("select u from UsersPojo u where userName=:search_text or firstName=:search_text or lastName=:search_text").setParameter("search_text", user_input).list();
		//List<Object[]> userDetails = queryResult.list();
		// String userDetails = searchQuery.toString();
		session.close();
		if (!userDetails.isEmpty()) {
			//if (userDetails != "") {
				
				System.out.println(userDetails);
			return userDetails;
			
		}
		else {
			
			return null;
			
		}
		
		
		
		
		
	}

}
