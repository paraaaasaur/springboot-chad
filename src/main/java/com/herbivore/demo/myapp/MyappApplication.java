package com.herbivore.demo.myapp;

import com.herbivore.demo.myapp.dao.StudentDAO;
import com.herbivore.demo.myapp.entity.Student;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.util.Comparator;
import java.util.List;

import static io.github.paraaaasaur.util.Toolbox.*;


@SpringBootApplication
public class MyappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyappApplication.class, args);
	}

	@Order(value = 4)
	@Bean
	public CommandLineRunner clrV4() {
		return strargs -> {
			System.out.println("Inside v4");
		};
	}

	@Order(2)
	@Bean
	public CommandLineRunner clrV2() {
		return args -> {
			System.out.println("Inside v2");
		};
	}

	@Order(3)
	@Bean
	public CommandLineRunner clrV3() {
		return args -> {
			System.out.println("Inside v3");
		};
	}

	@Order(Integer.MAX_VALUE)
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return args -> {
			if (args.length != 0) {
				System.out.println("> You are see this because you offered args" +
								   "on a command line!");
				System.out.println("> They are: ");
				for (String arg : args) {
					System.out.println("\t- " + arg);
				}
			} else {
				System.out.println("> No command line arguments detected!");
			}

//			createStudent(studentDAO);

//			createMultipleStudents(studentDAO);

//			readStudent(studentDAO);

//			queryForStudents(studentDAO);

//			queryStudentsByLastName(studentDAO);

//			updateStudent(studentDAO);

//			deleteStudent(studentDAO);

//			deleteAllStudents(studentDAO);
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

	private void readStudent(StudentDAO studentDAO) {

		// Create a student object
		System.out.println("> Creating new Student object...");
		Student tempStudent = new Student("Dafty", "Duck", "daftyyy@luv2code.com");

		// Save the student
		System.out.println("> Saving the Student...");
		studentDAO.save(tempStudent);

		// Display id of the saved student
		int id = tempStudent.getId();
		System.out.println("> Saved Student's id = " + id);

		// Retrieve the Student based on the id (primary key)
		System.out.println("> Retrieving the Student with id = " + id);
		Student foundStudent = studentDAO.findById(id);

		// Display the retrieved student
		System.out.println("> Found the student: " + foundStudent);

	}

	private void queryForStudents(StudentDAO studentDAO) {

		if (true) {
			// get a list of students
			List<Student> students = studentDAO.findAll();

			// display list of students
			students.stream()
//					.sorted(Comparator.comparing(Student::getLastName))
					.forEach(System.out::println);
		}

		if (false) {
			studentDAO.findAllInTuple().stream()
					.sorted(Comparator.comparing(tuple -> tuple.get(2, String.class)))
					.forEach(System.out::println);
		}
	}

	private void queryStudentsByLastName(StudentDAO studentDAO) {

		// Get a list of Students
		List<Student> students = studentDAO.findByLastName("duck");

		// Display list of Students
		students.forEach(System.out::println);
	}

	private void updateStudent(StudentDAO studentDAO) {
//		studentDAO.test();

		// Retrieve student based on the id: primary key
		final int id = 1;
		System.out.println("> Getting student with id = " + id);
		Student foundStudent = studentDAO.findById(id);

		// Change his or her first name
		foundStudent.setFirstName("Gura");

		// Update the student
		studentDAO.update(foundStudent);

		// Display the update student
		System.out.println("> Update student: " + studentDAO.findById(id));

	}

	private void deleteStudent(StudentDAO studentDAO) {
		int deleteId = 6;
		System.out.println("> Deleting student, id = " + deleteId);
		studentDAO.delete(deleteId);
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("> Deleting all students");
		int n = studentDAO.deleteAll();
		System.out.println("> Deleted row counts: " + n);
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
