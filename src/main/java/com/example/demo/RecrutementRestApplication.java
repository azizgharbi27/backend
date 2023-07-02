package com.example.demo;

import org.springframework.boot.SpringApplication;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@CrossOrigin
@SpringBootApplication
@EnableWebMvc
public class RecrutementRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecrutementRestApplication.class, args);
		String pop3sHost = "pop.gmail.com";//change accordingly
		  String mailStoreType = "pop3s";	
		  final String userName = "med.aziz.gharbi01@gmail.com";//change accordingly
		  final String password = "dnfknskmchoxwgmh";//change accordingly
		  
		  Thread t = new Thread() {
			    @Override
			    public void run() {
			        while(true) {
			            try {
			                GetMail m=new GetMail();
			      		  m.receiveEmail(pop3sHost, mailStoreType, userName, password);
			      		Thread.sleep(1000*60*60*24*7);
			            } catch (InterruptedException ie) {   }
			        }
			    }
			};
			t.start();
			
			 

	        
	    }

	}


