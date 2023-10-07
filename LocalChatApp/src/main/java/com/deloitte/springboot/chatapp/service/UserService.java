package com.deloitte.springboot.chatapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.deloitte.springboot.chatapp.dao.UserDao;
import com.deloitte.springboot.chatapp.model.User;

@Service
@ComponentScan(basePackages = {"com.deloitte.springboot.chatapp.Service"})
public class UserService {

	@Autowired
	private UserDao userDao;
	
	 public ResponseEntity<?> register(User user) {
		 
		 	
	        if (userDao.findByUserName(user.getUserName()) != null) {        	
	        	 return ResponseEntity.badRequest().body("Username already exists");
	        }
	        else {
	        	userDao.save(user);
	        }
	        
	        user.setUserPassword(user.getUserPassword());
	        
	        return ResponseEntity.ok("Registration successful");
	 	}
	 
	 public ResponseEntity<?> login(User user) {
		 
	        User existingUser = userDao.findByUserName(user.getUserName());
	        
	        
	        if (existingUser == null) {
	            return ResponseEntity.badRequest().body("User not found");
	        }
	        
	        
	        if (!existingUser.getUserPassword().equals(user.getUserPassword())) {
	            return ResponseEntity.badRequest().body("Incorrect password");
	        }
	        
	        
	        return ResponseEntity.ok("Login successful");
	    }
	





	 
}
