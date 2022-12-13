package com.JsonWT.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.JsonWT.helper.JWTHelperUtil;
import com.JsonWT.service.CustomUserDetailsService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private JWTHelperUtil helperUtil;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		//get JWT 
		
		//bearer
		
		//validate
		String requestTokenHeader=request.getHeader("Authorization");
		String username=null;
		String jwtToken=null;
		//null and formate
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {
		jwtToken= requestTokenHeader.substring(7);
		System.out.println(jwtToken);
		try {
			username=this.helperUtil.extractUsername(jwtToken);
		}catch (Exception e) {
			e.printStackTrace();
		}
//		UserDetails userDetails=this.customUserDetailsService.loadUserByUsername(username);
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, jwtToken, null);
			usernamePasswordAuthenticationToken
					.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

		}
		}
		//
		filterChain.doFilter(request, response);
	}

}