package com.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;

import org.springframework.stereotype.Service;

import com.bean.UserBean;

@Service
public class MailService {

	public void sendOtpForForgetPassword(UserBean userBean) {

		String to = userBean.getEmail();// change accordingly

		// Sender's email ID needs to be mentioned
		String from = "dhagash2000@gmail.com";// change accordingly
		final String username = "dhagash2000@gmail.com";// change accordingly
		final String password = "idkp fyij rkjz guvq";// change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		javax.mail.Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject("Password Reset OTP");

			// Now set the actual message
			message.setContent("Hello " + userBean.getFirstname() + ", <b>" + userBean.getOtp()
					+ "</b> is your OTP to verify your Identity For Reset Password<br>", "text/html");

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void sendMailForPasswordUpdate(UserBean userBean) {

		String to = userBean.getEmail();// change accordingly

// Sender's email ID needs to be mentioned
		String from = "dhagash2000@gmail.com";// change accordingly
		final String username = "dhagash2000@gmail.com";// change accordingly
		final String password = "idkp fyij rkjz guvq";// change accordingly

// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

// Set Subject: header field
			message.setSubject("Password Updated!!!");

// Now set the actual message
			message.setContent("Hello " + userBean.getFirstname() + ", Your Password succssfully update.. <br>",
					"text/html");

// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
