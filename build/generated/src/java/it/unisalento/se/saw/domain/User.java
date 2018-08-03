package it.unisalento.se.saw.domain;
// Generated 3-ago-2018 14.36.53 by Hibernate Tools 5.2.0.Final


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name="User"
    ,catalog="mydb"
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User  implements java.io.Serializable {


     private Integer idUser;
     private String name;
     private String surname;
     private String email;
     private String password;
     private Integer age;
     private Integer uid;
     private Integer userType;
     @JsonBackReference
     private Set<Professor> professors = new HashSet<Professor>(0);
     @JsonBackReference
     private Set<Secretary> secretaries = new HashSet<Secretary>(0);
     @JsonBackReference
     private Set<Student> students = new HashSet<Student>(0);

    public User() {
    }

    public User(String name, String surname, String email, String password, Integer age, Integer uid, Integer userType, Set<Professor> professors, Set<Secretary> secretaries, Set<Student> students) {
       this.name = name;
       this.surname = surname;
       this.email = email;
       this.password = password;
       this.age = age;
       this.uid = uid;
       this.userType = userType;
       this.professors = professors;
       this.secretaries = secretaries;
       this.students = students;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idUser", unique=true, nullable=false)
    public Integer getIdUser() {
        return this.idUser;
    }
    
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    
    @Column(name="name", length=45)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="surname", length=45)
    public String getSurname() {
        return this.surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }

    
    @Column(name="email", length=45)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="password", length=45)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Column(name="age")
    public Integer getAge() {
        return this.age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }

    
    @Column(name="Uid")
    public Integer getUid() {
        return this.uid;
    }
    
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    
    @Column(name="userType")
    public Integer getUserType() {
        return this.userType;
    }
    
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<Professor> getProfessors() {
        return this.professors;
    }
    
    public void setProfessors(Set<Professor> professors) {
        this.professors = professors;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<Secretary> getSecretaries() {
        return this.secretaries;
    }
    
    public void setSecretaries(Set<Secretary> secretaries) {
        this.secretaries = secretaries;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<Student> getStudents() {
        return this.students;
    }
    
    public void setStudents(Set<Student> students) {
        this.students = students;
    }




}


