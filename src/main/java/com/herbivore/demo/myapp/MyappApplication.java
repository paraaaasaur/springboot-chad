package com.herbivore.demo.myapp;


import com.herbivore.demo.myapp.dao.AppDAO;
import com.herbivore.demo.myapp.entity.Course;
import com.herbivore.demo.myapp.entity.Instructor;
import com.herbivore.demo.myapp.entity.InstructorDetail;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.PersistenceUtil;
import org.hibernate.LazyInitializationException;
import org.hibernate.collection.spi.PersistentSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.herbivore.demo.myapp.util.Trivial.aqtn;
import static io.github.paraaaasaur.util.Toolbox.*;


@SpringBootApplication
public class MyappApplication {

	private AppDAO appDAO;

	private PersistenceUtil persistenceUtil;

	@Value("${app.courseTitles}")
	private LinkedList<String> courseTitles;

	@Autowired
	public MyappApplication(AppDAO appDAO, PersistenceUtil persistenceUtil) {
		this.appDAO = appDAO;
		this.persistenceUtil = persistenceUtil;
	}

	public static void main(String[] args) {
		SpringApplication.run(MyappApplication.class, args);
	}

	@Bean
	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	public CommandLineRunner commandLineRunner(String[] args) {
		return runner -> {
//			createInstructor(appDAO);

//			findInstructor(appDAO, 1);

//			updateInstructor(appDAO);

//			deleteInstructor(appDAO, 27);

//			findDetail(appDAO, 27);

//			updateDetail(appDAO, 27);

//			deleteDetail(appDAO, 31);

//			createInstructorWithCourses(appDAO);

//			findInstructorWithCourses(appDAO, 1);

//			findCoursesForInstructor(appDAO, 5);

//			testLazyObject(appDAO, 1);

//			testLazyCollection(appDAO, 5);

//			findInstructorWithCoursesJoinFetch(appDAO, 5);

//			updateCourseTitle(appDAO, 10);

//			deleteInstructor(appDAO, 1);

			// Throws LazyInitializationException if not initialized somehow
			appDAO.findInstructorById(3, true).getCourses().size();
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

	private void findInstructorWithCourses(AppDAO appDAO, int instructorId) {
		System.out.println(cyan("> Retrieving Instructor with id = " + instructorId + "..."));

		Instructor found = appDAO.findInstructorById(instructorId);

		System.out.println(found);
		System.out.println(found.getCourses());

		aqtn();
	}

	private void findCoursesForInstructor(AppDAO appDAO, int instructorId) {
		System.out.println(cyan("> Retrieving Instructor with id = " + instructorId + "..."));
		Instructor foundInstructor = appDAO.findInstructorById(instructorId);
		System.out.println(foundInstructor.getFirstName() + " " +
						   foundInstructor.getLastName());

		System.out.println(cyan("> Retrieving Courses with instructor_id = " + instructorId + "..."));
		Set<Course> foundCourses = appDAO.findCoursesById(instructorId);

		var lazyCourses = (PersistentSet<Course>) foundInstructor.getCourses();



		System.out.println(persistenceUtil.isLoaded(lazyCourses));
//		System.out.println(persistenceUtil.isLoaded(foundInstructor, "courses"));
//		Hibernate.initialize(foundInstructor);
//		System.out.println(Hibernate.isInitialized(lazyCourses));


		foundInstructor.setCourses(foundCourses);
		foundCourses.forEach(course -> course.setInstructor(foundInstructor));

		System.out.println(cyan("> The associated courses: "
								+ foundInstructor.getCourses()));

		aqtn();
	}

	/** You should set fetch type LAZY in Course class to test*/
	private void testLazyObject(AppDAO appDAO, int instructorId) {
		System.out.println(yellow("(Set fetch type LAZY for Course before testing!)"));
		System.out.println(cyan("> Retrieving Courses with instructor_id = " + instructorId + "..."));
		Set<Course> foundCourses = appDAO.findCoursesById(instructorId);
		Course course = foundCourses.stream().reduce(null, (r, v) -> v);
		Assert.notNull(course, "course is null");
		Instructor lazyInstructor = course.getInstructor();

		// Fields except for ID are not accessible for lazily loaded entity
		Set<Supplier<Object>> getters = Stream.of((Supplier<Object>)
						// ↓ pass ↓
						lazyInstructor::getId,
				lazyInstructor::getClass,
				lazyInstructor::hashCode,
				() -> lazyInstructor.equals(lazyInstructor),
				// ↓ fail ↓
				lazyInstructor::getFirstName,
				lazyInstructor::getLastName,
				lazyInstructor::getEmail,
				lazyInstructor::getInstructorDetail,
				lazyInstructor::getCourses
		).collect(Collectors.toCollection(LinkedHashSet::new));

		getters.forEach(getter -> {
			try {
				System.out.println(blue(getter.get().toString()));
			} catch (LazyInitializationException e) {
				System.err.println(e.getClass().getSimpleName() + ": " + e.getMessage());
			}
		});

		aqtn();
	}

	/** You should set fetch type LAZY in Instructor class to test*/
	private void testLazyCollection(AppDAO appDAO, int instructorId) {
		System.out.println(yellow("(Set fetch type LAZY for Instructor class before testing!)"));
		System.out.println(cyan("> Retrieving Courses with instructor_id = " + instructorId + "..."));
		Instructor foundInstructor = appDAO.findInstructorById(instructorId);
		Set<Course> lazyCourses = foundInstructor.getCourses();

		Map<String, Runnable> logics = new HashMap<>();
		// fail
		logics.put(".equal()", () -> lazyCourses.equals(lazyCourses));
		logics.put(".add()", () -> lazyCourses.add(new Course("!Hola")));
		logics.put(".contains()", () -> lazyCourses.contains(null));
		logics.put(".remove()", () -> lazyCourses.remove(null));
		logics.put(".stream() + .forEach()", () -> lazyCourses.stream().forEach(System.out::println));
		logics.put(".toString()", lazyCourses::toString);
		logics.put(".clear()", lazyCourses::clear);
		logics.put(".notify()", lazyCourses::notify);
		logics.put(".hashCode()", lazyCourses::hashCode);
		logics.put(".isEmpty()", lazyCourses::isEmpty);
		logics.put(".size()", lazyCourses::size);
		logics.put(".iterator()", lazyCourses::iterator);
		logics.put("new HashSet", () -> new HashSet<>(lazyCourses));
		// pass
		logics.put(".stream()", lazyCourses::stream);

		logics.forEach((methodName, logic) -> {
			try {
				logic.run();
				System.out.println(blue(methodName + " passed^_^"));
			} catch (Exception e) {
				System.err.println(yellow(methodName + " failed!"));
				System.err.println(e.getClass().getSimpleName() + ": " + e.getMessage());
			}
		});

		aqtn();
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO, int id) {
		System.out.println(cyan("> JOIN FETCHing Instructor + courses with id = " + id + "..."));
		Instructor jfi = appDAO.findInstructorByIdJoinFetch(5);
		System.out.println(yellow(jfi + ""));
		System.out.println(yellow(jfi.getInstructorDetail() + ""));
		jfi.getCourses().stream()
				.sorted(Comparator.comparing(Course::getId))
				.forEach(course -> System.out.println(yellow("- " + course)));

		aqtn();
	}

	private void updateCourseTitle(AppDAO appDAO, int courseId) {
		System.out.println(cyan("> Finding course title with id = " + courseId + "..."));

		Course foundCourse = appDAO.findCourseById(courseId);

		foundCourse.setTitle(getRandomCourseTitle());

		System.out.println(cyan("> Updating course title with id = " + courseId + "..."));

		appDAO.updateCourse(foundCourse);

		if (false) {
			// ObjectOptimisticLockingFailureException blablabla
			Instructor i1 = appDAO.findInstructorByIdJoinFetch(1);
			Course c = new Course(getRandomCourseTitle());
//			c.setId(9);
			i1.associate(c);
			appDAO.updateCourse(c);
		}

		aqtn();
	}

	private String getRandomCourseTitle() {
		Collections.shuffle(courseTitles);
		int randomYear = ThreadLocalRandom.current().nextInt(1977, 3077);
		return courseTitles.element() + "(" + randomYear + ")";
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
