package com.herbivore.demo.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(
//		scanBasePackages = {"com.herbivore.demo.myapp",
//				"com.herbivore.util"}
//)
@SpringBootApplication
public class MyappApplication {

	public static void main(String[] args) {
		// Run this as a Java application, not on server
		// (∵ already embedded in Spring Boot app)
		SpringApplication.run(MyappApplication.class, args);
	}

}
