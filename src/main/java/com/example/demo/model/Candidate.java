package com.example.demo.model;

import org.hibernate.service.spi.InjectService;
import org.springframework.stereotype.Repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Candidate")
public class Candidate {
@Id
int id;	
String Fullname;
String email;
String phone;
String Skills;
String Field;
String cv;
String status;
String manager;
String feedback;


public Candidate() {
	super();
	// TODO Auto-generated constructor stub
	
	
}


public String getFeedback() {
	return feedback;
}


public void setFeedback(String feedback) {
	this.feedback = feedback;
}


public String getManager() {
	return manager;
}

public void setManager(String manager) {
	this.manager = manager;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getFullname() {
	return Fullname;
}

public void setFullname(String fullname) {
	Fullname = fullname;
}

public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}

public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getSkills() {
	return Skills;
}
public void setSkills(String skills) {
	Skills = skills;
}
public String getField() {
	return Field;
}
public void setField(String field) {
	Field = field;
}


public String getCv() {
	return cv;
}

public void setCv(String cv) {
	this.cv = cv;
}




@Override
public String toString() {
	return "Candidate [id=" + id + ", Fullname=" + Fullname + ", email=" + email + ", phone=" + phone + ", Skills="
			+ Skills + ", Field=" + Field + ", cv=" + cv + "]";
}







}
