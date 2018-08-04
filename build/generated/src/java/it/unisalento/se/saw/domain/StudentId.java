package it.unisalento.se.saw.domain;
// Generated 3-ago-2018 17.30.57 by Hibernate Tools 5.2.0.Final


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * StudentId generated by hbm2java
 */
@Embeddable
public class StudentId  implements java.io.Serializable {


     private int idStudent;
     private int courseIdCourse;
     private int userIdUser;

    public StudentId() {
    }

    public StudentId(int idStudent, int courseIdCourse, int userIdUser) {
       this.idStudent = idStudent;
       this.courseIdCourse = courseIdCourse;
       this.userIdUser = userIdUser;
    }
   


    @Column(name="idStudent", nullable=false)
    public int getIdStudent() {
        return this.idStudent;
    }
    
    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }


    @Column(name="Course_idCourse", nullable=false)
    public int getCourseIdCourse() {
        return this.courseIdCourse;
    }
    
    public void setCourseIdCourse(int courseIdCourse) {
        this.courseIdCourse = courseIdCourse;
    }


    @Column(name="User_idUser", nullable=false)
    public int getUserIdUser() {
        return this.userIdUser;
    }
    
    public void setUserIdUser(int userIdUser) {
        this.userIdUser = userIdUser;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof StudentId) ) return false;
		 StudentId castOther = ( StudentId ) other; 
         
		 return (this.getIdStudent()==castOther.getIdStudent())
 && (this.getCourseIdCourse()==castOther.getCourseIdCourse())
 && (this.getUserIdUser()==castOther.getUserIdUser());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdStudent();
         result = 37 * result + this.getCourseIdCourse();
         result = 37 * result + this.getUserIdUser();
         return result;
   }   


}


