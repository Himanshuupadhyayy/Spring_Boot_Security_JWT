package com.JsonWT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.JsonWT.helper.JWTHelperUtil;
import com.JsonWT.model.JWTRequest;
import com.JsonWT.model.JwtResponse;
import com.JsonWT.service.CustomUserDetailsService;
import com.JsonWT.service.UserService;
import com.JsonWT.model.*;
@RestController
public class HomeController {

	@Autowired 
	private AuthenticationManager authenticationManager;
	@Autowired 
	private CustomUserDetailsService customUserDetailsService;
	@Autowired 
	private JWTHelperUtil helperUtil;
	@Autowired
	private UserService service;
	
	@GetMapping("/welcome")
	public String welcome() {
		String text="tjis is private page";
		text+="this page is allowed to authorised users";
		return text;
	}
	
	@PostMapping("/user")
	public ResponseEntity<?> add(@RequestBody User user) {
		User user1=	this.service.registerUser(user);
		return ResponseEntity.ok(user1);
	}
	
	
	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JWTRequest request) throws Exception{
		System.out.println(request);
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok("please enter the valid username or credentials");
		}
		//fine area
	UserDetails userDetails=	this.customUserDetailsService.loadUserByUsername(request.getUsername());
	String token=this.helperUtil.generateToken(userDetails);
	System.out.println("JWT Tokens is "+token);
	
	//return token into json formate			.ok(new JwtResponse(token));
//	return ResponseEntity.ok(new JwtResponse(token));
	return ResponseEntity.ok(new JwtResponse(token));
	}
}