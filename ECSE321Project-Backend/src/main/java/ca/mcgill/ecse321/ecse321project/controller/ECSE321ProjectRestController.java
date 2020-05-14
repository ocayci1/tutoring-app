package ca.mcgill.ecse321.ecse321project.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.ecse321project.dto.*;
import ca.mcgill.ecse321.ecse321project.service.*;
import ca.mcgill.ecse321.ecse321project.model.*;

@CrossOrigin(origins = "*")
@RestController
public class ECSE321ProjectRestController {
	@Autowired
	ECSE321ProjectService service;
	StudentDto currentUser = null;
	CourseDto currentCourse = null;
	
	@PostMapping(value = {"/tutor/create"})
	public TutorDto createTutor(@RequestBody JSONConverter json) {
		Tutor tutor = service.createTutor(json.getName(), json.getEmail(), json.getPassword());
		tutor.setWage(20);;
		TutorDto tutorD = this.convertToDto(tutor);
		tutorD.setFullName(json.getName());
		tutorD.setEmail(json.getEmail());
		return tutorD;
	}
	
	@PostMapping(value = {"/course/create"})
	public CourseDto createCourse(@RequestBody JSONConverter json) {
		Course course = service.createCourse(json.getCourseName(), json.getCourseCode(), json.getSchool(), json.getFaculty(), json.getLevel());
		List<Tutor> tD = service.getAllTutors();
		System.out.println("td size " + tD.size());
		course.setTutor(tD);
		CourseDto courseD = this.convertToDto(course);
		return courseD;
	}
	
	//Login
	@PostMapping (value = {"/student"})
	public StudentDto login(@RequestBody JSONConverter json) {
			List<Student> students = service.getAllStudents();
			for(Student student: students) {
				String studentEmail = student.getEmail();
				String studentPassword = student.getPassword();
				if(json.getEmail().equals(studentEmail) && json.getPassword().equals(studentPassword)) {
					currentUser = this.convertToDto(student);
					return currentUser;
				}
			}
			return null;
		}	
	
	@PostMapping (value = {"/getstudent"})
	public StudentDto signin(@RequestParam(name="email") String email, @RequestParam(name="password") String password)throws IllegalArgumentException{
		List<Student> students = service.getAllStudents();
		for(Student student: students) {
			String studentEmail = student.getEmail();
			String studentPassword = student.getPassword();
			if(email.equals(studentEmail) && password.equals(studentPassword)) {
				currentUser = this.convertToDto(student);
				return currentUser;
			}
		}
		return null;
								
	}
	
//Sign up, Create Student

	@PostMapping (value = {"/student/create/"})
	public StudentDto createStudent (@RequestBody JSONConverter json)throws IllegalArgumentException {
		
		Student student = service.createStudent(json.getName(), json.getEmail(), json.getPassword());
		currentUser = convertToDto(student);
		return currentUser;
	}
	
	@GetMapping (value = {"/profile"})
	public StudentDto getCurrentUser() {
		return this.currentUser;
	}
	
	//Side Methods
	private StudentDto convertToDto (Student s) {
		if(s==null) {
			throw new IllegalArgumentException("There is no such Student!");
		}
			StudentDto studentDto = new StudentDto (s.getFullName(),s.getEmail(),s.getPassword(),s.getRating(),s.getSchool());
			return studentDto;
	}
	
//Course Search
	public List<CourseDto> getCourseByName(String name) throws IllegalArgumentException {
		List<CourseDto> courseDtos = new ArrayList<>();
		for(Course c: service.getCourseByName(name)) {
			courseDtos.add(convertToDto(c));
		}
		return courseDtos;
	}
	
	@GetMapping(value = "/courses")
	public List<CourseDto> getCourses() {
		List<CourseDto> courses = new ArrayList<CourseDto>();
		for(Course course: service.getAllCourses()) {
			courses.add(convertToDto(course));
		}
		return courses;
	}
	
	
	public List<CourseDto> getCourseByCode(String code) throws IllegalArgumentException {
		List<CourseDto> courseDtos = new ArrayList<>();
		for(Course c: service.getCourseByCode(code)) {
			courseDtos.add(convertToDto(c));
		}
		return courseDtos;
	}
	
	public Course getCoursenDByCode(String code) throws IllegalArgumentException {
		List<Course> courseDtos = service.getCourseByCode(code);
			
		return courseDtos.get(0);
	}
	public List<CourseDto> getCourseByInstitution(String institution) throws IllegalArgumentException {
		List<CourseDto> courseDtos = new ArrayList<>();
		for(Course c: service.getCourseByInstitution(institution)) {
			courseDtos.add(convertToDto(c));
		}
		return courseDtos;
	}
	
	@PostMapping(value = "/courses/search/")
	public List<CourseDto> searchCourses(@RequestBody JSONConverter json) throws IllegalArgumentException {
		String input = json.getName();
		List<CourseDto> searchResult = new ArrayList<CourseDto>();
		searchResult.addAll(this.getCourseByName(input));
		searchResult.addAll(this.getCourseByCode(input));
		searchResult.addAll(this.getCourseByInstitution(input));
		return searchResult;
	}
	
	//Side Methods
		private CourseDto convertToDto(Course c) {
		if(c==null) {
			throw new IllegalArgumentException("There is no such Course!");
		}
		List<Tutor> listoft = c.getTutor();
		Set<TutorDto> listofdto = new HashSet<TutorDto>();
		for (Tutor t: listoft) {
			listofdto.add(this.convertToDto(t));
		}
		System.out.println("tutor size " + listofdto.size());
		CourseDto cDto = new CourseDto(c.getName(),c.getCode(),c.getInstitution(),c.getFaculty(),c.getLevel(), listofdto);
		return cDto;
		}

//Request Course
	public CourseDto requestCourse(String name, String code, String institution, String faculty, String level){
		//TODO: will be processed later
		return null;
	}

//Get Tutor List of Course
	public List<TutorDto> getTutorsOfCourse(String course){
		List<TutorDto> tutorDtos = new ArrayList<>();
		for(Course c: service.getCourseByName(course)) {
			c.getTutor();
			for(Tutor t: service.getTutorbyCourse(c)) {
				tutorDtos.add(convertToDto(t));
			}
		}
		return tutorDtos;
		
		
	}
	
	//Side Methods
	private TutorDto convertToDto(Tutor tutor) {
		if (tutor == null) {
			throw new IllegalArgumentException("There is no such Tutor!");
		}
		TutorDto tutorDto = new TutorDto (tutor.getCourse(), tutor.getRating(),tutor.getSchool(),tutor.getWage());
		return tutorDto;
	}
	
	private Course convertToDomainObject(CourseDto cDto) {
		Iterable<Course> Courses = service.getAllCourses();
		for (Course c: Courses) {
			if(c.getName().equals(cDto.getName())) {
				if(c.getCode().equals(cDto.getCode())) {
					if(c.getInstitution().equals(cDto.getInstitution())) {
						return c;
					}
				}
			}
		}
			return null;
	}
	
	
	public TutorDto convertToDto_T (Tutor t) {
		if(t==null) {
			throw new IllegalArgumentException("There is no such Tutor!");
		}
			TutorDto tutorDto = new TutorDto ();
			return tutorDto;
	}
	
//Get Tutor Profile
	@GetMapping (value = "/tutors/{name}")
	public TutorDto getTutorProfile (@PathVariable("name") String name) throws IllegalArgumentException {
		return convertToDto(service.getTutorByName(name));
	}
	
		
//Get Tutor Schedule
	@GetMapping (value = {"tutors/{name}/schedule"})
	public Tutor_ScheduleDto getTutorScheduleOfTutor(@PathVariable("name")String  name) {
	    TutorDto tDto = getTutorProfile(name);
		Tutor t = convertToDomainObject(tDto, tDto.getFullName());
		return createTutorScheduleDtosForTutor(t);
	}
	
	// Side Methods
	private Tutor_ScheduleDto createTutorScheduleDtosForTutor(Tutor t) {
		TutorSchedule scheduleForTutor = service.getTutorSchedule(t);
		Tutor_ScheduleDto tsDto = convertToDto(scheduleForTutor, t);
		return tsDto;
	}

	private Tutor_ScheduleDto convertToDto(TutorSchedule scheduleForTutor, Tutor t) {
		if (scheduleForTutor == null) {
			throw new IllegalArgumentException("There is no such Schedule!");
		}
		TutorDto tDto = convertToDto(t);
		Tutor_ScheduleDto tsDto = new Tutor_ScheduleDto(tDto ,scheduleForTutor.getDate(), scheduleForTutor.getTime());
		return tsDto;
	}

	private Tutor convertToDomainObject(TutorDto tDto, String name) {
		Tutor tutor = service.getTutorByName(name);
		if(tutor.getEmail().equals(tDto.getEmail())) {
			return tutor;
		}
		return null;
	}
	
//Create Booking
	@PostMapping(value = { "/booking/create"})
public BookingDto createBooking(@RequestBody JSONConverter json) {
	Student student = service.getStudentByEmail(json.getEmail());
		Tutor tutor = service.getTutorByName(json.getName());
		List<Course> courses = service.getCourseByName(json.getCourseName());
		Course course = courses.get(0);
		Booking booking = service.createBooking(student, tutor,course,json.getDuration(),json.getDate(),Time.valueOf(json.getStartTime()));
		System.out.println(service.getAllBookings());
		return convertToDto(booking);	
	}
	
	//Side Methods
	public BookingDto convertToDto (Booking b) {
		if (b == null) {
			throw new IllegalArgumentException("There is no such Booking!");
		}
		TutorDto tDto = convertToDto(b.getTutor());
		StudentDto sDto = convertToDto(b.getStudent());
		CourseDto cDto = convertToDto(b.getCourse());
		
		BookingDto bookingDto = new BookingDto(sDto,tDto, cDto, b.getDuration(),b.getDate(), b.getStartTime());
		return bookingDto;
	}
	@PostMapping (value= {"/booking/review"})
	public ReviewDto createReview(
			@RequestParam  (name = "email") String email, 
			@RequestParam  (name = "name") String name, 
			@RequestParam  (name = "date") Date date, 
			@RequestParam (name = "comment") String comment,
			@RequestParam (name = "rate") int rate) {
		Student s = service.getStudentByEmail(email);
		Tutor t = service.getTutorByName(name);
		Booking booking = new Booking(); //current booking
		List <Booking> bStudent = service.getBookingByStudent(s);
		List <Booking> bTutor = service.getBookingByTutor(t);
		List <Booking> bDate = service.getBookingByDate(date);	
		for(Booking b1: bStudent) {
			for(Booking b2: bTutor) {
				for(Booking b3: bDate) {
					if( b1.equals(b2) && b2.equals(b3)) {
						booking = b1;
					}
				}
			}
		}
		
		double oldRate = t.getRating();
		double newRate = (oldRate + rate) /2;
		t.setRating(newRate);
		Review r = service.createReview(booking, comment, rate);
		return convertToDto(r, booking, s, t, comment, rate);
	}
		
	//Side Method
	private ReviewDto convertToDto(Review r, Booking b, Student s, Tutor t, String c, int rate) {
		BookingDto bDto = convertToDto(b);
		StudentDto sDto = convertToDto(s);
		TutorDto tDto = convertToDto(t);
		return new ReviewDto(bDto, sDto, tDto, c, rate);
	}
	
//View Booking
	@GetMapping(value= {"student/{email}/bookings"})
	public List<BookingDto> viewBooking(@PathVariable("email")String email) {
		Student s = service.getStudentByEmail(email);
		return createBookingDtosForStudent(s);
	}
	
	//Side Methods
	private List<BookingDto> createBookingDtosForStudent(Student s) {
		List<Booking> bookingsForStudent = service.getBookingByStudent(s);
		List <BookingDto> bookings = new ArrayList <>();
		for(Booking b: bookingsForStudent) {
			bookings.add(convertToDto(b));
		}
		return bookings;
	}

//Modify Booking
	@PutMapping(value = { "/booking/modify/"})
	public BookingDto modifyBooking(
			@RequestParam(name = "email") String email,
			@RequestParam(name = "oldName") String oldName,
			@RequestParam(name = "newName") String newName, 
			@RequestParam(name = "roomCode") String code, 
			@RequestParam(name ="courseName") String courseName, 
			@RequestParam (name = "duration")int duration, 
			@RequestParam Date oldDate,
			@RequestParam Date newDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime) throws IllegalArgumentException{
		
		Student student = service.getStudentByEmail(email);
		Tutor tutor = service.getTutorByName(oldName);
		List<Course> courses = service.getCourseByName(courseName);
		Course course = null;
		for (Course c : courses) {
			if(c.getTutor() == tutor) {
				 course = c;
			}
		}
		
		Booking booking = new Booking(); //current booking
		List <Booking> bStudent = service.getBookingByStudent(student);
		List <Booking> bTutor = service.getBookingByTutor(tutor);
		List <Booking> bDate = service.getBookingByDate(oldDate);	
		
		for(Booking b1: bStudent) {
			for(Booking b2: bTutor) {
				for(Booking b3: bDate) {
					if( b1.equals(b2) && b2.equals(b3)) {
						booking = b1;
					}
				}
			}
		}
		Tutor newTutor = service.getTutorByName(newName);
		Time time = Time.valueOf(startTime);
		if(booking.equals(null)) {
			throw new IllegalArgumentException ("There is no such booking!");
		}
		booking.setStudent(student);
		booking.setTutor(newTutor);
		booking.setCourse(course);
		booking.setDuration(duration);
		booking.setDate(newDate);
		booking.setStartTime(time);
		
		return convertToDto(booking);	
	}

//Cancel Booking
	@DeleteMapping(value = { "/booking/cancel/"})
	public void deleteBooking(
			@RequestParam (name = "email") String email, 
			@RequestParam (name = "name") String name, 
			@RequestParam Date date) throws Exception {
		Student s = service.getStudentByEmail(email);
		Tutor t = service.getTutorByName(name);
		List <Booking> bStudent = service.getBookingByStudent(s);
		List <Booking> bTutor = service.getBookingByTutor(t);
		List <Booking> bDate = service.getBookingByDate(date);
		Booking b = new Booking();
		if(bStudent.contains(b) && bTutor.contains(b) && bDate.contains(b)) {
			service.deleteBooking(b);
		}
	}

//Logout
	@PostMapping(value = {"/logout"})
	public void logout() {
		this.currentUser = null;
	}
	
}
