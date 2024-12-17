package util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dao.ConnectionManager;

public class SendOTP
{
	// Method to send an email with the OTP
	public static int sendEmailOTP(String recipient, String otp)
	{
		// final String sender = "userforwork12345@gmail.com"; // your own email address
		// String host = "smtp.gmail.com"; // your SMTP server's hostname
		// final String password = "vosrsixlzbjbcvvo"; // your own email password

		final String sender = "choudharylove567@gmail.com"; // your own email address
		String host = "smtp.gmail.com"; // your SMTP server's hostname

		final String password = "jbyenftckcslynxe"; // your own email password

		try
		{
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props, new javax.mail.Authenticator()
			{
				protected javax.mail.PasswordAuthentication getPasswordAuthentication()
				{
					return new javax.mail.PasswordAuthentication(sender, password);
				}
			});
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
			message.setSubject("Verification Code for Signup");
			message.setText("Your verification code is: " + otp);

			Transport.send(message);
		} catch (MessagingException e)
		{
			ConnectionManager.getConn();
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
}
