package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties; 
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;

import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.mail.Part;
 
@CrossOrigin
public class GetMail { 
	private String attach;
	private  int MailNumbers;
	
	public GetMail() {
		
	}
	
 public int getMailNumbers() {
		return this.MailNumbers;
	}

	public  void setMailNumbers(int mailNumbers) {
		this.MailNumbers = mailNumbers;
	}

public String getAttach() {
		return this.attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

public void receiveEmail(String pop3sHost,String mailStoreType, String userName, String password){
    //Set properties
    Properties props = new Properties();
    props.put("mail.store.protocol", "pop3s");
    props.put("mail.pop3s.host", pop3sHost);
    props.put("mail.pop3s.port", "995");
    props.put("mail.pop3s.starttls.enable", "true");
 
    // Get the Session object.
    Session session = Session.getInstance(props);
 
    try {
        //Create the pop3s store object and connect to the pop store.
	Store store = session.getStore("pop3s");
	store.connect(pop3sHost, userName, password);
	
 
	//Create the folder object and open it in your mailbox.
	Folder emailFolder = store.getFolder("INBOX");
	emailFolder.open(Folder.READ_ONLY);
	
 
	//Retrieve the messages from the folder object.
	Message[] messages = emailFolder.getMessages();
	MailNumbers=messages.length;
	System.out.println("Total Message" + messages.length);
 
	//Iterate the messages
	for (int i = 0; i < messages.length; i++) {
	   Message message = messages[i];
	   Address[] toAddress = 
             message.getRecipients(Message.RecipientType.TO);
	     System.out.println("---------------------------------");  
	     System.out.println("Details of Email Message " 
                                                   + (i + 1) + " :");  
	     System.out.println("Subject: " + message.getSubject());  
	     System.out.println("From: " + message.getFrom()[0]);  
 
	     //Iterate recipients 
	     System.out.println("To: "); 
	     for(int j = 0; j < toAddress.length; j++){
	       System.out.println(toAddress[j].toString());
	     }
	     
	     if (message.getContentType().contains("multipart")) {
	    	    //send to the download utility...
	    	 List<String> downloadedAttachments = new ArrayList<String>();
	    	    Multipart multiPart = (Multipart) message.getContent();
	    	    int numberOfParts = multiPart.getCount();
	    	    for (int partCount = 0; partCount < numberOfParts; partCount++) {
	    	        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
	    	        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
	    	            String file = part.getFileName(); 
	    	            part.saveFile( "C:\\TestR" + File.separator + part.getFileName()); 
	    	            downloadedAttachments.add(file);
	    	            PDFreader pdfReader=new PDFreader();
	    	            pdfReader.read(file);
	    	        }
	    	    }
	     }
	}  
 
	    /* //Iterate multiparts
	     Multipart multipart = (Multipart) message.getContent();
	     for(int k = 0; k < multipart.getCount(); k++){
	       BodyPart bodyPart = multipart.getBodyPart(k);  
	       InputStream stream = 
                             (InputStream) bodyPart.getInputStream();  
	       BufferedReader bufferedReader = 
	    	   new BufferedReader(new InputStreamReader(stream));  
 
	        while (bufferedReader.ready()) {  
	    	       System.out.println(bufferedReader.readLine());  
	    	}  
	    	   System.out.println();  
	      }  */
	   
 
	   //close the folder and store objects
	   emailFolder.close(false);
	   store.close();
	} catch (NoSuchProviderException e) {
		e.printStackTrace();
	} catch (MessagingException e){
		e.printStackTrace();
	} catch (Exception e) {
	       e.printStackTrace();
	}
 
    }
 

}
