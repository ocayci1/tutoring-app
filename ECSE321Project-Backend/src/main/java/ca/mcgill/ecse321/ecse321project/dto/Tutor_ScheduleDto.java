package ca.mcgill.ecse321.ecse321project.dto;

import java.sql.Date;
import java.sql.Time;

public class Tutor_ScheduleDto {
	private TutorDto tutor;
	private Date date;
	private Time time;
	
	public Tutor_ScheduleDto() {
	}
	
	public Tutor_ScheduleDto(TutorDto tutor, Date date, Time time) {
		this.date = date;
		this.time = time;
		this.tutor = tutor;
	}
	
	public TutorDto getTutor() {
		return tutor;
	}
	public Date getDate() {
		return date;
	}
	public Time getTime() {
		return time;
	}
	public void setDate (Date date) {
		this.date = date;
	}
	public void setTime (Time time) {
		this.time = time;
	}
	public void setTutor (TutorDto tutor) {
		this.tutor = tutor;
	}

}