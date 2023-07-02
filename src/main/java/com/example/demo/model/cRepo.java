package com.example.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Candidate;

public interface cRepo extends JpaRepository<Candidate, Integer> {
	List<Candidate> findByStatusAndManager(String status, String manager);
	List<Candidate> findByStatus(String status);
	

}
