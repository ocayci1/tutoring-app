
package ca.mcgill.ecse321.ecse321project.service;

import java.util.List;
import org.junit.runner.RunWith; 
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ca.mcgill.ecse321.ecse321project.dao.*;
import org.mockito.invocation.InvocationOnMock;
import ca.mcgill.ecse321.ecse321project.model.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

//import java.awt.List;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//ERROR MESSAGES

@RunWith(MockitoJUnitRunner.class)

public class BackendTests {
	@Mock 
	private BookingRepository bookingRepository;
	@Mock
	private CourseRepository courseRepository;  
	@Mock
	private ReviewRepository reviewRepository;  
	@Mock
	private TutorRepository tutorRepository; 
	@Mock
	private StudentRepository studentRepository; 
	@Mock
	private  RoomRepository roomRepository; 
	@Mock
	private  TutorScheduleRepository tutorScheduleRepository; 
	
	@InjectMocks
	private ECSE321ProjectService service;

	// constants for course
	private static final String COURSE_NAME = "Intro to Software Engineering";
	private static final String COURSE_CODE = "ECSE 321";
	private static final String COURSE_INSTITUTION = "McGill";
	private static final String COURSE_FACULTY = "Engineering";
	private static final String COURSE_LEVEL = "Post-Secondary";
		
	// constants for tutor
	private static final String TUTOR_NAME = "Mike";
	private static final String TUTOR_EMAIL = "mike@mail.ca";
	private static final String TUTOR_PASSWORD = "mike123";
		
	// constants for student
	private static final String STUDENT_NAME = "Mikel";
	private static final String STUDENT_EMAIL = "mikel@mail.ca";
	private static final String STUDENT_PASSWORD = "mikel123";
	
	// constant for room
	private static final String ROOM_DESCRIPTION= "Schulich";
	private static final String ROOM_CODE= "S-37";
	private static final boolean ROOM_BIGROOM= true;
	
	// constants for booking
	private static final int DURATION = 2;
	private static final int WRONG_DURATION = -2;
	private static final Date SESSION_DATE = Date.valueOf("2020-02-01");
	private static final Date WRONG_SESSION_DATE = Date.valueOf("1000-10-01");
	private static final Time SESSION_TIME = Time.valueOf("23:59:01");
	private static final Time WRONG_SESSION_TIME = Time.valueOf("00:00:00");
	
	// constants for review
	private static final String COMMENT = "Excellent teacher";
	private static final int RATE=4;
	
	
	//HAVE TO CLEAR TOO
	@Before
	public void setMockOutput() {
		
		// run all setups for mock outputs

		setMockStudentOutput();
		setMockCourseOutput();
		setMockCourseByInstOutput();
		setMockCourseByNameOutput();
		setMockCourseByCodeOutput();
		setMockScheduleOutput();
		setMockTutorOutput();
		setMockRoomOutput();
		setMockBookingOutput();
		setMockReviewOutput();
	}
	 
	private void setMockBookingOutput() {
		when(bookingRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
		Booking b = new Booking();
	
		Course c = new Course();
		
		c.setName(COURSE_NAME);
		c.setCode(COURSE_CODE);
		c.setFaculty(COURSE_FACULTY);
		c.setInstitution(COURSE_INSTITUTION);
		c.setLevel(COURSE_LEVEL);
		
		Student st = new Student();
		st.setFullName(STUDENT_NAME);
		st.setEmail(STUDENT_EMAIL);
		st.setPassword(STUDENT_PASSWORD);
		
		Tutor t = new Tutor();
		t.setFullName(TUTOR_NAME);
		t.setEmail(TUTOR_EMAIL);
		t.setPassword(TUTOR_PASSWORD);
		
		b.setCourse(c);
		b.setDate(SESSION_DATE);
		b.setDuration(DURATION);
		b.setTutor(t);
		b.setStudent(st);
		
		return b;
		});
	}
	
	@SuppressWarnings("unchecked")
	public void setMockReviewOutput() {
		
		when(reviewRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
		Review review = new Review();
		
		Tutor t = new Tutor();
		t.setFullName(TUTOR_NAME);
		t.setEmail(TUTOR_EMAIL);
		t.setPassword(TUTOR_PASSWORD);
		
		Student st = new Student();
		st.setFullName(STUDENT_NAME);
		st.setEmail(STUDENT_EMAIL);
		st.setPassword(STUDENT_PASSWORD);
		
		Course c = new Course();
		c.setName(COURSE_NAME);
		c.setCode(COURSE_CODE);
		c.setFaculty(COURSE_FACULTY);
		c.setInstitution(COURSE_INSTITUTION);
		c.setLevel(COURSE_LEVEL);
		
		Booking b = new Booking();
		b.setCourse(c);
		b.setDate(SESSION_DATE);
		b.setDuration(DURATION);
		b.setTutor(t);
		b.setStudent(st);
		b.setReview((Set<Review>) review);
		
		review.setBooking(b);
		review.setComment(COMMENT);
				
		return review;
		});
	}

	private void setMockRoomOutput() {
	
		when(roomRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			
			Room r = new Room();
			r.setBigRoom(true);
			r.setCode(ROOM_CODE);
			r.setDescription(ROOM_DESCRIPTION);
						
			Booking b = new Booking();
			
			Course c = new Course();
			c.setName(COURSE_NAME);
			c.setCode(COURSE_CODE);
			c.setFaculty(COURSE_FACULTY);
			c.setInstitution(COURSE_INSTITUTION);
			c.setLevel(COURSE_LEVEL);
			
			Student st = new Student();
			st.setFullName(STUDENT_NAME);
			st.setEmail(STUDENT_EMAIL);
			st.setPassword(STUDENT_PASSWORD);
			
			Tutor t = new Tutor();
			t.setFullName(TUTOR_NAME);
			t.setEmail(TUTOR_EMAIL);
			t.setPassword(TUTOR_PASSWORD);
			
			b.setCourse(c);
			b.setDate(SESSION_DATE);
			b.setDuration(DURATION);
			b.setTutor(t);
			b.setStudent(st);
			b.setRoom(r);
			b.setStartTime(SESSION_TIME);
	
			return r;
		});
	}
	
	private void setMockTutorOutput() {
		
			when(tutorRepository.findTutorByFullName((anyString()))).thenAnswer((InvocationOnMock invocation) -> {
				if (invocation.getArgument(0).equals(TUTOR_NAME)) {
					
					Tutor t = new Tutor();
					t.setFullName(TUTOR_NAME);
					t.setEmail(TUTOR_EMAIL);
					t.setPassword(TUTOR_PASSWORD);
			
					return t;
				} else {
					
					return null;
				}
			});
		
	}
	
	private void setMockScheduleOutput() {  
		when(tutorScheduleRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
		
		TutorSchedule ts = new TutorSchedule();
		Tutor t = new Tutor();
		t.setFullName(TUTOR_NAME);
		t.setEmail(TUTOR_EMAIL);
		t.setPassword(TUTOR_PASSWORD);
		
		ts.setDate(SESSION_DATE);
		ts.setTutor(t);
		
		return ts;
		});
		
			
	}
	
	private void setMockCourseOutput() {
		when(courseRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(COURSE_NAME)) {
				Course c = new Course();
				
				c.setName(COURSE_NAME);
				c.setCode(COURSE_CODE);
				c.setFaculty(COURSE_FACULTY);
				c.setInstitution(COURSE_INSTITUTION);
				c.setLevel(COURSE_LEVEL);
				return c;
			}
			return null;
		});
	}
	
	private void setMockCourseByInstOutput() {
			when(courseRepository.findCourseByInstitution(anyString())).thenAnswer((InvocationOnMock invocation) -> {
				List<Course> cs = new ArrayList<Course>();
				
				if(invocation.getArgument(0).equals(COURSE_INSTITUTION)) {
					Course c = new Course();
					
					c.setName(COURSE_NAME);
					c.setCode(COURSE_CODE);
					c.setFaculty(COURSE_FACULTY);
					c.setInstitution(COURSE_INSTITUTION);
					c.setLevel(COURSE_LEVEL);
					cs.add(c);
					return cs;
					
				}else {
					
					return null;
				}
		});
	}
	
	private void setMockCourseByNameOutput() {
		when(courseRepository.findCourseByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			List<Course> cs = new ArrayList<Course>();
			
			if(invocation.getArgument(0).equals(COURSE_NAME)) {
				Course c = new Course();
				
				c.setName(COURSE_NAME);
				c.setCode(COURSE_CODE);
				c.setFaculty(COURSE_FACULTY);
				c.setInstitution(COURSE_INSTITUTION);
				c.setLevel(COURSE_LEVEL);
				cs.add(c);
				return cs;
				
			}else {
				
				return null;
			}
		});
	}
	
	private void setMockCourseByCodeOutput() {
		when(courseRepository.findCourseByCode(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			List<Course> cs = new ArrayList<Course>();
			
			if(invocation.getArgument(0).equals(COURSE_CODE)) {
				Course c = new Course();
				
				c.setName(COURSE_NAME);
				c.setCode(COURSE_CODE);
				c.setFaculty(COURSE_FACULTY);
				c.setInstitution(COURSE_INSTITUTION);
				c.setLevel(COURSE_LEVEL);
				cs.add(c);
				return cs;
				
			}else {
				
				return null;
			}
		});
	}
	
	public void setMockStudentOutput() {
			when(studentRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
	
				// create a student				
				Set<Student> stu = new HashSet<>();
				Student st = new Student();
				st.setFullName(STUDENT_NAME);
				st.setEmail(STUDENT_EMAIL);
				st.setPassword(STUDENT_PASSWORD);
				stu.add(st);
				
				return stu;
			});
		}
	
	//================ TESTS ==================//

	
	//COURSE TEST
	@Test
	public void getCourseByInstitution() {
		List<Course> courses = new ArrayList <Course> ();
		
		try {
			courses = service.getCourseByInstitution(COURSE_INSTITUTION);
			
		} catch(IllegalArgumentException e) { fail();}
		
		assertEquals(1, courses.size());
		assertEquals(COURSE_NAME, courses.get(0).getName());	
	}
		
	@Test
	public void getCourseByCode() { 
		List<Course> courses = new ArrayList<Course>();
		
		try {
		courses = service.getCourseByCode(COURSE_CODE);
			
		} catch(IllegalArgumentException e) { fail();}
		
		assertEquals(1, courses.size());
		assertEquals(COURSE_INSTITUTION, courses.get(0).getInstitution());	
	}
	
	@Test
	public void getCourseByName() { 
		List<Course> courses = new ArrayList<Course> ();
			 
		try {
			courses =service.getCourseByName(COURSE_NAME);
		} catch(IllegalArgumentException e) { fail();}
	
		assertEquals(1, courses.size());
		assertEquals(COURSE_CODE, courses.get(0).getCode());	
	}
	
//STUDENT TEST
	@Test
	public void createStudent() {
		List <Student> students = new ArrayList<Student>();
		try {
			Student s = service.createStudent(STUDENT_NAME, STUDENT_EMAIL, STUDENT_PASSWORD);
			students.add(s);
		}catch(IllegalArgumentException e) { fail();}
		
		assertEquals(1, students.size());
	}
	
	@Test
	public void getStudentByEmail() {
		List<Student> students = new ArrayList<Student>();
		try {
			Student s = service.getStudentByEmail(STUDENT_EMAIL);
			students.add(s);
			
		}catch(IllegalArgumentException e) { fail();}
		
		assertEquals(1, students.size());
	}
			
// TUTOR TEST
	
	@Test
	public void getTutorProfileTest() {
		
		List<Tutor> tutors = new ArrayList <Tutor>();

		try {
			Tutor tutor = service.getTutorByName(TUTOR_NAME);
			tutors.add(tutor);
		} catch(IllegalArgumentException e) { fail();}
		
		assertEquals(1, tutors.size());
		assertEquals(TUTOR_NAME, tutors.get(0).getFullName());
	}
	
	@Test
	public void getTutorsOfCourseTest() {
		
	 Course course = service.createCourse(COURSE_NAME, COURSE_CODE, COURSE_INSTITUTION, COURSE_FACULTY, COURSE_LEVEL);
	 Tutor tutor = service.createTutor(TUTOR_NAME, TUTOR_EMAIL, TUTOR_PASSWORD);
	
	 Set <Course> courses = new HashSet<Course>();
	 List <Tutor> tutors = new 	ArrayList <Tutor>();
	 courses.add(course);
	 tutor.setCourse(courses);
	 	 
	 try {
		 tutors =  service.getTutorbyCourse(course);
	 }catch(IllegalArgumentException e) { fail();}
	 
	 assertEquals(1, tutors.size());
	 assertEquals(tutors, course.getTutor());

	}

	@Test
	public void getTutorScheduleOfTutorTest() {
		
		Tutor tutor = service.createTutor(TUTOR_NAME, TUTOR_EMAIL, TUTOR_PASSWORD);
		TutorSchedule ts = service.createTutorSchedule(SESSION_DATE, tutor);
				
		try {
			tutor.setTutorSchedule(ts);
		 }catch(IllegalArgumentException e) { fail();}
		
		assertEquals(ts, tutor.getTutorSchedule());
	}
	
// BOOKING Test

	@Test
	public void cancelBookingTest() //Check this
	{
		Tutor tutor = service.createTutor(TUTOR_NAME, TUTOR_EMAIL, TUTOR_PASSWORD);
		Student student = service.createStudent(STUDENT_NAME, STUDENT_EMAIL, STUDENT_PASSWORD);
		Course course = service.createCourse(COURSE_NAME, COURSE_CODE, COURSE_INSTITUTION, COURSE_FACULTY, COURSE_LEVEL);
		Room room = service.createRoom(ROOM_DESCRIPTION, ROOM_CODE, ROOM_BIGROOM);
				
		List<Booking> allBookings = new ArrayList <Booking> ();
		Booking booking = service.createBooking(student, tutor, room, course, DURATION, SESSION_DATE, SESSION_TIME);
		allBookings.add(booking);
		Integer id = booking.getBookingId();
		
		assertEquals(1, allBookings.size());
				
		try {
			service.deleteBooking(booking);
		 }catch(IllegalArgumentException e) { fail();}
			
		assertEquals(0, allBookings.size());
			
	}
	
	@Test
	public void testCreateBookingMock() {
		
		
		Tutor tutor = service.createTutor(TUTOR_NAME, TUTOR_EMAIL, TUTOR_PASSWORD);
		Student student = service.createStudent(STUDENT_NAME, STUDENT_EMAIL, STUDENT_PASSWORD);
		Course course = service.createCourse(COURSE_NAME, COURSE_CODE, COURSE_INSTITUTION, COURSE_FACULTY, COURSE_LEVEL);
		Room room = service.createRoom(ROOM_DESCRIPTION, ROOM_CODE, ROOM_BIGROOM);
			
		List <Booking> allBookings = new ArrayList <Booking>();
	
		try 
		{
			Booking booking = service.createBooking(student, tutor, room, course, DURATION, SESSION_DATE, SESSION_TIME);
			allBookings.add(booking);
			
		} catch (IllegalArgumentException e) {fail();}
			
			assertEquals(1, allBookings.size());
			assertEquals(tutor, allBookings.get(0).getTutor());
			assertEquals(student, allBookings.get(0).getStudent());
			assertEquals(room, allBookings.get(0).getRoom());
			assertEquals(course, allBookings.get(0).getCourse());
			assertEquals(DURATION, allBookings.get(0).getDuration());
			assertEquals(SESSION_DATE, allBookings.get(0).getDate());
			assertEquals(SESSION_TIME, allBookings.get(0).getStartTime());
	}
	
	@Test
	public void testCreateBookingNullStudentMock() {
		
		
		Tutor tutor = service.createTutor(TUTOR_NAME, TUTOR_EMAIL, TUTOR_PASSWORD);
		Course course = service.createCourse(COURSE_NAME, COURSE_CODE, COURSE_INSTITUTION, COURSE_FACULTY, COURSE_LEVEL);
		Room room = service.createRoom(ROOM_DESCRIPTION, ROOM_CODE, ROOM_BIGROOM);
		
		List<Booking> allBookings = new ArrayList <Booking> ();
		String error = null;
		
		try {
			 Booking booking = service.createBooking(null, tutor, room, course, DURATION, SESSION_DATE, SESSION_TIME);
			 allBookings.add(booking);
		} catch(IllegalArgumentException e) { error = e.getMessage();}
				
		assertEquals(0, allBookings.size());
		assertEquals("Student cannot be empty!", error);

	}
	
	@Test
	public void testCreateBookingNullTutorMock() {
			
		Student student = service.createStudent(STUDENT_NAME, STUDENT_EMAIL, STUDENT_PASSWORD);
		Course course = service.createCourse(COURSE_NAME, COURSE_CODE, COURSE_INSTITUTION, COURSE_FACULTY, COURSE_LEVEL);
		Room room = service.createRoom(ROOM_DESCRIPTION, ROOM_CODE, ROOM_BIGROOM);
		
		List<Booking> allBookings = new ArrayList <Booking> ();
		String error = null;
		
		try {
			 Booking booking = service.createBooking(student, null, room, course, DURATION, SESSION_DATE, SESSION_TIME);
			 allBookings.add(booking);	
		} catch(IllegalArgumentException e) { error = e.getMessage();}
		
		assertEquals(0, allBookings.size());
		assertEquals("Tutor cannot be empty!", error);	
	}
	@Test
	public void testCreateBookingNullRoomMock() {
		
		Tutor tutor = service.createTutor(TUTOR_NAME, TUTOR_EMAIL, TUTOR_PASSWORD);
		Student student = service.createStudent(STUDENT_NAME, STUDENT_EMAIL, STUDENT_PASSWORD);
		Course course = service.createCourse(COURSE_NAME, COURSE_CODE, COURSE_INSTITUTION, COURSE_FACULTY, COURSE_LEVEL);
		List<Booking> allBookings = new ArrayList <Booking> ();
		String error = null;
		
		try {
			 Booking booking = service.createBooking(student, tutor, null, course, DURATION, SESSION_DATE, SESSION_TIME);
			 allBookings.add(booking);
			 
		} catch(IllegalArgumentException e) { error = e.getMessage();}	
		
			assertEquals(0, allBookings.size());
			assertEquals("Room cannot be empty!", error);
	}
	
	@Test
	public void testCreateBookingNullCourseMock() {
		
		Tutor tutor = service.createTutor(TUTOR_NAME, TUTOR_EMAIL, TUTOR_PASSWORD);
		Student student = service.createStudent(STUDENT_NAME, STUDENT_EMAIL, STUDENT_PASSWORD);
		Room room = service.createRoom(ROOM_DESCRIPTION, ROOM_CODE, ROOM_BIGROOM);
		
		List<Booking> allBookings = new ArrayList<Booking>();
		String error = null;
		
		try {
			Booking booking = service.createBooking(student, tutor, room, null, DURATION, SESSION_DATE, SESSION_TIME);
			allBookings.add(booking);
			
		} catch(IllegalArgumentException e) { error = e.getMessage();}
				
		assertEquals(0, allBookings.size());
		assertEquals("Course cannot be empty!", error);
	
	}
	@Test
	public void testCreateBookingNullDateMock() {
				
		Tutor tutor = service.createTutor(TUTOR_NAME, TUTOR_EMAIL, TUTOR_PASSWORD);
		Student student = service.createStudent(STUDENT_NAME, STUDENT_EMAIL, STUDENT_PASSWORD);
		Course course = service.createCourse(COURSE_NAME, COURSE_CODE, COURSE_INSTITUTION, COURSE_FACULTY, COURSE_LEVEL);
		Room room = service.createRoom(ROOM_DESCRIPTION, ROOM_CODE, ROOM_BIGROOM);
		List<Booking> allBookings = new ArrayList<Booking>();
		String error = null;
		
		try {
			Booking booking = service.createBooking(student, tutor, room, course, DURATION, null, SESSION_TIME);
			allBookings.add(booking);
		} catch(IllegalArgumentException e) { error = e.getMessage();}
				
		assertEquals(0, allBookings.size());
		assertEquals("Date cannot be empty!", error);
	}
	
	@Test
	public void testCreateBookingNullTimeMock() {
		
		Tutor tutor = service.createTutor(TUTOR_NAME, TUTOR_EMAIL, TUTOR_PASSWORD);
		Student student = service.createStudent(STUDENT_NAME, STUDENT_EMAIL, STUDENT_PASSWORD);
		Course course = service.createCourse(COURSE_NAME, COURSE_CODE, COURSE_INSTITUTION, COURSE_FACULTY, COURSE_LEVEL);
		Room room = service.createRoom(ROOM_DESCRIPTION, ROOM_CODE, ROOM_BIGROOM);
		List<Booking> allBookings = new ArrayList<Booking>();
		String error = null;
		
		try {
			Booking booking = service.createBooking(student, tutor, room, course, DURATION, SESSION_DATE, null);
			allBookings.add(booking);
		} catch(IllegalArgumentException e) { error = e.getMessage();}
				
		assertEquals(0, allBookings.size());
		assertEquals("Time cannot be empty!", error);
	}
	@Test
	public void testCreateBookingWrongDurationMock() {
				
		Tutor tutor = service.createTutor(TUTOR_NAME, TUTOR_EMAIL, TUTOR_PASSWORD);
		Student student = service.createStudent(STUDENT_NAME, STUDENT_EMAIL, STUDENT_PASSWORD);
		Course course = service.createCourse(COURSE_NAME, COURSE_CODE, COURSE_INSTITUTION, COURSE_FACULTY, COURSE_LEVEL);
		Room room = service.createRoom(ROOM_DESCRIPTION, ROOM_CODE, ROOM_BIGROOM);
		List<Booking> allBookings = new ArrayList<Booking>();
		String error = null;
		
		try {
			Booking booking = service.createBooking(student, tutor, room, course, WRONG_DURATION, SESSION_DATE, SESSION_TIME);
			allBookings.add(booking);
		} catch(IllegalArgumentException e) { error = e.getMessage();}
				
		assertEquals(0, allBookings.size());
		assertEquals("Duration must be above 0!", error);
	}
	
	@Test
	public void testCreateBookingWrongDateMock() {
		
		Tutor tutor = service.createTutor(TUTOR_NAME, TUTOR_EMAIL, TUTOR_PASSWORD);
		Student student = service.createStudent(STUDENT_NAME, STUDENT_EMAIL, STUDENT_PASSWORD);
		Course course = service.createCourse(COURSE_NAME, COURSE_CODE, COURSE_INSTITUTION, COURSE_FACULTY, COURSE_LEVEL);
		Room room = service.createRoom(ROOM_DESCRIPTION, ROOM_CODE, ROOM_BIGROOM);
		
		List<Booking> allBookings = new ArrayList<Booking>();
		String error = null;
		
		try {
			Booking booking = service.createBooking(student, tutor, room, course, DURATION, WRONG_SESSION_DATE, SESSION_TIME);
			allBookings.add(booking);
		} catch(IllegalArgumentException e) { error = e.getMessage();}
		
		assertEquals(0, allBookings.size());
		assertEquals("Date is not available", error);
	}
	
	@Test
	public void testCreateBookingWrongTimeMock() {
		
		Tutor tutor = service.createTutor(TUTOR_NAME, TUTOR_EMAIL, TUTOR_PASSWORD);
		Student student = service.createStudent(STUDENT_NAME, STUDENT_EMAIL, STUDENT_PASSWORD);
		Course course = service.createCourse(COURSE_NAME, COURSE_CODE, COURSE_INSTITUTION, COURSE_FACULTY, COURSE_LEVEL);
		Room room = service.createRoom(ROOM_DESCRIPTION, ROOM_CODE, ROOM_BIGROOM);
		List<Booking> allBookings = new ArrayList<Booking>();
		String error = null;
		
		try {
			Booking booking = service.createBooking(student, tutor, room, course, DURATION, SESSION_DATE, WRONG_SESSION_TIME);
			allBookings.add(booking);
		} catch(IllegalArgumentException e) { error = e.getMessage();}
		
		assertEquals(0, allBookings.size());
		assertEquals("Time is not available.", error);
	
	}
		
// Review Tests
	@Test
	public void createReviewTestMock()
	{
		Tutor tutor = service.createTutor(TUTOR_NAME, TUTOR_EMAIL, TUTOR_PASSWORD);
		Student student = service.createStudent(STUDENT_NAME, STUDENT_EMAIL, STUDENT_PASSWORD);
		Course course = service.createCourse(COURSE_NAME, COURSE_CODE, COURSE_INSTITUTION, COURSE_FACULTY, COURSE_LEVEL);
		Room room = service.createRoom(ROOM_DESCRIPTION, ROOM_CODE, ROOM_BIGROOM);
		Booking booking = service.createBooking(student, tutor, room, course, DURATION, SESSION_DATE, SESSION_TIME);
		
		Set <Review> reviews = new HashSet <Review> ();
		try {
			Review review = service.createReview(booking, COMMENT, RATE);
			reviews.add(review);
			booking.setReview(reviews);
		} catch(IllegalArgumentException e) {fail();}
		
		assertEquals(1, reviews.size());
	}
	
	@Test
	public void getStudentReview() {
		
		Set <Review> reviews = new HashSet<Review>();
		
		Student student = service.createStudent(STUDENT_NAME, STUDENT_EMAIL, STUDENT_PASSWORD);
		Tutor tutor = service.createTutor(TUTOR_NAME, TUTOR_EMAIL, TUTOR_PASSWORD);
		Room room = service.createRoom(ROOM_DESCRIPTION, ROOM_CODE, ROOM_BIGROOM);
		Course course = service.createCourse(COURSE_NAME, COURSE_CODE, COURSE_INSTITUTION, COURSE_FACULTY, COURSE_LEVEL);
		Booking booking = service.createBooking(student, tutor, room, course, DURATION, SESSION_DATE, SESSION_TIME);
		Review r = service.createReview(booking, COMMENT, RATE);
		reviews.add(r);
		
		try {
			
			service.getReview(booking);
			
		}catch(IllegalArgumentException e) { fail();}
		
		assertEquals(1, reviews.size());
	}
	
	@Test
	 public void getTutorReview() {
			
			Set <Review> reviews = new HashSet<Review>();
			
			Student student = service.createStudent(STUDENT_NAME, STUDENT_EMAIL, STUDENT_PASSWORD);
			Tutor tutor = service.createTutor(TUTOR_NAME, TUTOR_EMAIL, TUTOR_PASSWORD);
			Room room = service.createRoom(ROOM_DESCRIPTION, ROOM_CODE, ROOM_BIGROOM);
			Course course = service.createCourse(COURSE_NAME, COURSE_CODE, COURSE_INSTITUTION, COURSE_FACULTY, COURSE_LEVEL);
			Booking booking = service.createBooking(student, tutor, room, course, DURATION, SESSION_DATE, SESSION_TIME);
			Review r = service.createReview(booking, COMMENT, RATE);
			reviews.add(r);
					
			try {
				
				service.getReview(booking);
				
			}catch(IllegalArgumentException e) { fail();}
			
			assertEquals(1, reviews.size());
		}

	@Test
	public void createReviewCommentNullTestMock(){
		
		Tutor tutor = service.createTutor(TUTOR_NAME, TUTOR_EMAIL, TUTOR_PASSWORD);
		Student student = service.createStudent(STUDENT_NAME, STUDENT_EMAIL, STUDENT_PASSWORD);
		Course course = service.createCourse(COURSE_NAME, COURSE_CODE, COURSE_INSTITUTION, COURSE_FACULTY, COURSE_LEVEL);
		Room room = service.createRoom(ROOM_DESCRIPTION, ROOM_CODE, ROOM_BIGROOM);
		Booking booking = service.createBooking(student, tutor, room, course, DURATION, SESSION_DATE, SESSION_TIME);
			
		String error = null;
		Set <Review> reviews = new HashSet <Review> ();
		try {
			Review review = service.createReview(booking, null, RATE);
			reviews.add(review);
			booking.setReview(reviews);
		} catch(IllegalArgumentException e) {error = e.getMessage();}
		
		assertEquals(0, reviews.size());
		assertEquals("Comment part cannot be empty!", error);
	}
}