package edu.unsw.comp9321;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SharedSessionContract;
import org.hibernate.Transaction;

public class Friend {
	HibernateHelper hh = new HibernateHelper();

	public String acceptRequest(String request_user, HttpServletRequest request){


		Session session = hh.getSessionFactory().openSession();
		Transaction tt = session.beginTransaction();

		UsersPojo credit = (UsersPojo) request.getSession().getAttribute("credit");
		String user1 = credit.getUserName();

		String user2 = session.createSQLQuery("select UserName from users where URL=:token").setParameter("token", request_user).uniqueResult().toString();

		Query queryResult = session.createSQLQuery("SELECT User1, User2 from friends");


		List<Object[]> userDetails = queryResult.list();

		int flag = 0;
		if (!userDetails.isEmpty()){

			for (int i=0; i<userDetails.size(); i++){

				String test1 = userDetails.get(i)[0].toString();
				String test2 = userDetails.get(i)[1].toString();

				System.out.println("test1 " + test1 + "user1 " + user1 +  "test2 " + test2 + "user2 " + user2);
				if ((test1.equals(user1) && test2.equals(user2)) || (test1.equals(user2) && test2.equals(user1))){

					System.out.println("DUPLICATE");
					flag = 1;
				}
				else{


					System.out.println("asdsadasd" + user1 + user2);
					Timestamp date = new Timestamp(System.currentTimeMillis());
					Query query = session.createSQLQuery("INSERT INTO friends (User1, User2, FriendsSince) VALUES (:user1, :user2, :date)");
					query.setParameter("user1", user1);
					query.setParameter("user2", user2);
					query.setParameter("date", date);
					query.executeUpdate();
					tt.commit();
					session.close();
					System.out.println(user1 + user2);

					flag = 2;


				}



			}



		}

		if (flag == 2){

			return "success";
		}
		else {

			return "fail";
		}
	}








}


