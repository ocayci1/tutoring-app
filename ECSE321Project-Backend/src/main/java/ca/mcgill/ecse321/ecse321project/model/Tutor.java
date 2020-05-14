package ca.mcgill.ecse321.ecse321project.model;

import javax.persistence.Entity;
import ca.mcgill.ecse321.ecse321project.model.User;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;

@Entity
public class Tutor extends User{
   private double rating;
   
   public void setRating(double value) {
      this.rating = value;
   }
   
   public double getRating() {
      return this.rating;
   }
   
   private String school;
   
   public void setSchool(String value) {
      this.school = value;
   }
   
   public String getSchool() {
      return this.school;
   }
   
   private double wage;
   
   public void setWage(double value) {
      this.wage = value;
   }
   
   public double getWage() {
      return this.wage;
   }
   
   private Set<Course> course;
   
   @ManyToMany(mappedBy="tutor" , cascade={CascadeType.ALL})
   public Set<Course> getCourse() {
      return this.course;
   }
   
   public void setCourse(Set<Course> courses) {
      this.course = courses;
   }
   
   private TutorSchedule tutorSchedule;
   
   @OneToOne(mappedBy="tutor" , cascade={CascadeType.ALL})
   public TutorSchedule getTutorSchedule() {
      return this.tutorSchedule;
   }
   
   public void setTutorSchedule(TutorSchedule tutorSchedule) {
      this.tutorSchedule = tutorSchedule;
   }
   
   private Set<Booking> booking;
   
   @OneToMany(mappedBy="tutor" , cascade={CascadeType.ALL})
   public Set<Booking> getBooking() {
      return this.booking;
   }
   
   public void setBooking(Set<Booking> bookings) {
      this.booking = bookings;
   }
   
   }
