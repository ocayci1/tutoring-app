package ca.mcgill.ecse321.ecse321project.dto;

import java.util.List;
import java.util.Set;

import ca.mcgill.ecse321.ecse321project.model.Course;

public class TutorDto extends UserDto {
	private CourseDto course;
	private Set<Course> courses;
	private double rating;
	private String school;
	private double wage;
	private String name; 
	private String email;
	
	public TutorDto() {
	}
	
	public TutorDto (Set<Course> course, double rating, String school, double wage) {
		this.rating=rating;
		this.school = school;
		this.wage = wage;
	}
	
	public CourseDto getCourse() {
		return course;
	}
	public Set<Course> getCourses(){
		return courses;
	}
	public double getRating() {
		return rating;
	}
	public String getSchool() {
		return school;
	}
	public double getWage() {
		return wage;
	}
	public void setCourse(CourseDto course) {
		this.course = course;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public void setWage (double wage) {
		this.wage = wage;
	}
	public void setFullName (String name) {
		this.name = name;
	}
	public String getFullName() {
		return name;
	}
	public void setEmail (String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	
} 
