package ca.mcgill.ecse321.ecse321project.model;

import javax.persistence.Entity;

import java.util.List;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
public class Course{
   private String name;
   
   public void setName(String value) {
      this.name = value;
   }
   
   public String getName() {
      return this.name;
   }
   
   private String code;
   
   public void setCode(String value) {
      this.code = value;
   }
   
   public String getCode() {
      return this.code;
   }
   
   private String institution;
   
   public void setInstitution(String value) {
      this.institution = value;
   }
   
   public String getInstitution() {
      return this.institution;
   }
   
   private String faculty;
   
   public void setFaculty(String value) {
      this.faculty = value;
   }
   
   public String getFaculty() {
      return this.faculty;
   }
   
   private String level;
   
   public void setLevel(String value) {
      this.level = value;
   }
   
   public String getLevel() {
      return this.level;
   }
   
   private List<Tutor> tutor;
   
   @ManyToMany
   public List<Tutor> getTutor() {
      return this.tutor;
   }
   
   public void setTutor(List<Tutor> tutors) {
      this.tutor = tutors;
   }

   private Integer courseId;

public void setCourseId(Integer value) {
    this.courseId = value;
}
@Id
public Integer getCourseId() {
    return this.courseId;
}
   private Set<Booking> booking;
   
   @OneToMany(mappedBy="course" , cascade={CascadeType.ALL})
   public Set<Booking> getBooking() {
      return this.booking;
   }
   
   public void setBooking(Set<Booking> bookings) {
      this.booking = bookings;
   }   
}
