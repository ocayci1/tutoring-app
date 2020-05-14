package ca.mcgill.ecse321.ecse321project.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.ecse321project.model.Course;
import ca.mcgill.ecse321.ecse321project.model.Tutor;

import org.springframework.stereotype.Component;

@Component
public interface TutorRepository extends CrudRepository<Tutor, String>{
	
	Tutor findTutorByFullName(String fullName);
	
	Tutor findTutorByEmail(String email);
	
	List<Tutor> findTutorByCourse(Course course);
}