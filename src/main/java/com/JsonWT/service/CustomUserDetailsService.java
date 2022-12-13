package com.JsonWT.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.JsonWT.model.CustomUserDetails;
import com.JsonWT.model.User;
import com.JsonWT.repo.UserRepo;


@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo userRepository;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User	user=this.userRepository.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("no users is exist");
		}
			return new CustomUserDetails(user);
		}


	}

/*	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if(username.equals("Himanshu")) {
			return new User("Himanshu", "1234", new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("username not found");
		}
	}
*/
