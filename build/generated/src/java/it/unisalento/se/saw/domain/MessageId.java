package it.unisalento.se.saw.domain;
// Generated 29-lug-2018 10.15.06 by Hibernate Tools 5.2.0.Final


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MessageId generated by hbm2java
 */
@Embeddable
public class MessageId  implements java.io.Serializable {


     private int idMessage;
     private int userIdUser;
     private int teachingIdTeaching;
     private int teachingCourseIdCourse;
     private int teachingProfessorIdProfessor;
     private int teachingProfessorUserIdUser;

    public MessageId() {
    }

    public MessageId(int idMessage, int userIdUser, int teachingIdTeaching, int teachingCourseIdCourse, int teachingProfessorIdProfessor, int teachingProfessorUserIdUser) {
       this.idMessage = idMessage;
       this.userIdUser = userIdUser;
       this.teachingIdTeaching = teachingIdTeaching;
       this.teachingCourseIdCourse = teachingCourseIdCourse;
       this.teachingProfessorIdProfessor = teachingProfessorIdProfessor;
       this.teachingProfessorUserIdUser = teachingProfessorUserIdUser;
    }
   


    @Column(name="idMessage", nullable=false)
    public int getIdMessage() {
        return this.idMessage;
    }
    
    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }


    @Column(name="User_idUser", nullable=false)
    public int getUserIdUser() {
        return this.userIdUser;
    }
    
    public void setUserIdUser(int userIdUser) {
        this.userIdUser = userIdUser;
    }


    @Column(name="Teaching_idTeaching", nullable=false)
    public int getTeachingIdTeaching() {
        return this.teachingIdTeaching;
    }
    
    public void setTeachingIdTeaching(int teachingIdTeaching) {
        this.teachingIdTeaching = teachingIdTeaching;
    }


    @Column(name="Teaching_Course_idCourse", nullable=false)
    public int getTeachingCourseIdCourse() {
        return this.teachingCourseIdCourse;
    }
    
    public void setTeachingCourseIdCourse(int teachingCourseIdCourse) {
        this.teachingCourseIdCourse = teachingCourseIdCourse;
    }


    @Column(name="Teaching_Professor_idProfessor", nullable=false)
    public int getTeachingProfessorIdProfessor() {
        return this.teachingProfessorIdProfessor;
    }
    
    public void setTeachingProfessorIdProfessor(int teachingProfessorIdProfessor) {
        this.teachingProfessorIdProfessor = teachingProfessorIdProfessor;
    }


    @Column(name="Teaching_Professor_User_idUser", nullable=false)
    public int getTeachingProfessorUserIdUser() {
        return this.teachingProfessorUserIdUser;
    }
    
    public void setTeachingProfessorUserIdUser(int teachingProfessorUserIdUser) {
        this.teachingProfessorUserIdUser = teachingProfessorUserIdUser;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof MessageId) ) return false;
		 MessageId castOther = ( MessageId ) other; 
         
		 return (this.getIdMessage()==castOther.getIdMessage())
 && (this.getUserIdUser()==castOther.getUserIdUser())
 && (this.getTeachingIdTeaching()==castOther.getTeachingIdTeaching())
 && (this.getTeachingCourseIdCourse()==castOther.getTeachingCourseIdCourse())
 && (this.getTeachingProfessorIdProfessor()==castOther.getTeachingProfessorIdProfessor())
 && (this.getTeachingProfessorUserIdUser()==castOther.getTeachingProfessorUserIdUser());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdMessage();
         result = 37 * result + this.getUserIdUser();
         result = 37 * result + this.getTeachingIdTeaching();
         result = 37 * result + this.getTeachingCourseIdCourse();
         result = 37 * result + this.getTeachingProfessorIdProfessor();
         result = 37 * result + this.getTeachingProfessorUserIdUser();
         return result;
   }   


}


