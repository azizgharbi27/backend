package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class LoginCtrl {

	@PostMapping("/AdminLog")
	public String auth(String user,String pass) {
		if(user=="admin" && pass=="admin123"){
	        return "success";
	        
	      }
	      else {
	    	return "fail";
	      }
	}
}
