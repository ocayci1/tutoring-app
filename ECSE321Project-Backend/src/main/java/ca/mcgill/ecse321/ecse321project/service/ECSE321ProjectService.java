package ca.mcgill.ecse321.ecse321project.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.ecse321project.model.*;
import ca.mcgill.ecse321.ecse321project.dao.*;

@Service
public class ECSE321ProjectService {

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	TutorRepository tutorRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	TutorScheduleRepository tutorScheduleRepository;
	@Autowired
	ReviewRepository reviewRepository;
	@Autowired
	BookingRepository bookingRepository; 
	
	private Integer courseID = 0;
	private Integer bookingID = 0;
	private Integer tutorSID = 0;
	private Integer reviewID = 0;
	@Transactional
	public Student createStudent(String name, String email, String password) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Name cannot be empty!");
		}
		if (email == null || email.trim().length() == 0) {
			throw new IllegalArgumentException("Email cannot be empty!");
		}
		if (password == null || password.trim().length() == 0) {
			throw new IllegalArgumentException("Password cannot be empty!");
		}
		if (email == null || email.trim().length() == 0) {
			throw new IllegalArgumentException("Person email cannot be empty!");
		}
		if (password == null || password.trim().length() == 0) {
			throw new IllegalArgumentException("Person password cannot be empty!");
		}
		if (email == null || email.trim().length() == 0) {
			throw new IllegalArgumentException("Person email cannot be empty!");
		}
		if (password == null || password.trim().length() == 0) {
			throw new IllegalArgumentException("Person password cannot be empty!");
		}
		Student student = new Student();
		student.setFullName(name);
		student.setEmail(email);
		student.setPassword(password);
		studentRepository.save(student);
		return student;
	}
	
	@Transactional
	public Student getStudent(String name) {
		Student student = studentRepository.findStudentByFullName(name);
		
		return student;
	}
	
	@Transactional
	public Student getStudentByEmail (String email) {
		Student student = studentRepository.findStudentByEmail(email);
		return student;
	}
	
	@Transactional
	public List<Student> getAllStudents() {
		List<Student> students = (List<Student>) studentRepository.findAll();
		return students;
	}
	
	@Transactional
	public Tutor createTutor(String name, String email, String password) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Name cannot be empty!");
		}
		if (email == null || email.trim().length() == 0) {
			throw new IllegalArgumentException("Email cannot be empty!");
		}
		if (password == null || password.trim().length() == 0) {
			throw new IllegalArgumentException("Password cannot be empty!");
		}
		Tutor tutor = new Tutor();
		tutor.setFullName(name);
		tutor.setEmail(email);
		tutor.setPassword(password);
		tutorRepository.save(tutor);
		return tutor;
	}
	
	@Transactional
	public Tutor getTutorByName(String name) {
		Tutor tutor = tutorRepository.findTutorByFullName(name);
		if(name == null || name ==" ") {
			throw new IllegalArgumentException("Tutor name cannot be empty!");
		}
		return tutor;
	}
	
	@Transactional
	public List<Tutor> getTutorbyCourse (Course course){
		if (course == null ) {
	        throw new IllegalArgumentException("Course cannot be null!");
	    }
		List <Tutor> tutorbycourse = tutorRepository.findTutorByCourse(course);
		return tutorbycourse;
	}
	
	@Transactional
	public List<Tutor> getAllTutors() {
		List<Tutor> tutors =(List<Tutor>) tutorRepository.findAll();
		return tutors;
	}
	
	@Transactional
	public Course createCourse(String name, String code, String institution, String faculty, String level) {
		Course course = new Course();
		course.setCourseId(courseID);
		courseID++;
		course.setName(name);
		course.setCode(code);
		course.setInstitution(institution);
		course.setFaculty(faculty);
		course.setLevel(level);
		course.setTutor(this.getAllTutors());
		courseRepository.save(course);
		return course;
	}
	
	@Transactional
	public List<Course> getAllCourses(){
		List<Course> courses = (List<Course>) courseRepository.findAll();
		return courses;
	}
	
	@Transactional
	public List<Course> getCourseByName(String name) {
		List<Course> courses = courseRepository.findCourseByName(name);
		if (name == null || name == "") {
		    throw new IllegalArgumentException("Course name cannot be empty!");
		  }
		return courses;
	}
	
	@Transactional
	public List<Course> getCourseByCode(String code) {
		List<Course> courses = courseRepository.findCourseByCode(code);
		if (code == null || code == "") {
		    throw new IllegalArgumentException("Course code cannot be empty!");
		  }
		return courses;
	}
	
	@Transactional
	public List <Course> getCourseByInstitution(String institution) {
		List <Course> courses = courseRepository.findCourseByInstitution(institution);
		if (institution == null || institution == "") {
		    throw new IllegalArgumentException("Course institution cannot be empty!");
		  }
		return courses;
	}
	
	@Transactional
	public TutorSchedule createTutorSchedule(Date date, Tutor tutor) {
		TutorSchedule tutorSchedule = new TutorSchedule();
		tutorSchedule.setScheduleId(tutorSID);
		tutorSID++;
		tutorSchedule.setDate(date);
		tutorSchedule.setTutor(tutor);
		tutorScheduleRepository.save(tutorSchedule);
		return tutorSchedule;
	}
	
	@Transactional
	public TutorSchedule getTutorSchedule(Tutor tutor) {
		TutorSchedule tutorSchedules = tutorScheduleRepository.findTutorScheduleByTutor(tutor);
		return tutorSchedules;
	}
	
	@Transactional
	public List<TutorSchedule> getAllTutorSchedules() {
		List<TutorSchedule> tutorSchedules = (List<TutorSchedule>) tutorScheduleRepository.findAll();
		return tutorSchedules;
	}

	@Transactional
	public Review createReview(Booking b, String comment, int rate) {
		if(comment == null) {
			throw new IllegalArgumentException("Comment part cannot be empty!");
		}
		Review review = new Review();
		review.setReviewId(reviewID);
		reviewID++;
		review.setBooking(b);
		review.setComment(comment);
		review.setRate(rate);
		reviewRepository.save(review);
		return review;
	}
	
	@Transactional
	public List<List<Review>> getReview(Booking b) {
		List<Review> reviews = reviewRepository.findReviewByBooking(b);
		List<Review> rs = new ArrayList<Review>();
		List<List<Review>> all = new ArrayList<List<Review>>();
		for (Review r: reviews) {
				rs.add(r);
		}
		all.add(rs);
		return all;
	}
	
	@Transactional
	public List<Review> getAllReviews() {
		List<Review> reviews = (List<Review>) reviewRepository.findAll();
		return reviews;
	}
		
	@Transactional
	public Booking createBooking(Student student, Tutor tutor, Course course, int duration, Date date, Time startTime) {
	if(course  == null ) {
			throw new IllegalArgumentException ("Course cannot be empty!");
		}
		if(tutor == null) {
			throw new IllegalArgumentException("Tutor cannot be empty!");
		}
		if(student == null) {
			throw new IllegalArgumentException("Student cannot be empty!");
		}
		if(duration <= 0) {
			throw new IllegalArgumentException("Duration must be above 0!");
		}
		if(date == null) {
			throw new IllegalArgumentException("Date cannot be empty!");
		}
		if(startTime == null) {
			throw new IllegalArgumentException("Time cannot be empty!");
		}
		Date d = new Date(Calendar.getInstance().getTimeInMillis());
		if(date.before(d)) {
			throw new IllegalArgumentException("Date is not available");
		}
				
		LocalTime lc = LocalTime.now();
		
		LocalTime t = startTime.toLocalTime();
		
		Booking booking = new Booking();
		booking.setBookingId(bookingID);
		bookingID++;
		//Admin admin = new Admin();
		//admin.setEmail("admin@yalcin.com");
		booking.setStudent(student);
		//booking.setAdmin(admin);
		booking.setTutor(tutor);
		booking.setCourse(course);
		booking.setDuration(duration);
		booking.setDate(date);
		booking.setStartTime(startTime);
		bookingRepository.save(booking);
		return booking;
	}
	
	
	@Transactional
	public List<Booking> getBookingByStudent(Student student) {
		List<Booking> bookings = bookingRepository.findByStudent(student);
		return bookings;
	}
	
	@Transactional
	public List<Booking> getBookingByTutor(Tutor tutor) {
		List<Booking> bookings =  bookingRepository.findByTutor(tutor);
		return bookings;
	}
	
	@Transactional
	public List<Booking> getBookingByCourse(Course course) {
		List<Booking> bookings = bookingRepository.findByCourse(course);
		return bookings;
	}
	
	@Transactional
	public List<Booking> getBookingByDate(Date date) {
		List<Booking> bookings = bookingRepository.findByDate(date);
		return bookings;
	}
	
	@Transactional
	public Optional<Booking> getBookingById(Integer id) {
		Optional<Booking> booking = bookingRepository.findById(id);
		return booking;
	}
	
	@Transactional

	public List<Booking> getAllBookings() {
		List<Booking> bookings = (List<Booking>) bookingRepository.findAll();
		return bookings;
	}
		
	@Transactional
	public void deleteBooking(Booking booking) {
		bookingRepository.delete(booking);
		
	}
		
}