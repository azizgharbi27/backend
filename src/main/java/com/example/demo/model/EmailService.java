package com.example.demo.model;

import java.io.IOException;
import java.io.WriteAbortedException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.aspectj.apache.bcel.classfile.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.ConferenceData;
import com.google.api.services.calendar.model.ConferenceSolutionKey;
import com.google.api.services.calendar.model.CreateConferenceRequest;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.DateStart;
import biweekly.util.Duration;
@Service
public class EmailService {

     JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(Email email,LocalDateTime date) throws MailException, IOException {
    	
        String from = "med.aziz.gharbi01@gmail.com";//change accordingly  
        String host = "localhost";//or IP address  
    
        final String userName = "med.aziz.gharbi01@gmail.com";//change accordingly
		  final String password = "dnfknskmchoxwgmh";//change accordingly
		  
      Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
      prop.put("mail.smtp.port", "587");
      prop.put("mail.smtp.auth", "true");
      prop.put("mail.smtp.starttls.enable", "true"); //TLS
      
      Session session = Session.getInstance(prop,
              new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(userName, password);
                  }
              });
       //compose the message  
        try{  
           MimeMessage message = new MimeMessage(session);  
           message.setFrom(new InternetAddress(from));  
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(email.getEmailm()));  
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(email.getEmailc()));  
           message.setSubject(email.getName());  
           message.setText(email.getMessage()); 
          // 
           MimeMultipart mimemultipart = new MimeMultipart();
           mimemultipart.addBodyPart(createCalendarMimeBody(email,date));
           message.setContent(mimemultipart);
         // Send message  
           Transport.send(message);  
           System.out.println("message sent successfully....");  
    
        }catch (MessagingException mex) {mex.printStackTrace();}  
     }  
    
     private BodyPart createCalendarMimeBody(Email email, LocalDateTime date) throws MessagingException, IOException {
    	 MimeBodyPart calendarBody= new MimeBodyPart();
    	 final DataSource source = new ByteArrayDataSource(createCal(email,date),"text/calendar; charset=UTF-8");
    	 calendarBody.setDataHandler(new DataHandler(source));
    	 calendarBody.setHeader("Contet-Type", "text/calendar; charset=UTF-8; method=REQUEST");
    	 
    	 return calendarBody;
    	 
     }
     
     private String createCal(Email email,LocalDateTime date) {
    	 
    
    	 Instant instant= date.toInstant(ZoneOffset.UTC);
    	 Date D=Date.from(instant);
    	 Date startDate = new Date(D.getTime() );
    	 System.out.println(startDate);
    	 
    	 ICalendar ical= new ICalendar();
    	 ical.addProperty(new biweekly.property.Method(biweekly.property.Method.REQUEST));
    	 VEvent event = new VEvent();

    	
    	 event.setUrl("https://meet.google.com/yhv-rjfh-tsr");
    	 
    	 event.setSummary("interview");
    	event.setDescription(email.getMessage());
    	 event.setDateStart(new DateStart(startDate));
    	 event.setDuration(new Duration.Builder().hours(1).build());
    	 event.setOrganizer("med.aziz.gharbi01@gmail.com");
    	 event.addAttendee(email.getEmailc());
    	 event.addAttendee(email.getEmailm());
    	 
    	 ConferenceSolutionKey conferenceSKey = new ConferenceSolutionKey();
    	    conferenceSKey.setType("hangoutsMeet"); // Non-G suite user
    	    CreateConferenceRequest createConferenceReq = new CreateConferenceRequest();
    	    createConferenceReq.setRequestId("3whatisup3"); // ID generated by you
    	    createConferenceReq.setConferenceSolutionKey(conferenceSKey);
    	    ConferenceData conferenceData = new ConferenceData();
    	    conferenceData.setCreateRequest(createConferenceReq);
    	    
    	
    	 
    	 ical.addEvent(event);
    	 return Biweekly.write(ical).go();
    	 
    	 
    	 
     }
     public void sendNormalEmail(Email email) throws MailException, IOException {
     	
         String from = "med.aziz.gharbi01@gmail.com";//change accordingly  
         String host = "localhost";//or IP address  
     
         final String userName = "med.aziz.gharbi01@gmail.com";//change accordingly
 		  final String password = "dnfknskmchoxwgmh";//change accordingly
 		  
       Properties prop = new Properties();
 		prop.put("mail.smtp.host", "smtp.gmail.com");
       prop.put("mail.smtp.port", "587");
       prop.put("mail.smtp.auth", "true");
       prop.put("mail.smtp.starttls.enable", "true"); //TLS
       
       Session session = Session.getInstance(prop,
               new javax.mail.Authenticator() {
                   protected PasswordAuthentication getPasswordAuthentication() {
                       return new PasswordAuthentication(userName, password);
                   }
               });
        //compose the message  
         try{  
            MimeMessage message = new MimeMessage(session);  
            message.setFrom(new InternetAddress(from));  
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(email.getEmailm()));  
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(email.getEmailc()));  
            message.setSubject(email.getName());  
            message.setText(email.getMessage()); 
           
          // Send message  
            Transport.send(message);  
            System.out.println("message sent successfully....");  
     
         }catch (MessagingException mex) {mex.printStackTrace();}  
      }  
     
   
    }


