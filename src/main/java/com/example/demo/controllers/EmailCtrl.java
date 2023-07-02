package com.example.demo.controllers;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Email;
import com.example.demo.model.EmailService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class EmailCtrl {
	@Autowired
	  private EmailService emailService;

	

	  @PostMapping(value = "/email/{date}")
	  public ResponseEntity<Email> sEmail(@RequestBody Email email, @PathVariable LocalDateTime date) throws IOException, GeneralSecurityException{
	    try {
	      emailService.sendEmail(email,date);
	      
	      return new ResponseEntity<>(email,  HttpStatus.OK);
	    } catch( MailException e){
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }


	  }
	  
	  @PostMapping(value = "/email")
	  public ResponseEntity<Email> sNormalEmail(@RequestBody Email email) throws IOException, GeneralSecurityException{
	    try {
	      emailService.sendNormalEmail(email);
	      
	      return new ResponseEntity<>(email,  HttpStatus.OK);
	    } catch( MailException e){
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }


	  }

}
