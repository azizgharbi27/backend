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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.HR;
import com.example.demo.model.Manager;
import com.example.demo.model.hrRepo;

@CrossOrigin()
@RestController
public class HRctrl {
	

	@Autowired
	hrRepo hrR;
	
	@PostMapping("/HR")	
	public String addM(@RequestBody HR hr) {
		hrR.save(hr);
	   return "added";
	}
	@DeleteMapping("/HR/{id}")
	public String deleteM(@PathVariable int id) {
		hrR.deleteById(id);
		return "deleted";
	}
	@GetMapping("/HR")
	public List<HR> getM() {
		return hrR.findAll();
	}
	

	@PutMapping("/HR/{id}")
	public HR updateM(@RequestBody HR HR ,@PathVariable int id) {
		return hrR.findById(id)
			      .map( hr -> {
			        hr.setName(HR.getName());
			        hr.setPassword(HR.getPassword());
			        hr.setEmail(HR.getEmail());
			        return hrR.save(hr);
			      })
			      .orElseGet(() -> {
			        
			        return hrR.save(HR);
			        
			        
			      });
	}
	

	


}
