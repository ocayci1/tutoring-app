package ca.mcgill.ecse321.ecse321project.model;

import javax.persistence.Entity;
import ca.mcgill.ecse321.ecse321project.model.User;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
public class Student extends User{
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
   
   private Set<Booking> booking;
   
   @OneToMany(mappedBy="student" , cascade={CascadeType.ALL})
   public Set<Booking> getBooking() {
      return this.booking;
   }
   
   public void setBooking(Set<Booking> bookings) {
      this.booking = bookings;
   }
   
   }
