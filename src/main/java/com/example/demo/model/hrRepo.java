package com.example.demo.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface hrRepo extends JpaRepository<HR, Integer> {

	public HR findByName(String name);
public 	Optional<HR> findByPassword(String password);

}
