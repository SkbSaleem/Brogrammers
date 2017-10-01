package edu.unsw.comp9321;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.mail.Message;
import javax.mail.MessagingException;

import javax.mail.internet.AddressException;

import javax.mail.internet.MimeMessage;

/**
 * Servlet implementation class FriendRequest
 */
public class FriendRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		

	      // Recipient's email ID needs to be mentioned.
	      String to = "skb.saleem@gmail.com";
	      
	 
	      String from = "unswbookvalidation@gmail.com";//change accordingly
	      final String username = "unswbookvalidation";//change accordingly
	      final String password = "comp9321";//change accordingly
	      String  request_user= request.getParameter("token");
	      
	      System.out.println(request_user);
	      
	      // Assuming you are sending email from localhost
	      String host = "localhost";
	 
	      // Get system properties
	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", "smtp.gmail.com");
	      props.put("mail.smtp.port", "587");
	 
	 
	      Session session = Session.getInstance(props,
	    	      new javax.mail.Authenticator() {
	    	         protected PasswordAuthentication getPasswordAuthentication() {
	    	            return new PasswordAuthentication(username, password);
	    	         }
	    	      });
	      // Set response content type
	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();

	      try {
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);
	         
	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));
	         
	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	         
	         // Set Subject: header field
	         message.setSubject("Confirm Friend Request");
	         
	         // Now set the actual message
	         message.setText("You have received a Friend request from " + request_user + " 	\t\t\n" + "http://localhost:8080/UNSWBook/loggedin/confirmFriend.jsp?token="+request_user);
	         
	         // Send message
	         Transport.send(message);
	       
	         System.out.println("Email sent");
	         request.getRequestDispatcher("loggedin/RequestSent.jsp").forward(request, response);
	        
	      } catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	      
	   }
	
		
		
		
	}


