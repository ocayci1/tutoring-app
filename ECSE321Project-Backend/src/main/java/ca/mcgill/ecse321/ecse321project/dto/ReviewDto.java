package ca.mcgill.ecse321.ecse321project.dto;

public class ReviewDto {
	private BookingDto booking;
	private StudentDto student;
	private TutorDto tutor;
	private String comment;
	private int rate;
	
	public ReviewDto() {
	}
	
	public ReviewDto(BookingDto booking, StudentDto student, TutorDto tutor, String comment, int rate) {
		this.booking = booking;
		this.student = student;
		this.tutor = tutor;
		this.comment = comment;
		this.rate = rate;
	}
	
	public BookingDto getBooking() {
		return booking;
	}
	public StudentDto getStudent() {
		return student;
	}
	public TutorDto getTutorDto() {
		return tutor;
	}
	public String getComment() {
		return comment;
	}
	public int getRate() {
		return rate;
	}
}
