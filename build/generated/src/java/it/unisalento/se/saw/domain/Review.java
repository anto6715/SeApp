package it.unisalento.se.saw.domain;
// Generated 3-ago-2018 14.36.53 by Hibernate Tools 5.2.0.Final


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Review generated by hbm2java
 */
@Entity
@Table(name="Review"
    ,catalog="mydb"
)
public class Review  implements java.io.Serializable {


     private ReviewId id;
     private Student student;
     private Teaching teaching;
     private String rate;

    public Review() {
    }

	
    public Review(ReviewId id, Student student, Teaching teaching) {
        this.id = id;
        this.student = student;
        this.teaching = teaching;
    }
    public Review(ReviewId id, Student student, Teaching teaching, String rate) {
       this.id = id;
       this.student = student;
       this.teaching = teaching;
       this.rate = rate;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="idReview", column=@Column(name="idReview", nullable=false) ), 
        @AttributeOverride(name="studentIdStudent", column=@Column(name="Student_idStudent", nullable=false) ), 
        @AttributeOverride(name="studentCourseIdCourse", column=@Column(name="Student_Course_idCourse", nullable=false) ), 
        @AttributeOverride(name="studentUserIdUser", column=@Column(name="Student_User_idUser", nullable=false) ), 
        @AttributeOverride(name="teachingIdTeaching", column=@Column(name="Teaching_idTeaching", nullable=false) ), 
        @AttributeOverride(name="teachingCourseIdCourse", column=@Column(name="Teaching_Course_idCourse", nullable=false) ), 
        @AttributeOverride(name="teachingProfessorIdProfessor", column=@Column(name="Teaching_Professor_idProfessor", nullable=false) ), 
        @AttributeOverride(name="teachingProfessorUserIdUser", column=@Column(name="Teaching_Professor_User_idUser", nullable=false) ) } )
    public ReviewId getId() {
        return this.id;
    }
    
    public void setId(ReviewId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( { 
        @JoinColumn(name="Student_idStudent", referencedColumnName="idStudent", nullable=false, insertable=false, updatable=false), 
        @JoinColumn(name="Student_Course_idCourse", referencedColumnName="Course_idCourse", nullable=false, insertable=false, updatable=false), 
        @JoinColumn(name="Student_User_idUser", referencedColumnName="User_idUser", nullable=false, insertable=false, updatable=false) } )
    public Student getStudent() {
        return this.student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( { 
        @JoinColumn(name="Teaching_idTeaching", referencedColumnName="idTeaching", nullable=false, insertable=false, updatable=false), 
        @JoinColumn(name="Teaching_Course_idCourse", referencedColumnName="Course_idCourse", nullable=false, insertable=false, updatable=false), 
        @JoinColumn(name="Teaching_Professor_idProfessor", referencedColumnName="Professor_idProfessor", nullable=false, insertable=false, updatable=false), 
        @JoinColumn(name="Teaching_Professor_User_idUser", referencedColumnName="Professor_User_idUser", nullable=false, insertable=false, updatable=false) } )
    public Teaching getTeaching() {
        return this.teaching;
    }
    
    public void setTeaching(Teaching teaching) {
        this.teaching = teaching;
    }

    
    @Column(name="rate", length=45)
    public String getRate() {
        return this.rate;
    }
    
    public void setRate(String rate) {
        this.rate = rate;
    }




}


