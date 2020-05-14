package ca.mcgill.ecse321.ecse321project.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.ecse321project.model.Booking;
import ca.mcgill.ecse321.ecse321project.model.Review;

import org.springframework.stereotype.Component;

@Component
public interface ReviewRepository extends CrudRepository<Review, String>{

	List<Review> findReviewByBooking(Booking booking);

}