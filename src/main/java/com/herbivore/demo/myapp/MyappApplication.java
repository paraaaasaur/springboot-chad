package com.herbivore.demo.myapp;

import com.herbivore.demo.myapp.dao.StudentDAO;
import com.herbivore.demo.myapp.entity.Student;
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
		// Run this as a Java application, not on server
		// (∵ already embedded in Spring Boot app)
		SpringApplication.run(MyappApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return strargs -> {
			createStudent(studentDAO);
		};
    }

	private void createStudent(StudentDAO studentDAO) {
		// Create the Student object
		System.out.println("> Creating Student object...");
		Student tempStudent = new Student("Sheriff", "Lua", "dq5isgood@github.io");


		// Save the Student object
		System.out.println("> Saving the student...");
		studentDAO.save(tempStudent);

		// Display the saved Student id
		System.out.println("> Saved student's id = " + tempStudent.getId());

	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		// Create the Student objects
		System.out.println("> Creating Student object...");
		Student tempStudent1 = new Student("Cascading", "Noodles", "nc2@proton.me");
		Student tempStudent2 = new Student("Sadge", "Sadge", "gloomy-tomorrow@gmaili.com");
		Student tempStudent3 = new Student("Hazelnut", "Chocolate", "yit1231@tuta.io");

		// Save the Student objects
		System.out.println("> Saving the student...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
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
