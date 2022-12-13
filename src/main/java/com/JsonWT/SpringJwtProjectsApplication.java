package com.JsonWT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJwtProjectsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJwtProjectsApplication.class, args);
		
		System.out.println("spring security and spring jwet porjects");
		System.out.println("if we are using java 1.7+ version need to import one more dependancy "
				+ "javax.xml.bind dependancy and jaxb-api");
	}
}