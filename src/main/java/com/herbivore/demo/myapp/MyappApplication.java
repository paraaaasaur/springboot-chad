package com.herbivore.demo.myapp;

import com.herbivore.demo.myapp.dao.AppDAO;
import com.herbivore.demo.myapp.entity.Instructor;
import com.herbivore.demo.myapp.entity.InstructorDetail;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static io.github.paraaaasaur.util.Toolbox.*;


@SpringBootApplication
public class MyappApplication {

	private AppDAO appDAO;

	public MyappApplication(AppDAO appDAO) {
		this.appDAO = appDAO;
	}

	public static void main(String[] args) {
		SpringApplication.run(MyappApplication.class, args);
	}

	@Bean
	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	public CommandLineRunner commandLineRunner(String[] args) {
		return runner -> {
			createInstructor(appDAO);
		};
	}

	private void createInstructor(AppDAO appDAO) {
		Instructor instructor = new Instructor("Chad", "Darby", "darby@luv2code.com");
		InstructorDetail instructorDetail = new InstructorDetail("https://www.luv2code.com/youtube", "coding");

		instructor.setInstructorDetail(instructorDetail);

		// Also save instructorDetails because of CascadeType.ALL
		appDAO.save(instructor);

		System.out.println(cyan("( ^)o(^ )"));
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
