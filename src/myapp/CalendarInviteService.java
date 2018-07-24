/*
 * © Copyright 2009-2014 RiseSmart, Inc. All rights reserved.
 * See the file "LICENSE" for the full license governing this code.
 */
package myapp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.TzId;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Clazz;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.UidGenerator;

/**
 * The Class CalendarInviteService.
 * @author Bibhakar Prakash
 */
public class CalendarInviteService {

    public static byte[] prepareCalendarEventICS() {
  	  //Creating a new calendar 
  	  net.fortuna.ical4j.model.Calendar calendar = new net.fortuna.ical4j.model.Calendar();
  	  calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
  	  calendar.getProperties().add(Version.VERSION_2_0);
  	  calendar.getProperties().add(CalScale.GREGORIAN);
  	  
  	  //Creating an event
  	  java.util.Calendar cal = java.util.Calendar.getInstance(TimeZone.getTimeZone("UTC"));
  	  Calendar today = Calendar.getInstance();
  	  cal.set(java.util.Calendar.YEAR, today.get(Calendar.YEAR));
  	  cal.set(java.util.Calendar.MONTH, today.get(Calendar.MONTH));
  	  cal.set(java.util.Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH));
  	  cal.set(Calendar.HOUR_OF_DAY, today.get(Calendar.HOUR_OF_DAY));
  	  cal.set(Calendar.MINUTE, today.get(Calendar.MINUTE));
  	  cal.set(Calendar.SECOND, today.get(Calendar.SECOND));
  	  //cal.set(Calendar.MILLISECOND, today.get(Calendar.MILLISECOND));
  	  
  	  byte[] calendarEvent = {};
  	  VEvent eventDetails = new VEvent(new net.fortuna.ical4j.model.DateTime(
				cal.getTime()), "Christmas Day");
  	  
  	        // Generate a UID for the event..
  			UidGenerator uidGenerator = null;
  			try {
  				uidGenerator = new UidGenerator("1");
  				eventDetails.getProperties().add(uidGenerator.generateUid());

  				eventDetails.getProperties().add(
  						new Location("Pune, Maharashtra"));
  				eventDetails.getProperties().add(Clazz.PUBLIC);
  				eventDetails.getProperties().add(
  						new Description("Appointment Confirmed"));

  					//java.util.TimeZone timezone = java.util.TimeZone.getDefault();
  				    java.util.TimeZone timezone = TimeZone.getTimeZone("UTC");
  					System.out.println("The Current TimeZone is : "+timezone.getID());
  					//TzId tzParam = new TzId(timezone.getID());
  					//VTimeZone tz = VTimeZone.getDefault();
  					//TzId tzParam = new TzId(tz.getProperties().getProperty(Property.TZID).getValue());
  					
  					/*TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
  					// create timezone property..
  					VTimeZone  tz = registry.getTimeZone(timezone.getID()).getVTimeZone();
  				    // create tzid parameter..
  					TzId tzParam = new TzId(tz.getProperty(Property.TZID).getValue());*/
  					
  					/*if(timezone.getID().equalsIgnoreCase("Etc/UTC")) {
  						Calendar c2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
  						c2.set(year, month, date, hourOfDay, minute, second);

  					}*/
  					
  					TzId tzParam = new TzId(timezone.getID());
  					eventDetails.getProperties().getProperty(Property.DTSTART)
  							.getParameters().add(tzParam);
  					if (eventDetails.getProperties().getProperty(Property.DTEND) != null) {
  						eventDetails.getProperties().getProperty(Property.DTEND)
  								.getParameters().add(tzParam);
  					}

  				calendar.getComponents().add(eventDetails);
  				ByteArrayOutputStream fout = new ByteArrayOutputStream();
  				CalendarOutputter outputter = new CalendarOutputter();
  				outputter.output(calendar, fout);
  				fout.close();
  				return fout.toByteArray();
  			} catch (ValidationException validationException) {
  				System.out.println("Exception occured while preparing calendar event with ValidationException: {}"+
  						validationException.getMessage());
  			} catch (IOException ioException) {
  				System.out.println("Exception occured while preparing calendar event with IOException: {}"+
  						ioException.getMessage());
  			}
  			return calendarEvent;
    }
}// end of class
