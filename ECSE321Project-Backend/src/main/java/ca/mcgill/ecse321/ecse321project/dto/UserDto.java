package ca.mcgill.ecse321.ecse321project.dto;

public class UserDto {
	private String fullName;
	private String email;
	private String password;
	
public UserDto() {
}

public UserDto(String fullName, String email , String password){
	this.fullName = fullName;
	this.email = email;
	this.password = password;
}

public String getFullName() {
	return fullName;
}
public String getEmail() {
	return email;
}
public String getPassword() {
	return password;
}

public void setFullName(String fullName) {
	this.fullName = fullName;
}
public void setEmail(String email) {
	this.email = email;
}
public void setPassword(String password) {
	this.password =  password;
}
}
