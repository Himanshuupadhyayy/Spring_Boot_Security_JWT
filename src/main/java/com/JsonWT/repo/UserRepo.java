package com.JsonWT.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JsonWT.model.User;


public interface UserRepo extends JpaRepository<User, Integer> {

	public User findByUsername(String username);

}