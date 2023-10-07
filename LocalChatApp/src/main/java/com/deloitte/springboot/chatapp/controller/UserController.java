package com.deloitte.springboot.chatapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.springboot.chatapp.dao.ChatDao;
import com.deloitte.springboot.chatapp.model.Chat;

@RestController
public class UserController {
	
	@Autowired
	ChatDao chatD;
	
	@RequestMapping(value = "/sendMessage",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity sendMessage(@RequestBody Chat ch)
	{
		
		Chat save = chatD.save(ch);
		
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
