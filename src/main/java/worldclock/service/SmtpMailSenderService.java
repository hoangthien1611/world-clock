package worldclock.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SmtpMailSenderService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void send(String to, String subject, String body) throws MessagingException, UnsupportedEncodingException {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(new InternetAddress("ces.worldclock@gmail.com", "Ces Worldclock"));
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(body, true);

		javaMailSender.send(message);

	}

}
