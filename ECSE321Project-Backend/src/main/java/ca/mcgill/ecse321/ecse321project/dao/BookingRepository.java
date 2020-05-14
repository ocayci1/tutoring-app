package ca.mcgill.ecse321.ecse321project.dao;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.ecse321project.model.Booking;
import ca.mcgill.ecse321.ecse321project.model.Course;
import ca.mcgill.ecse321.ecse321project.model.Student;
import ca.mcgill.ecse321.ecse321project.model.Tutor;
import org.springframework.stereotype.Component;

@Component
public interface BookingRepository extends CrudRepository<Booking, Integer> {

	List<Booking> findByStudent(Student studentName);
	
	List<Booking> findByTutor(Tutor tutorName);
	
	List<Booking> findByCourse(Course course);
	
	List<Booking> findByDate(Date date);
	
	Optional<Booking> findById (Integer id);

	boolean existsByStudentAndCourse(Student student, Course courseName);

	Booking findByStudentAndCourse(Student student, Course courseName);

	boolean existsByTutorAndCourse(Tutor tutor, Course courseName);

	Booking findByTutorAndCourse(Tutor tutor, Course courseName);
}