package ca.mcgill.ecse321.ecse321project.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CourseDto {
	private String name;
	private String code;
	private String institution;
	private String faculty;
	private String level;
	private Integer id;
	private Set<TutorDto> tutor = new HashSet<TutorDto>();

public CourseDto() {
}

public CourseDto(String name, String code, String institution, String faculty, String level, Set<TutorDto> tutors) {
	this.name = name;
	this.code = code;
	this.institution = institution;
	this.faculty = faculty;
	this.level = level;
	this.setTutor(tutors);
}
	
public String getName() {
	return name;
}
public String getCode() {
	return code;
}
public String getInstitution() {
	return institution;
}
public String getFaculty() {
	return faculty;
}
public String getLevel() {
	return level;
}
public Integer getId() {
	return id;
}

public Set<TutorDto> getTutor() {
	return tutor;
}

public void setTutor(Set<TutorDto> tutor) {
	this.tutor = tutor;
}

}
