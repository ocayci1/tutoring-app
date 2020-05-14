package ca.mcgill.ecse321.ecse321project.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.ecse321project.model.Student;

import org.springframework.stereotype.Component;

@Component
public interface StudentRepository extends CrudRepository<Student, String>{
	
	Student findStudentByFullName(String fullName);
	
	Student findStudentByEmail(String email);
	
}