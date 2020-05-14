package ca.mcgill.ecse321.ecse321project.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.ecse321project.model.Course;
import org.springframework.stereotype.Component;

@Component
public interface CourseRepository extends CrudRepository<Course, String>{

	List<Course> findCourseByName(String name);
	
	List<Course> findCourseByCode(String code);
	
	List<Course> findCourseByInstitution(String institution);

}