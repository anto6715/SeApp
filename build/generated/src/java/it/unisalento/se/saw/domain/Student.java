package it.unisalento.se.saw.domain;
// Generated 29-lug-2018 10.15.06 by Hibernate Tools 5.2.0.Final


import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Student generated by hbm2java
 */
@Entity
@Table(name="Student"
    ,catalog="mydb"
)
public class Student  implements java.io.Serializable {


     private StudentId id;
     private Course course;
     private User user;
     private String matricola;
     private String year;
     private String yearStart;
     private Set<Review> reviews = new HashSet<Review>(0);

    public Student() {
    }

	
    public Student(StudentId id, Course course, User user) {
        this.id = id;
        this.course = course;
        this.user = user;
    }
    public Student(StudentId id, Course course, User user, String matricola, String year, String yearStart, Set<Review> reviews) {
       this.id = id;
       this.course = course;
       this.user = user;
       this.matricola = matricola;
       this.year = year;
       this.yearStart = yearStart;
       this.reviews = reviews;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="idStudent", column=@Column(name="idStudent", nullable=false) ), 
        @AttributeOverride(name="userIdUser", column=@Column(name="User_idUser", nullable=false) ), 
        @AttributeOverride(name="courseIdCourse", column=@Column(name="Course_idCourse", nullable=false) ) } )
    public StudentId getId() {
        return this.id;
    }
    
    public void setId(StudentId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Course_idCourse", nullable=false, insertable=false, updatable=false)
    public Course getCourse() {
        return this.course;
    }
    
    public void setCourse(Course course) {
        this.course = course;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="User_idUser", nullable=false, insertable=false, updatable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    
    @Column(name="matricola", length=45)
    public String getMatricola() {
        return this.matricola;
    }
    
    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    
    @Column(name="year", length=45)
    public String getYear() {
        return this.year;
    }
    
    public void setYear(String year) {
        this.year = year;
    }

    
    @Column(name="yearStart", length=45)
    public String getYearStart() {
        return this.yearStart;
    }
    
    public void setYearStart(String yearStart) {
        this.yearStart = yearStart;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="student")
    public Set<Review> getReviews() {
        return this.reviews;
    }
    
    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }




}


