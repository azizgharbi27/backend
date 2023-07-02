package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Managers")
public class Manager {
	@Id
	int id;
	String name;
	String email;
	String password;
	String team;
	
	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}

}
