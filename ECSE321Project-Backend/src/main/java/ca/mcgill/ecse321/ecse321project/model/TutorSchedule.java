package ca.mcgill.ecse321.ecse321project.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import java.sql.Date;
import java.sql.Time;

@Entity
public class TutorSchedule{
   private String hour;
   
   public void setHour(String value) {
      this.hour = value;
   }
   
   public String getHour() {
      return this.hour;
   }

   private Integer scheduleId;

public void setScheduleId(Integer value) {
    this.scheduleId = value;
}
@Id
public Integer getScheduleId() {
    return this.scheduleId;
}
private Date date;

public void setDate(Date value) {
    this.date = value;
}
public Date getDate() {
    return this.date;
}
private Tutor tutor;

@OneToOne
public Tutor getTutor() {
   return this.tutor;
}

public void setTutor(Tutor tutor) {
   this.tutor = tutor;
}

private Time time;

public void setTime(Time value) {
    this.time = value;
}
public Time getTime() {
    return this.time;
}

}
