package com.deloitte.springboot.chatapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.deloitte.springboot.chatapp.model.Chat;


@Repository
public interface ChatDao extends JpaRepository<Chat, Integer> {
	
	
	@Query(value = "Select * from Chat LIMIT :n ORDER BY Id DESC",nativeQuery = true)
	public List<Chat> getNChatRecords(int n);

}
