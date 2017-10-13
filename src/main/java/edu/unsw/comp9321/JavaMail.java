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
		
	      String from = "unswbookvalidation@gmail.com";
	      final String username = "unswbookvalidation";
	      final String password = "comp9321";
	 
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
		         message.setText("Verify your account by clicking on the URL: "+ "http://localhost:8080/UNSWBook/confirm?token="+url);
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
	public static void sendEmailToAdmin(String to, String msg, String user) {
		
	      String from = "unswbookvalidation@gmail.com";
	      final String username = "unswbookvalidation";
	      final String password = "comp9321";
	 
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
		         message.setSubject("Message related to bullying");
		         
		         // Now set the actual message
		         message.setText(user + " has posted something that might be related to bullying with this message: "+"\n" + msg);
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
