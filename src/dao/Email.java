package dao;

import java.util.Scanner;
import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.*;
import model.Customer;

public class Email {

	public void email(String user, String customer, String custPhone, String custName, long bookId, String bookName, int quantity, double total) {
		Scanner in = new Scanner(System.in);
		String from = user;
		String password = "0W0JWP@84629";
		String to = customer;
		Properties properties = System.getProperties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.port", "587");

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(from, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Book Store");
			message.setText("Mobileno                 : " + custPhone+"\n"+"Customer Name     : " + custName+"\n"+
					"Book Id                       : " + bookId+"\n"+"Book Name               : "
					+ bookName+"\n"+"Book Quantity           : " + quantity+"\n"+"Book Total              : " + total);
			Transport.send(message);
			System.out.println("Message Sent ....");
		} catch (MessagingException mex) {
			mex.printStackTrace();

		}
	}
	public void wishEmail(String customer, String custPhone, String custName, String bookName) {
		Scanner in = new Scanner(System.in);
		String from = "vinaycbaddi@gmail.com";
		String password = "0W0JWP@84629";
		String to = customer;
		Properties properties = System.getProperties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.port", "587");

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(from, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Book Store");
			message.setText("Mobileno                 : " + custPhone+"\n"+"Customer Name     : " + custName+"\n"+"Book Name               : "+ bookName
					+"\n"+"Stock is available"+"\n"+"Grab the book before it is out of stock");
			Transport.send(message);
			System.out.println("Message Sent ....");
		} catch (MessagingException mex) {
			mex.printStackTrace();

		}
	}

}
