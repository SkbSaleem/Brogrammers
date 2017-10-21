package edu.unsw.comp9321;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

public class CreateGraph {

	private List<Map<String,String>> triplesMap;
	
	public CreateGraph() {
		Session session = HibernateHelper.getSessionFactory().openSession();
		List<Object[]> posts = (List<Object[]>) session.createQuery("select p.username, p.content, p.postid from PostPojo p").list();
		List<Object[]> friends = (List<Object[]>) session.createQuery("select f.user1, f.user2 from FriendsPojo f").list();
		List<Object[]> likes = (List<Object[]>) session.createSQLQuery("SELECT * from likes").list();

		triplesMap = new ArrayList<Map<String,String>>();
		for(Object[] post : posts) {
			Map<String, String> values = new HashMap<String, String>();
			values.put("subject", post[0].toString());
			values.put("predicate", "posted");
			values.put("object", post[1].toString());
			triplesMap.add(values);
			for(Object[] like : likes) {
				if(like[0].toString().equals(post[2].toString())) {
					values = new HashMap<String, String>();
					values.put("subject", like[1].toString());
					values.put("predicate", "liked");
					values.put("object", post[1].toString());
					triplesMap.add(values);
				}
			}
		}
		for(Object[] friend : friends) {
			Map<String, String> values = new HashMap<String, String>();
			values.put("subject", friend[0].toString());
			values.put("predicate", "friendOf");
			values.put("object", friend[1].toString());
			triplesMap.add(values);
			values = new HashMap<String, String>();
			values.put("subject", friend[1].toString());
			values.put("predicate", "friendOf");
			values.put("object", friend[0].toString());
			triplesMap.add(values);
		}
		session.close();
	}

	public List<Map<String,String>> getTriplesMap() {
		return triplesMap;
	}

	public void setTriplesMap(List<Map<String,String>> triplesMap) {
		this.triplesMap = triplesMap;
	}
	
}
