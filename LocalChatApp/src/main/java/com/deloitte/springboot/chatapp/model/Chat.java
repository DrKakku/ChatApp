package com.deloitte.springboot.chatapp.model;

import org.hibernate.annotations.ForeignKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Chat {
	
	public int getUserId() {
		return id;
	}


	public void setUserId(int id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getContent() {
		return Content;
	}


	public void setContent(String content) {
		Content = content;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	int id; 
	@ManyToOne()
	@JoinColumn(name="userId",referencedColumnName = "userId")
	User user;
	@Column(name="Content", length = 1000)
	String Content;
	
	
	public Chat(User user, String content) {
		this.user = user;
		this.Content = content;
		
	}
	
	

}
