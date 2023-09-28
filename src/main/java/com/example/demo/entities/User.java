package com.example.demo.entities;

import java.io.IOException;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "users")
public class User {
	

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long user_id;

	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String userName;
	
	@Column
	private String password;
	
	@Column
	private String email;
	
	@Column
	private String roles;
	
	
	//constructor (no user ID is needed to construct user because user id is auto generated using annotation)
	public User(String firstName, String lastName, String userName, String password, String email, String roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) { 
		this.user_id = user_id;
	}

	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
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
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	

	
	//method to create a user object from the data entered in  a form on the frontend 
		//it accepts a Hashmap of string key and value pairs
	public static User createUserFromFormData(Map<String, Object> formData) {
	    User user = new User();
	   // user.setUser_id(formData.get("user_id") instanceof Long ? (Long) formData.get("user_id") : null);
	    user.setuserName(formData.get("userName") instanceof String ? (String) formData.get("userName") : null);
	    user.setFirstName(formData.get("firstName") instanceof String ? (String) formData.get("firstName") : null);
	    user.setLastName(formData.get("lastName") instanceof String ? (String) formData.get("lastName") : null);
	    user.setPassword(formData.get("password") instanceof String ? (String) formData.get("password") : null);
	    user.setEmail(formData.get("email") instanceof String ? (String) formData.get("email") : null);
	    user.setRoles(formData.get("roles") instanceof String ? (String) formData.get("roles") : null);

	    return user;
	}
	


}
