package ca.mcgill.ecse321.ecse321project.service;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Date;
import java.sql.Time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.ecse321project.dao.BookingRepository;
import ca.mcgill.ecse321.ecse321project.dao.CourseRepository;
import ca.mcgill.ecse321.ecse321project.dao.ReviewRepository;
import ca.mcgill.ecse321.ecse321project.dao.RoomRepository;
import ca.mcgill.ecse321.ecse321project.dao.StudentRepository;
import ca.mcgill.ecse321.ecse321project.dao.TutorRepository;
import ca.mcgill.ecse321.ecse321project.dao.TutorScheduleRepository;
import ca.mcgill.ecse321.ecse321project.model.*;
import ca.mcgill.ecse321.ecse321project.service.ECSE321ProjectService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestECSE321ProjectService {
	
	@Autowired
	private ECSE321ProjectService service;
	
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	TutorRepository tutorRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	TutorScheduleRepository tutorScheduleRepository;
	@Autowired
	RoomRepository roomRepository;
	@Autowired
	ReviewRepository reviewRepository;
	@Autowired
	BookingRepository bookingRepository;
	
	@After
	public void clearDatabase() {
		bookingRepository.deleteAll();
		roomRepository.deleteAll();
		reviewRepository.deleteAll();
		courseRepository.deleteAll();
		tutorScheduleRepository.deleteAll();
		studentRepository.deleteAll();
		tutorRepository.deleteAll();
	}
	
	//Booking Test
	@Test
	public void testCreateBooking() {
		
		String school = "McGill University";
		String type = "Engineering";
		String level = "Post-secondary";
		
		int month = 03;
		int year = 2019;
		int day = 26;
		int duration = 3;
		int hour = 16;
		int minute = 0;
		int second = 0;
		
		boolean approval = true;
		boolean assignedRoom = true;
		boolean group = false;
		
		@SuppressWarnings("deprecation")
		Date date = new Date(year, month, day);
		@SuppressWarnings("deprecation")
		Time startTime = new Time(hour, minute, second);
		Tutor tutor = service.createTutor("John" , "john@mail.ca", "forever21");
		Student student = service.createStudent("Wick" , "wick@mail.ca", "forever21");
		Course course = service.createCourse("Intro to Software Engineering", "ECSE 321" , school, type, level);
		Room room = service.createRoom("Redpath Library", "RM1", true);
		
		Set<Review> reviews =  (Set<Review>) reviewRepository.findAll();
		List<Booking> allBookings = (List<Booking>) bookingRepository.findAll();
			
		assertEquals(0, service.getBookingByCourse(course));
		assertEquals(0, service.getBookingByDate(date));
		assertEquals(0, service.getBookingByStudent(student));
		assertEquals(0, service.getBookingByTutor(tutor));
		assertEquals(0, allBookings.size());
				
		try {
			service.createBooking(student, tutor, room, course, duration, date, startTime) ;			
		} 
		catch (IllegalArgumentException e) {
			fail();
		}
					
		assertEquals(1, allBookings.size());
		
		Booking booking = service.createBooking(student, tutor, room, course, duration, date, startTime);
		booking.setApproval(approval);
		booking.setGroupRequest(group);
		booking.setRoomAssigned(assignedRoom);
		booking.setReview(reviews);
		
		assertEquals(course, service.getBookingByCourse(course));
		assertEquals(date, service.getBookingByDate(date));
		assertEquals(student, service.getBookingByStudent(student));
		assertEquals(tutor, service.getBookingByTutor(tutor));
		assertEquals(duration, allBookings.get(0).getDuration());
		assertEquals(approval, allBookings.get(0).isApproval());
		assertEquals(assignedRoom, allBookings.get(0).isRoomAssigned());
		assertEquals(group, allBookings.get(0).isGroupRequest());
	}
	
	@Test
	public void testCreateBookingNullStudent() {
		List<Booking> allBookings = (List<Booking>) bookingRepository.findAll();
		assertEquals(0, allBookings.size());
		
		String school = "McGill University";
		String type = "Engineering";
		String level = "Post-secondary";
		
		int month = 03;
		int year = 2019;
		int day = 26;
		int duration = 3;
		int hour = 16;
		int minute = 0;
		int second = 0;

		@SuppressWarnings("deprecation")
		Date date = new Date(year, month, day);
		@SuppressWarnings("deprecation")
		Time startTime = new Time(hour, minute, second);
		
		Tutor tutor = service.createTutor("John" , "john@mail.ca", "forever21");
		Student student = null;
		Course course = service.createCourse("Intro to Software Engineering", "ECSE 321" , school, type, level);
		Room room = service.createRoom("Redpath Library", "RM1", true);
			
		String error = null;
		
		try {
			service.createBooking(student, tutor, room, course, duration, date, startTime);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error
		assertEquals("Booking name cannot be empty!", error);

		// check no change in memory
		assertEquals(0, allBookings.size());
	}
	
	@Test
	public void testCreateBookingNullTutor() {
		
		List<Booking> allBookings = (List<Booking>) bookingRepository.findAll();
		assertEquals(0, allBookings.size());
		
		String school = "McGill University";
		String type = "Engineering";
		String level = "Post-secondary";
		
		int month = 03;
		int year = 2019;
		int day = 26;
		int duration = 3;
		int hour = 16;
		int minute = 0;
		int second = 0;

		@SuppressWarnings("deprecation")
		Date date = new Date(year, month, day);
		@SuppressWarnings("deprecation")
		Time startTime = new Time(hour, minute, second);
		
		Tutor tutor = null;
		Student student =  service.createStudent("Wick" , "wick@mail.ca", "forever21");
		Course course = service.createCourse("Intro to Software Engineering", "ECSE 321" , school, type, level);
		Room room = service.createRoom("Redpath Library", "RM1", true);
		
		String error = null;
		
		try {
			service.createBooking(student, tutor, room, course, duration, date, startTime);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Tutor cannot be empty!", error);

		// check no change in memory
		assertEquals(0, allBookings.size());
	}
	
	@Test
	public void testCreateBookingNullCourse() {
		List<Booking> allBookings = (List<Booking>) bookingRepository.findAll();
		assertEquals(0, allBookings.size());
		
		int month = 03;
		int year = 2019;
		int day = 26;
		int duration = 3;
		int hour = 16;
		int minute = 0;
		int second = 0;
		
		@SuppressWarnings("deprecation")
		Date date = new Date(year, month, day);
		@SuppressWarnings("deprecation")
		Time startTime = new Time(hour, minute, second);
		
		Tutor tutor = service.createTutor("John" , "john@mail.ca", "forever21");
		Student student =  service.createStudent("Wick" , "wick@mail.ca", "forever21");
		Room room = service.createRoom("Redpath Library", "RM1", true);
		
		String error = null;
		
		try {
			service.createBooking(student, tutor, room, null, duration, date, startTime);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error
		assertEquals("Course cannot be empty!", error);

		// check no change in memory
		assertEquals(0, allBookings.size());
	}
	
	@Test
	public void testCreateBookingIllegalDuration() {
		
		List<Booking> allBookings = (List<Booking>) new ArrayList<Booking>();
		assertEquals(0, allBookings.size());
		
		String school = "McGill University";
		String type = "Engineering";
		String level = "Post-secondary";
		
		int month = 03;
		int year = 2019;
		int day = 26;
		int hour = 16;
		int minute = 0;
		int second = 0;
		
		@SuppressWarnings("deprecation")
		Date date = new Date(year, month, day);
		@SuppressWarnings("deprecation")
		Time startTime = new Time(hour, minute,second);
		
		Tutor tutor = service.createTutor("John" , "john@mail.ca", "forever21");
		Student student =  service.createStudent("Wick" , "wick@mail.ca", "forever21");
		Course course = service.createCourse("Intro to Software Engineering", "ECSE 321" , school, type, level);
		Room room = service.createRoom("Redpath Library", "RM1", true);

		String error = null;
		
		try {
			service.createBooking(student, tutor, room, course, -1, date, startTime);
			allBookings = service.getAllBookings();
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Duration must be 1 or greater.", error);

		// check no change in memory
		assertEquals(0, allBookings.size());
	}
	
	@Test
	public void testCreateBookingNullDate() {
		
		List<Booking> allBookings = (List<Booking>) bookingRepository.findAll();
		assertEquals(0, allBookings.size());

		String school = "McGill University";
		String type = "Engineering";
		String level = "Post-secondary";
		
		int duration = 3;
		int hour = 16;
		int minute = 0;
		int second = 0;
		
		@SuppressWarnings("deprecation")
		Time startTime = new Time(hour, minute, second);
		
		Tutor tutor = service.createTutor("John" , "john@mail.ca", "forever21");
		Student student =  service.createStudent("Wick" , "wick@mail.ca", "forever21");
		Course course = service.createCourse("Intro to Software Engineering", "ECSE 321" , school, type, level);
		Room room = service.createRoom("Redpath Library", "RM1", true);
		
		String error = null;
	
		try {
			service.createBooking(student, tutor, room, course, duration, null, startTime);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Date cannot be empty!", error);

		// check no change in memory
		assertEquals(0, allBookings.size());
	}

	@Test
	public void testCreateBookingNullTime() {
		
		List<Booking> allBookings = (List<Booking>) new ArrayList<Booking>();
		assertEquals(0, allBookings.size());

		String school = "McGill University";
		String type = "Engineering";
		String level = "Post-secondary";
		
		int duration = 3;
		int month = 03;
		int year = 2019;
		int day = 26;
		
		@SuppressWarnings("deprecation")
		Date date = new Date(year, month, day);
		
		Tutor tutor = service.createTutor("John" , "john@mail.ca", "forever21");
		Student student =  service.createStudent("Wick" , "wick@mail.ca", "forever21");
		Course course = service.createCourse("Intro to Software Engineering", "ECSE 321" , school, type, level);
		Room room = service.createRoom("Redpath Library", "RM1", true);
		String error = null;
		
		try {
			service.createBooking(student, tutor, room, course, duration, date, null);
			allBookings = service.getAllBookings();
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Date cannot be empty!", error);

		// check no change in memory
		assertEquals(0, allBookings.size());
	}
	
//Room Test
	@Test
	public void testCreateRoom(){
		
		List<Room> allRooms = (List<Room>) new ArrayList<Room>();
		
		String details = "Schulich";
		String key = "SK10";
		boolean roomSize = false;
		
		assertEquals(0, allRooms.size());
		
		try 
		{
			service.createRoom(details, key, roomSize) ;
			allRooms = service.getAllRooms();
			
		} catch (IllegalArgumentException e) {
			fail();
		}
			
		assertEquals(1, allRooms.size());
		
		Room room  = service.createRoom(details, key, roomSize);
		
		assertEquals(room.getCode(), service.getRoom(key).getCode());
	}

	@Test
	public void testCreateRoomNullKey(){
		
		List<Room> allRooms = (List<Room>) new ArrayList<Room>();
		
		String details = "Schulich";
		String key = null;
		boolean roomSize = false;
		
		String error = null;

		try {
			service.createRoom(details, key, roomSize);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Key cannot be empty!", error);

		// check no change in memory
		assertEquals(0, allRooms.size());
	}

//Tutor Test
	@Test
	public void testCreateTutor(){
		List <Tutor> allTutors = (List<Tutor>) new ArrayList<Tutor>();
		
		assertEquals(0, allTutors.size());
		
		try 
		{
			service.createTutor("Max", "max@mail.ca", "maxman");
			allTutors = service.getAllTutors();
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(1, allTutors.size());
		
		Tutor tutor = service.createTutor("Wick", "wick@mail.ca", "wickman");
		
		assertEquals(tutor.getFullName(), service.getTutorByName("Wick").getFullName());
	}
	
	@Test
	public void testCreateTutorNullName()
	{
		List<Tutor> allTutors = (List<Tutor>) new ArrayList<Tutor>();

		String error = null;
		
		try {
			service.createTutor(null, "wick@mail.ca", "password");
			allTutors = service.getAllTutors();
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Name cannot be empty!", error);

		// check no change in memory
		assertEquals(0, allTutors.size());
	}
	
	@Test
	public void testCreateTutorNullPassword(){
		List<Tutor> allTutors = (List<Tutor>) new ArrayList<Tutor>();
		String error = null;

		try {
			service.createTutor("Wick", "wick@mail.ca", null);
			allTutors = service.getAllTutors();
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Password cannot be empty!", error);

		// check no change in memory
		assertEquals(0, allTutors.size());
	}
	
	@Test
	public void testCreateTutorNullEmail(){
		
		List<Tutor> allTutors = (List<Tutor>) new ArrayList<Tutor>();
		String error = null;
		
		try {
			service.createTutor("Wick", null, "password");
			allTutors = service.getAllTutors();
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Email cannot be empty!", error);

		// check no change in memory
		assertEquals(0, allTutors.size());
	}
	
//Course Test
	@Test
	public void testCreateCourse(){
		List<Course> allCourses = (List<Course>) new ArrayList<Course>();
		
		assertEquals(0, allCourses.size());
		
		try {
			service.createCourse("ECSE", "ECSE 321", "McGill University", "Engineering", "Post-secondary");	
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(1, allCourses.size());
		
		Course course = service.createCourse("Intro to Software Engineering", "ECSE 321", "McGill University", "Engineering", "Post-secondary");
		
		assertEquals(course, service.getCourseByName("ECSE"));
		assertEquals(course, service.getCourseByCode("ECSE 321"));
		assertEquals(course, service.getCourseByInstitution("McGill University"));
	}
	
	@Test
	public void testCreateCoursesNullName(){
		List<Course> allCourses = (List<Course>) new ArrayList<Course>();

		String error = null;
		
		try {
			service.createCourse(null, "ECSE 321" , "McGill University", "Engineering", "Post-secondary");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Name of course cannot be empty!", error);

		// check no change in memory
		assertEquals(0, allCourses.size());
	}
	
	@Test
	public void testCreateCoursesIllegalCode(){
		List<Course> allCourses = (List<Course>) new ArrayList<Course>();

		String error = null;

		try {
			service.createCourse("Intro to Software Engineering", "ECSE 321", "McGill University", "Engineering", "Post-secondary");
			allCourses = service.getAllCourses();
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Course code must be greater than 0.", error);

		// check no change in memory
		assertEquals(0, allCourses.size());
	}
	
	@Test
	public void testCreateCoursesNullInstitution(){
		List<Course> allCourses = (List<Course>) new ArrayList<Course>();

		String error = null;
		
		try {
			service.createCourse("Intro to Software Engineering", "ECSE 321", null, "Engineering", "Post-secondary");
			allCourses = service.getAllCourses();
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Institution cannot be empty!", error);

		// check no change in memory
		assertEquals(0, allCourses.size());
	}
	
	@Test
	public void testCreateCoursesNullFaculty(){
		List<Course> allCourses = (List<Course>) new ArrayList<Course>();

		String error = null;
		
		try {
			service.createCourse("Intro to Software Engineering", "ECSE 321", "McGill", null, "Post-secondary");
			allCourses = service.getAllCourses();
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Faculty cannot be empty!", error);

		// check no change in memory
		assertEquals(0, allCourses.size());	
	}
	
	@Test
	public void testCreateCoursesNullLevel(){
		List<Course> allCourses = (List<Course>) new ArrayList<Course>();

		String error = null;

		try {
			service.createCourse("Intro to Software Engineering", "ECSE 321", "McGill", "Engineering", null);
			allCourses = service.getAllCourses();
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Level cannot be empty!", error);

		// check no change in memory
		assertEquals(0, allCourses.size());
	}

//Student Test
	@Test
	public void testCreateStudent(){
		List <Student> allStudents = (List<Student>) new ArrayList<Student>();
		
		assertEquals(0, allStudents.size());
		
		try {
			service.createStudent("Max", "max@mail.ca", "maxman");
			allStudents = service.getAllStudents();	
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(1, allStudents.size());
		
		Student student = service.createStudent("Morgan", "morgan@mail.ca", "morganstanley");
		
		assertEquals(student.getFullName(), service.getStudent("Morgan").getFullName());
	}
	
	@Test
	public void testCreateStudentsNullName(){
		List <Student> allStudents = (List<Student>) new ArrayList<Student>();

		String error = null;
		
		try {
			service.createStudent(null, "max@mail.ca", "maxman");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Name cannot be empty!", error);

		// check no change in memory
		assertEquals(0, allStudents.size());
	}
	
	@Test
	public void testCreateStudentsNullEmail(){
		List <Student> allStudents = (List<Student>) new ArrayList<Student>();

		String error = null;
		
		try {
			service.createStudent("Max", null, "maxman");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Email cannot be empty!", error);

		// check no change in memory
		assertEquals(0, allStudents.size());
	}
	
	@Test
	public void testCreateStudentNullPassword(){
		List <Student> allStudents = (List<Student>) new ArrayList<Student>();

		String error = null;
		
		try {
			service.createStudent("Max", "max@mail.ca", null);
			allStudents = service.getAllStudents();
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Password cannot be empty!", error);

		// check no change in memory
		assertEquals(0, allStudents.size());
	}

//Review Test
	@Test
	public void testCreateReview() {
		List <Review> allReviews = (List<Review>) service.reviewRepository.findAll();
		
		assertEquals(0, allReviews.size());
		
		Tutor tutor = 	service.createTutor("Max", "max@mail.ca", "maxman");
		Student student = service.createStudent("Name", "yal@mail.ca", "helloworld");
		String comment = "This is a comment.";
		Room room = service.createRoom("1st floor", "rm105", false);
		Course course = service.createCourse("Calculus", "MATH 140",  "McGill", "Engineering", "Bachelor");
		
		int month = 03;
		int year = 2019;
		int day = 26;
		int hour = 16;
		int minute = 0;
		int second = 0;

		Date date = new Date(year, month, day);
		Time startTime = new Time(hour, minute, second);
		
		Booking booking = service.createBooking(student, tutor, room, course, 60 , date, startTime);
		int rate = 4;
		
		try {
			service.createReview(booking, comment, rate);	
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		Review review = service.createReview(booking, comment, rate);
		
		assertEquals(1, allReviews.size());	
		assertEquals(review, service.getReview(booking));
	}
	
	@Test
	public void testCreateReviewNullComment()
	{
		List <Review> allReviews = (List<Review>) service.reviewRepository.findAll();
		
		assertEquals(0, allReviews.size());
		
		int month = 03;
		int year = 2019;
		int day = 26;
		int hour = 16;
		int minute = 0;
		int second = 0;

		Date date = new Date(year, month, day);
		Time startTime = new Time(hour, minute, second);
		
		Room room = service.createRoom("1st floor", "rm105", false);
		Course course = service.createCourse("Calculus", "MATH 140",  "McGill", "Engineering", "Bachelor");
		Student student = service.createStudent("Name", "yal@mail.ca", "helloworld");
		Booking booking = service.createBooking(student, null, room, course, 2,date , startTime);
		
		service.createTutor("Max", "max@mail.ca", "maxman");
	
		String error = null;
		
		assertEquals(0, allReviews.size());	
		
		int rate=3;
		try {
			service.createReview(booking,error , rate);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("Comment cannot be empty!", error);

		// check no change in memory
		assertEquals(0, allReviews.size());
	}
	
	@SuppressWarnings("deprecation")
	public void testCreateReviewNullTutor()
	{
		List <Review> allReviews = (List<Review>) service.reviewRepository.findAll();
		assertEquals(0, allReviews.size());
		int month = 03;
		int year = 2019;
		int day = 26;
		int hour = 16;
		int minute = 0;
		int second = 0;

		new Date(year, month, day);
		
		new Time(hour, minute, second);
		service.createRoom("1st floor", "rm105", false);
		service.createCourse("Calculus", "MATH 140",  "McGill", "Engineering", "Bachelor");
		
		service.createStudent("Name", "yal@mail.ca", "helloworld");
		String error = null;
		assertEquals(0, allReviews.size());		

		// check error
		assertEquals("Tutor cannot be empty!", error);

		// check no change in memory
		assertEquals(0, allReviews.size());
		
	}
	
	@Test
	public void testCreateReviewNullStudent()
	{
		List <Review> allReviews = (List<Review>) service.reviewRepository.findAll();
		assertEquals(0, allReviews.size());
		int month = 03;
		int year = 2019;
		int day = 26;
		int hour = 16;
		int minute = 0;
		int second = 0;

		@SuppressWarnings("deprecation")
		Date date = new Date(year, month, day);
		@SuppressWarnings("deprecation")
		Time startTime = new Time(hour, minute, second);
		Room room = service.createRoom("1st floor", "rm105", false);
		Course course = service.createCourse("Calculus", "MATH 140",  "McGill", "Engineering", "Bachelor");
		Tutor tutor = 	service.createTutor("Max", "max@mail.ca", "maxman");
		Booking booking = service.createBooking(null, tutor, room, course, 2,date , startTime);
		String comment = "This is a comment.";
		String error = null;
		int rate = 3;
		assertEquals(0, allReviews.size());	
		try 
		{
			service.createReview(booking, comment, rate);

			
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("Student cannot be empty!", error);

		// check no change in memory
		assertEquals(0, allReviews.size());
	}
}