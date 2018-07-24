/*
 * © Copyright 2009-2014 RiseSmart, Inc. All rights reserved.
 * See the file "LICENSE" for the full license governing this code.
 */
package myapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * The Class SendCalendarInvite.
 * @author Bibhakar Prakash
 */
public class SendCalendarInvite {
	
	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		System.out.println("Sending Calendar Invite Processing Started....");
		ApplicationContext context = new FileSystemXmlApplicationContext(
				"Spring-Mail.xml");

		MailService mailService = (MailService) context.getBean("mailService");
		mailService.sendMail("Bibhakar Prakash", "Please find the Calendar Appointment");
		System.out.println("Sending Calendar Invite Processing Ended....");
	}
}