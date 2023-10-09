package com.deloitte.springboot.chatapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.springboot.chatapp.dao.ChatDao;
import com.deloitte.springboot.chatapp.dao.UserDao;
import com.deloitte.springboot.chatapp.model.Chat;
import com.deloitte.springboot.chatapp.model.ChatResponse;
import com.deloitte.springboot.chatapp.model.User;

@RestController
public class ChatController {
	
	@Autowired
	ChatDao chatD;
	@Autowired
	UserDao userD;
	

	@RequestMapping(value = "/")
	public ResponseEntity chatHealth()
	{
		return new ResponseEntity<>("The Chat API is healthy and running",HttpStatus.OK);
	}
	
	@PostMapping(value = "/sendMessage",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity sendMessage(@RequestBody ChatResponse ch)
	{
		
		Optional<User> user = userD.findById(ch.getUserId());
		System.out.println(user);
		User user2 = user.get();
		if(user.isEmpty()) {
			return new ResponseEntity("User Does Not Exist",HttpStatus.OK);
		}
		Chat newChat = new Chat(user2,ch.getContent());
		System.out.println(newChat);
		Chat save = chatD.save(newChat);
		save.getUser().setUserPassword("");
		return new ResponseEntity(save,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getMessages", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Chat>> getMessages()
	{
		List<Chat> allChats = chatD.findAll();
		return new ResponseEntity(allChats,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getMessages/{n}", produces = {MediaType.APPLICATION_JSON_VALUE})	
	public ResponseEntity<List<Chat>> getNMessages(@PathVariable int n)
	{
		
		List<Chat> allChats = chatD.getNChatRecords(n);
		return new ResponseEntity(allChats,HttpStatus.OK);
	}

}
