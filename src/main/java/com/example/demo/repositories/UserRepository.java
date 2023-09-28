package com.example.demo.repositories;

import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.User;

public interface UserRepository extends JpaRepository <User, Long> {
	
	// Optional<User> findByUserName(String username) ;
	 
	 Optional<User> findById(Long itemId) ;
	 Optional<User> findByUserName(String  userName) ;
	 User save(User  user);
	 
	

}
