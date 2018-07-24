/*
 * © Copyright 2009-2014 RiseSmart, Inc. All rights reserved.
 * See the file "LICENSE" for the full license governing this code.
 */
package myapp;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * The Class MailService.
 */
public class MailService {

	/** The mail sender. */
	private JavaMailSender mailSender;
	
	/** The simple mail message. */
	private SimpleMailMessage simpleMailMessage;

	/**
	 * Sets the simple mail message.
	 * 
	 * @param simpleMailMessage
	 *            the new simple mail message
	 */
	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	/**
	 * Sets the mail sender.
	 * 
	 * @param mailSender
	 *            the new mail sender
	 */
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * Send mail.
	 * 
	 * @param userName
	 *            the dear
	 * @param content
	 *            the content
	 */
	public void sendMail(String userName, String content) {

		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(simpleMailMessage.getFrom());
			helper.setTo(simpleMailMessage.getTo());
			helper.setSubject(simpleMailMessage.getSubject());
			helper.setText(String.format(simpleMailMessage.getText(), userName,
					content));

			helper.addAttachment("mycalendar.ics", new ByteArrayResource(CalendarInviteService.prepareCalendarEventICS()));

		} catch (MessagingException e) {
			throw new MailParseException(e);
		}
		mailSender.send(message);
	}
}