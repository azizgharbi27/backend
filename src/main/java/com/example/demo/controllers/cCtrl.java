package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Candidate;
import com.example.demo.model.HR;
import com.example.demo.model.Manager;
import com.example.demo.model.cRepo;
import com.example.demo.model.mRepo;

@CrossOrigin
@RestController
public class cCtrl {
	@Autowired
	cRepo cr;
	
@PostMapping("/Candidate")	
public String addM(@RequestBody Candidate c) {
	cr.save(c);
   return "added";
}
@DeleteMapping("/Candidate/{id}")
public String deleteM(@PathVariable int id) {
	cr.deleteById(id);
	return "deleted";
}
@GetMapping("/Candidate/{status}/{manager}")
public List<Candidate> getC(@PathVariable String status, @PathVariable String manager) {
	return cr.findByStatusAndManager(status,manager);
}
@GetMapping("/Candidate")
public List<Candidate> getAll() {
	System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa");
	
	return cr.findAll();
	
}
@GetMapping("/Candidate/{status}")
public List<Candidate> getAll(@PathVariable String status) {
	
	return cr.findByStatus(status);
}
@PutMapping("/Candidate/{status}/{user}")
public Candidate acceptC(@RequestBody Candidate c, @PathVariable String user,@PathVariable String status ) {

	
	c.setStatus(status);
	c.setManager(user);
	return cr.save(c);
}

@PutMapping("/Candidate/restore")
public Candidate restoreC(@RequestBody Candidate c ) {

	
	c.setStatus("pending");
	c.setManager("all");
	return cr.save(c);
}
@PutMapping("/Candidate/{status}")
public Candidate book(@RequestBody Candidate c,@PathVariable String status ) {

	
	c.setStatus(status);
	
	return cr.save(c);
}

@PutMapping("/Candidate/feedback/{feedback}")
public Candidate giveFeedback(@RequestBody Candidate c,@PathVariable String feedback ) {

	c.setFeedback(feedback);

	System.out.println("aaa\naaa\naaa\naaa\naaa\n");
	return cr.save(c);
}



}
