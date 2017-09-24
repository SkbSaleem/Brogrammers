package edu.unsw.comp9321;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMail {
	public static void sendEmail(String to, String url) {
		
		// Recipient's email ID needs to be mentioned.
	     // String to = "christian.trinh@student.unsw.edu.au";
	 
	   // Sender's email ID needs to be mentioned
	      String from = "unswbookvalidation@gmail.com";//change accordingly
	      final String username = "unswbookvalidation";//change accordingly
	      final String password = "comp9321";//change accordingly
	 
	      // Assuming you are sending email from localhost
	      //String host = "localhost";
	 
	      


	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", "smtp.gmail.com");
	      props.put("mail.smtp.port", "587");
	 
	   // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });
	      
	   // Create a default MimeMessage object.
	         try {
		         MimeMessage message = new MimeMessage(session);

	   	      // Set From: header field of the header.

				message.setFrom(new InternetAddress(from));
				// Set To: header field of the header.
		         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		         
		         // Set Subject: header field
		         message.setSubject("UNSWBook email verification");
		         
		         // Now set the actual message
		         message.setText("Verify your account by clicking on the URL: "+ url);
		         Transport.send(message);
		         System.out.println("Email sent");
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
}
