package ca.mcgill.ecse321.ecse321project.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Booking{
   private boolean approval;
   
   public void setApproval(boolean value) {
      this.approval = value;
   }
   
   public boolean isApproval() {
      return this.approval;
   }
   
   private boolean roomAssigned;
   
   public void setRoomAssigned(boolean value) {
      this.roomAssigned = value;
   }
   
   public boolean isRoomAssigned() {
      return this.roomAssigned;
   }
   
   private boolean groupRequest;
   
   public void setGroupRequest(boolean value) {
      this.groupRequest = value;
   }
   
   public boolean isGroupRequest() {
      return this.groupRequest;
   }
   
   private int duration;
   
   public void setDuration(int value) {
      this.duration = value;
   }
   
   public int getDuration() {
      return this.duration;
   }

   private Integer bookingId;

public void setBookingId(Integer value) {
    this.bookingId = value;
}
@Id
public Integer getBookingId() {
    return this.bookingId;
}
private Date date;

public void setDate(Date value) {
    this.date = value;
}
public Date getDate() {
    return this.date;
}
private Time startTime;

public void setStartTime(Time value) {
    this.startTime = value;
}
public Time getStartTime() {
    return this.startTime;
}
   private Set<Review> review;
   
   @OneToMany(mappedBy="booking" , cascade={CascadeType.ALL})
   public Set<Review> getReview() {
      return this.review;
   }
   
   public void setReview(Set<Review> reviews) {
      this.review = reviews;
   }
   
   private Course course;
   
   @ManyToOne(optional=false)
   public Course getCourse() {
      return this.course;
   }
   
   public void setCourse(Course course) {
      this.course = course;
   }
   
   private Student student;
   
   @ManyToOne(optional=false)
   public Student getStudent() {
      return this.student;
   }
   
   public void setStudent(Student student) {
      this.student = student;
   }
   
   private Tutor tutor;
   
   @ManyToOne(optional=false)
   public Tutor getTutor() {
      return this.tutor;
   }
   
   public void setTutor(Tutor tutor) {
      this.tutor = tutor;
   }
   
      
   }
