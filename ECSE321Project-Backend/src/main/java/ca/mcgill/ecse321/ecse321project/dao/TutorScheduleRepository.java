package ca.mcgill.ecse321.ecse321project.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.ecse321project.model.Tutor;
import ca.mcgill.ecse321.ecse321project.model.TutorSchedule;

import org.springframework.stereotype.Component;

@Component
public interface TutorScheduleRepository extends CrudRepository<TutorSchedule, String>{
	
	TutorSchedule findTutorScheduleByTutor(Tutor tutor);
}