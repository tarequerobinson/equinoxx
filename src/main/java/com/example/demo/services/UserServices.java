package com.example.demo.services;

import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Order;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserServices {
	
	
	@Autowired
	public UserRepository userRepository;
	
	public Optional<User> getUserById (Long user_id) {
		return userRepository.findById(user_id);
	}
	
	public void deleteUser (Long user_id) {
		// TODO Auto-generated method stub
		Optional<User> deletedUser = userRepository.findById(user_id);
		userRepository.delete(deletedUser.get());	
	}
	
	
	

}
