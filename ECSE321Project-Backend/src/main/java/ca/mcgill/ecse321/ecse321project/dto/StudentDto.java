package ca.mcgill.ecse321.ecse321project.dto;

public class StudentDto extends UserDto{
	private double rating;
	private String school;
	
	public StudentDto() {
	}
	
	public StudentDto (String name, String email, String password, double rating, String school) {
		super.setFullName(name);
		super.setEmail(email);
		super.setPassword(password);
		this.rating=rating;
		this.school = school;
	}
	
	public double getRating() {
		return rating;
	}
	public String getSchool() {
		return school;
	}
	
	public void setRating(double rating) {
		this.rating = rating;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	
}
