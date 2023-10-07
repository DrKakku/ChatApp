package com.deloitte.springboot.chatapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.springboot.chatapp.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{
	
	User findByUserName(String userName);
}
