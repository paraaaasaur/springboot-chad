package com.herbivore.demo.myapp;


import com.herbivore.demo.myapp.dao.AppDAO;
import com.herbivore.demo.myapp.entity.Course;
import com.herbivore.demo.myapp.entity.Instructor;
import com.herbivore.demo.myapp.entity.InstructorDetail;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.herbivore.demo.myapp.util.Trivial.aqtn;
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
//			createInstructor(appDAO);

//			findInstructor(appDAO, 3);

//			updateInstructor(appDAO);

//			deleteInstructor(appDAO, 27);

//			findDetail(appDAO, 27);

//			updateDetail(appDAO, 27);

//			deleteDetail(appDAO, 31);

			createInstructorWithCourses(appDAO);
		};
	}

	private void createInstructor(AppDAO appDAO) {
		System.out.println(cyan("> Creating Instructor (and details)"));

		Instructor instructor = new Instructor("Chris", "Abroad", "aij@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("https://www.youtube.com/@AbroadInJapan", "Filming");
		instructor.associate(instructorDetail);
		appDAO.saveInstructor(instructor);

		aqtn();
	}

	private void findInstructor(AppDAO appDAO, int id) {
		System.out.println(cyan("> Retrieving Instructor with id = " + id + "..."));
		Instructor found = appDAO.findInstructorById(id);
		if (found == null) {
			System.out.println(yellow("> No instructor with id = " + id + " is found!"));
			return;
		}
		System.out.println(found);
		System.out.println(found.getInstructorDetail());

		aqtn();
	}

	private void updateInstructor(AppDAO appDAO) {
		System.out.println(cyan("> Updating(Trolling) instructor with id = 1..."));

		Instructor instructor = appDAO.findInstructorById(1);
		instructor.setFirstName("Foo");
		instructor.setLastName("Bar");
		instructor.setEmail("excellent@proton.me");
		InstructorDetail instructorDetail = instructor.getInstructorDetail();
		instructorDetail.setHobby("YEET");
		instructorDetail.setYoutubeChannel("https://youtube.com/@RamenIsLife");

		appDAO.updateInstructor(instructor);
		aqtn();
	}

	private void deleteInstructor(AppDAO appDAO, int id) {
		System.out.println(cyan("> Deleting instructor + details of id = " + id));

		appDAO.deleteInstructorById(id);
		aqtn();
	}

	private void findDetail(AppDAO appDAO, int id) {
		System.out.println(cyan("> Retrieving InstructorDetail with id = " + id + "..."));
		InstructorDetail foundDetail = appDAO.findInstructorDetailById(id);
		System.out.println(foundDetail);

		aqtn();
	}

	private void updateDetail(AppDAO appDAO, int id) {
		InstructorDetail detail = appDAO.findInstructorDetailById(id);
		detail.setHobby("jumping-jack");
		appDAO.updateInstructorDetail(detail);

		aqtn();
	}

	private void deleteDetail(AppDAO appDAO, int id) {
		System.out.println(cyan("> Deleting instructor + details of id = " + id));

		appDAO.deleteInstructorDetailById(id);
		aqtn();
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		System.out.println(cyan("> Creating Instructor (and Courses)"));

		var instructor = new Instructor("Loren", "Pepper", "owl-life@gmail.com");

		instructor.associate(
			new Course("200 Hours Yoga Teacher Training - Part 1 (Yoga Alliance)"),
			new Course("The No. 1 Breathwork & Meditation course | \"Breath is Life\""),
			new Course("Learn to Play the Flute: Beginner Basics to Intermediate"),
			new Course("Complete Indonesian Course: Learn Indonesian for Beginners"),
			new Course("Eat Real Food: How to Eat a Whole Food, Plant-Based Diet")
		);

		System.out.println(instructor);

		appDAO.saveInstructor(instructor);
		aqtn();
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
