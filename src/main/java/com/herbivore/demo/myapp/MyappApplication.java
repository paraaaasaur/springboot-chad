package com.herbivore.demo.myapp;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static io.github.paraaaasaur.util.Toolbox.*;


@SpringBootApplication
public class MyappApplication {

	public static void main(String[] args) {
		// Run this as a Java application, not on server
		// (∵ already embedded in Spring Boot app)
		SpringApplication.run(MyappApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return strargs -> {
			System.out.println("Hello World!");
		};
    }


	@PostConstruct
	public void aparecium() {
		System.out.println(yellow("✨ Aparecium! MagicalBean is ready for action."));
	}

	@PreDestroy
	public void evanesco() {
		System.out.println(blue("✨ Evanesco! MagicalBean is vanishing gracefully."));
	}

}
