package com.example.demo.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserServices;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	
	  @Autowired 
	  private PasswordEncoder passwordEncoder;
	 
	  @Autowired 
	  private UserServices userService;
	 
	
	@Autowired
	private UserRepository userRepository;
	
			//Retrieve specific user by id
			@GetMapping("/getUserById/{user_id}")
			//@PreAuthorize ("hasAuthority('ROLE_ADMIN')")
			public ResponseEntity<java.util.Optional<User>> getSpecificTrack (@PathVariable Long user_id){
				return ResponseEntity.ok(userService.getUserById(user_id));
			}
			
			
			//Show all users in the Database
		    @GetMapping("/showAllUsers")
			//@PreAuthorize ("hasAuthority('ROLE_ADMIN')")
		    public List<User> showAllUsers() {
		        return userRepository.findAll();
		    }
		    
		    
		    //Create new user 
			@PostMapping ("/createNewUser")
			 	public ResponseEntity<String> createNewUser(//@RequestParam Long user_id,
			 												@RequestParam String userName,
			 												@RequestParam String lastName,
			 												@RequestParam String firstName,


			 												@RequestParam String email,
			 												@RequestParam String password,
			 												@RequestParam String roles)
			
			{
				 Map<String, Object> userFormData = new HashMap<>();
				//userFormData.put("user_id", user_id);
				 userFormData.put("userName", userName);
				 userFormData.put("email", email);
				 userFormData.put("password", password);
				 userFormData.put("firstName", firstName);
				 userFormData.put("lastName", lastName);
				 userFormData.put("roles", roles);

				 


				 
				  User user = User.createUserFromFormData(userFormData);
				  System.out.println("User is being  Created...");

				user.setPassword(passwordEncoder.encode(user.getPassword()));
				userRepository.save(user);
		 	    return ResponseEntity.ok("User created successfully");

			}
	
			//Delete User from Users
			@DeleteMapping("/delete_user/{user_id}")
			//@PreAuthorize ("hasAuthority('ROLE_ADMIN')")

			public ResponseEntity<String> deleteUser(@PathVariable Long user_id) {
				userService.deleteUser(user_id);
				 return ResponseEntity.ok("User DELETED successfully");

			}

}
