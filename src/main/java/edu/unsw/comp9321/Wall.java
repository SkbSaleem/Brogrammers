package edu.unsw.comp9321;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Wall {


	HibernateHelper hh = new HibernateHelper();

	public List<PostPojo> wallPost(HttpServletRequest request){

		Session session = hh.getSessionFactory().openSession();
		Transaction tt = session.beginTransaction();

		UsersPojo credit = (UsersPojo) request.getSession().getAttribute("credit");
		String user1 = credit.getUserName();

		Query queryResult = session.createSQLQuery("SELECT User1, User2 from friends");

		List<Object[]> friendList = queryResult.list();
		ArrayList<String> friends = new ArrayList<String>();
		int flag = 0;
		System.out.println(friendList.size());
		if(!friendList.isEmpty()){

			for (int i=0; i<friendList.size(); i++){

				String test1 = friendList.get(i)[0].toString();
				String test2 = friendList.get(i)[1].toString();

				if(user1.equals(test1)){
					friends.add(test2);
				}
				else if(user1.equals(test2)){

					friends.add(test1);
				}
				

			}


		}else{
			
			
			flag = 1;
		}
		Query queryResult1 = session.createQuery("from PostPojo");

		List<PostPojo> postList = (List<PostPojo>) queryResult1.list();
		
		List<PostPojo> displayPostList = new ArrayList<PostPojo>();
		
		if (!postList.isEmpty()){
			
			for (PostPojo post : postList){
				
				if (friends.contains(post.getUsername())){
					
					displayPostList.add(post);
					
					
				}
				
			}
			
			
		}
		
		System.out.println("display :::::" + displayPostList);
		session.close();
		return displayPostList;



	}

	

}
