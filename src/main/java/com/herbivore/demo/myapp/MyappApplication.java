package com.herbivore.demo.myapp;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static io.github.paraaaasaur.util.Toolbox.blue;
import static io.github.paraaaasaur.util.Toolbox.yellow;


@SpringBootApplication
public class MyappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyappApplication.class, args);
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
