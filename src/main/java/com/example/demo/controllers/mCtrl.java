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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Manager;
import com.example.demo.model.mRepo;

@CrossOrigin
@RestController
public class mCtrl {
	@Autowired
	mRepo mr;
	
@PostMapping("/managers")	
public String addM(@RequestBody Manager m) {
	mr.save(m);
   return "added";
}
@DeleteMapping("/managers/{id}")
public String deleteM(@PathVariable int id) {
	mr.deleteById(id);
	return "deleted";
}
@GetMapping("/managers")
public List<Manager> getM() {
	
	return mr.findAll();

}

@PutMapping("/managers/{id}")
public Manager updateM(@RequestBody Manager m,@PathVariable int id) {
	return mr.findById(id)
		      .map(manager -> {
		        manager.setName(m.getName());
		        manager.setTeam(m.getTeam());
		        manager.setEmail(m.getEmail());
		        manager.setPassword(m.getPassword());
		        return mr.save(manager);
		      })
		      .orElseGet(() -> {
		        
		        return mr.save(m);
		        
		      });
}



}
