package com.JsonWT.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JsonWT.model.User;
import com.JsonWT.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo repo;
	
	public User registerUser(User user) {
		User user1=this.repo.save(user);
		return user1;
	}
}