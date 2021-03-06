package it.unisalento.se.saw.domain;
// Generated 8-ago-2018 18.06.28 by Hibernate Tools 5.2.0.Final


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name="review"
    ,catalog="mydb"
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Review  implements java.io.Serializable {


     private ReviewId id;
     private Lesson lesson;
     private Material material;
     private ReviewType reviewType;
     private Student student;
     private String note;
     private Integer rate;

    public Review() {
    }

	
    public Review(ReviewId id, ReviewType reviewType, Student student) {
        this.id = id;
        this.reviewType = reviewType;
        this.student = student;
    }
    public Review(ReviewId id, Lesson lesson, Material material, ReviewType reviewType, Student student, String note, Integer rate) {
       this.id = id;
       this.lesson = lesson;
       this.material = material;
       this.reviewType = reviewType;
       this.student = student;
       this.note = note;
       this.rate = rate;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="idReview", column=@Column(name="idReview", nullable=false) ), 
        @AttributeOverride(name="studentIdStudent", column=@Column(name="Student_idStudent", nullable=false) ), 
        @AttributeOverride(name="studentCourseIdCourse", column=@Column(name="Student_Course_idCourse", nullable=false) ), 
        @AttributeOverride(name="studentUserIdUser", column=@Column(name="Student_User_idUser", nullable=false) ), 
        @AttributeOverride(name="reviewTypeIdReviewType", column=@Column(name="ReviewType_idReviewType", nullable=false) ) } )
    public ReviewId getId() {
        return this.id;
    }
    
    public void setId(ReviewId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( { 
        @JoinColumn(name="Lesson_idLesson", referencedColumnName="idLesson"), 
        @JoinColumn(name="Lesson_Teaching_idTeaching", referencedColumnName="Teaching_idTeaching"), 
        @JoinColumn(name="Lesson_Teaching_Course_idCourse", referencedColumnName="Teaching_Course_idCourse"), 
        @JoinColumn(name="Lesson_Teaching_Professor_idProfessor", referencedColumnName="Teaching_Professor_idProfessor"), 
        @JoinColumn(name="Lesson_Teaching_Professor_User_idUser", referencedColumnName="Teaching_Professor_User_idUser"), 
        @JoinColumn(name="Lesson_Room_idRoom", referencedColumnName="Room_idRoom") } )
    public Lesson getLesson() {
        return this.lesson;
    }
    
    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( { 
        @JoinColumn(name="material_idMaterial", referencedColumnName="idMaterial"), 
        @JoinColumn(name="material_lesson_idLesson", referencedColumnName="Lesson_idLesson"), 
        @JoinColumn(name="material_lesson_Teaching_idTeaching", referencedColumnName="Lesson_Teaching_idTeaching"), 
        @JoinColumn(name="material_lesson_Teaching_Course_idCourse", referencedColumnName="Lesson_Teaching_Course_idCourse"), 
        @JoinColumn(name="material_lesson_Teaching_Professor_idProfessor", referencedColumnName="Lesson_Teaching_Professor_idProfessor"), 
        @JoinColumn(name="material_lesson_Teaching_Professor_User_idUser", referencedColumnName="Lesson_Teaching_Professor_User_idUser"), 
        @JoinColumn(name="material_lesson_Room_idRoom", referencedColumnName="Lesson_Room_idRoom") } )
    public Material getMaterial() {
        return this.material;
    }
    
    public void setMaterial(Material material) {
        this.material = material;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ReviewType_idReviewType", nullable=false, insertable=false, updatable=false)
    public ReviewType getReviewType() {
        return this.reviewType;
    }
    
    public void setReviewType(ReviewType reviewtype) {
        this.reviewType = reviewtype;
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

    
    @Column(name="note", length=500)
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }

    
    @Column(name="rate")
    public Integer getRate() {
        return this.rate;
    }
    
    public void setRate(Integer rate) {
        this.rate = rate;
    }




}


