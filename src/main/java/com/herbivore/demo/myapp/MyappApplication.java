package com.herbivore.demo.myapp;

import com.herbivore.demo.myapp.service.TrafficFortuneService;
import com.herbivore.demo.myapp.service.DemoService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static io.github.paraaaasaur.util.Toolbox.blue;
import static io.github.paraaaasaur.util.Toolbox.yellow;

@SpringBootApplication
public class MyappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyappApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(DemoService demoService, TrafficFortuneService trafficFortuneService) {
		return args -> {
//			demoService.demoTheBeforeAdvice();

//			demoService.demoTheAfterReturnAdvice();

//			demoService.demoTheAfterThrowingAdvice();

//			demoService.demoTheAfterAdvice();

//			demoService.demoTheAroundAdvice();

			demoService.demoTheAroundAdviceHandleException();
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