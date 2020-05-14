package ca.mcgill.ecse321.ecse321project.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review{
   private String comment;
   
   public void setComment(String value) {
      this.comment = value;
   }
   
   public String getComment() {
      return this.comment;
   }

   private Integer reviewId;

public void setReviewId(Integer value) {
    this.reviewId = value;
}
@Id
public Integer getReviewId() {
    return this.reviewId;
}
   private Booking booking;
   
   @ManyToOne(optional=false)
   public Booking getBooking() {
      return this.booking;
   }
   
   public void setBooking(Booking booking) {
      this.booking = booking;
   }
   
   private int rate;

   public void setRate(int value) {
       this.rate = value;
   }
   public int getRate() {
       return this.rate;
   }
      
   }
