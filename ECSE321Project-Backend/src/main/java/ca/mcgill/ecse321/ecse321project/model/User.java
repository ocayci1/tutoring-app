package ca.mcgill.ecse321.ecse321project.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class User{
   private String fullName;
   
   public void setFullName(String value) {
      this.fullName = value;
   }
   
   public String getFullName() {
      return this.fullName;
   }
   
   private String email;

public void setEmail(String value) {
    this.email = value;
}
@Id
public String getEmail() {
    return this.email;
}
   private String password;
   
   public void setPassword(String value) {
      this.password = value;
   }
   
   public String getPassword() {
      return this.password;
   }
   
   }
