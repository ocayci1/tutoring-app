package ca.mcgill.ecse321.ecse321project.service;

import java.time.LocalTime;
import java.sql.Date;

public class JSONConverter {
    private String name;
    private String email;
    private String password;
    private String roomCode;
    private int duration;
    private Date date;
    private LocalTime startTime;
    private String courseName;
    private String courseCode;
    private String faculty;
    private String school;
    private String level;
    
 public String getName() {
  return name;
 }
 public String getEmail() {
  return email;
 }

 public String getPassword() {
  return password;
 }
  public String getRoomCode() {
	  return roomCode;
  } 
  public int getDuration() {
	  return duration;
  }
  public Date getDate() {
	  return date;
  }
  public LocalTime getStartTime() {
	 return startTime;
  }
  public String getCourseName() {
	  return courseName;
  }
  public String getCourseCode() {
	  return courseCode;
  }
  public String getFaculty() {
	  return faculty;
  }
  public String getSchool() {
	  return school;
  }
  public String getLevel() {
	  return level;
  }
  
}