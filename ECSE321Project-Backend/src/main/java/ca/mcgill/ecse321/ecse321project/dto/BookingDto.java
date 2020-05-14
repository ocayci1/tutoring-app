package ca.mcgill.ecse321.ecse321project.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import ca.mcgill.ecse321.ecse321project.model.Student;

public class BookingDto {
	
	private StudentDto student;
	private TutorDto tutor;
	private CourseDto course;
	private Tutor_ScheduleDto schedule;
	private RoomDto room;
	private int duration;
	private Date date;
	private Time startTime;
	private Integer id;
			
	public BookingDto() {
	}
	
	public BookingDto(StudentDto student,TutorDto tutor, CourseDto course, int duration, Date date, Time startTime) {
		this.student = student;
		this.tutor = tutor;
		this.schedule = schedule;
		this.course = course;
		this.duration = duration;
		this.date = date;
		this.startTime = startTime;
	}
	
	public StudentDto getStudent() {
		return student;
	}
	
	public TutorDto getTutor() {
		return tutor;
	}
	public CourseDto getCourse() {
		return course;
	}
	public RoomDto getRoom() {
		return room;
	}
	public Tutor_ScheduleDto getSchedule() {
		return schedule;
	}	
	public int getDuration() {
		return duration;
	}
	public Date getDate() {
		return date;
	}
	public Time getStartTime() {
		return startTime;
	}
	public Integer getId() {
		return id;
	}

	public void setStudent(StudentDto student) {
		this.student = student;
	}
	public void  setTutor(TutorDto tutor) {
		this.tutor = tutor;
	}
	public void setCourse(CourseDto course) {
		this.course = course;
	}
	public void setRoom(RoomDto room) {
		this.room = room;
	}
	public void setSchedule(Tutor_ScheduleDto schedule) {
		this.schedule = schedule;
	}	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}


}
