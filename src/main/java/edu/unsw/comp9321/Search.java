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
	Session session = hh.getSessionFactory().openSession();
	Transaction tt = session.beginTransaction();
	
	SearchBean search = new SearchBean(null);
	search.setSearch(items.get("search").toString());
	
	
	}
	
	public List<Object[]> searchResult(String user_input) {
		
		Session session = hh.getSessionFactory().openSession();
		Transaction tt = session.beginTransaction();
		
		Query queryResult = session.createSQLQuery("SELECT * from users WHERE UserName=:search_text OR FirstName=:search_text OR LastName=:search_text").setParameter("search_text", user_input);
		
		List<Object[]> userDetails = queryResult.list();
		// String userDetails = searchQuery.toString();
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
