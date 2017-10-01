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

	public List<UsersPojo> searchResult(String user_input, String username) {

		Session session = hh.getSessionFactory().openSession();
		Transaction tt = session.beginTransaction();
		List<UsersPojo> userDetails = (List<UsersPojo>) session.createQuery("select u from UsersPojo u where (userName=:search_text"
				+ " or firstName=:search_text or lastName=:search_text) and (not userName=:username)")
				.setParameter("search_text", user_input).setParameter("username", username).list();
		session.close();
		if (!userDetails.isEmpty()) {

			System.out.println(userDetails);
			return userDetails;
		}
		else {

			return null;

		}





	}

}
