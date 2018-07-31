package it.unisalento.se.saw.domain;
// Generated 31-lug-2018 0.50.47 by Hibernate Tools 5.2.0.Final


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ExamId generated by hbm2java
 */
@Embeddable
public class ExamId  implements java.io.Serializable {


     private int idExam;
     private int roomIdRoom;
     private int teachingIdTeaching;
     private int teachingCourseIdCourse;
     private int teachingProfessorIdProfessor;
     private int teachingProfessorUserIdUser;

    public ExamId() {
    }

    public ExamId(int idExam, int roomIdRoom, int teachingIdTeaching, int teachingCourseIdCourse, int teachingProfessorIdProfessor, int teachingProfessorUserIdUser) {
       this.idExam = idExam;
       this.roomIdRoom = roomIdRoom;
       this.teachingIdTeaching = teachingIdTeaching;
       this.teachingCourseIdCourse = teachingCourseIdCourse;
       this.teachingProfessorIdProfessor = teachingProfessorIdProfessor;
       this.teachingProfessorUserIdUser = teachingProfessorUserIdUser;
    }
   


    @Column(name="idExam", nullable=false)
    public int getIdExam() {
        return this.idExam;
    }
    
    public void setIdExam(int idExam) {
        this.idExam = idExam;
    }


    @Column(name="Room_idRoom", nullable=false)
    public int getRoomIdRoom() {
        return this.roomIdRoom;
    }
    
    public void setRoomIdRoom(int roomIdRoom) {
        this.roomIdRoom = roomIdRoom;
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
		 if ( !(other instanceof ExamId) ) return false;
		 ExamId castOther = ( ExamId ) other; 
         
		 return (this.getIdExam()==castOther.getIdExam())
 && (this.getRoomIdRoom()==castOther.getRoomIdRoom())
 && (this.getTeachingIdTeaching()==castOther.getTeachingIdTeaching())
 && (this.getTeachingCourseIdCourse()==castOther.getTeachingCourseIdCourse())
 && (this.getTeachingProfessorIdProfessor()==castOther.getTeachingProfessorIdProfessor())
 && (this.getTeachingProfessorUserIdUser()==castOther.getTeachingProfessorUserIdUser());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdExam();
         result = 37 * result + this.getRoomIdRoom();
         result = 37 * result + this.getTeachingIdTeaching();
         result = 37 * result + this.getTeachingCourseIdCourse();
         result = 37 * result + this.getTeachingProfessorIdProfessor();
         result = 37 * result + this.getTeachingProfessorUserIdUser();
         return result;
   }   


}


