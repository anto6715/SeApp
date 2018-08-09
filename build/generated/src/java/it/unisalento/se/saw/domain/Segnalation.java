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
 * Segnalation generated by hbm2java
 */
@Entity
@Table(name="segnalation"
    ,catalog="mydb"
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Segnalation  implements java.io.Serializable {


     private SegnalationId id;
     private Professor professor;
     private Room room;
     private SegnalationState segnalationState;
     private String note;

    public Segnalation() {
    }

	
    public Segnalation(SegnalationId id, Professor professor, Room room, SegnalationState segnalationstate) {
        this.id = id;
        this.professor = professor;
        this.room = room;
        this.segnalationState = segnalationState;
    }
    public Segnalation(SegnalationId id, Professor professor, Room room, SegnalationState segnalationState, String note) {
       this.id = id;
       this.professor = professor;
       this.room = room;
       this.segnalationState = segnalationState;
       this.note = note;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="idSegnalation", column=@Column(name="idSegnalation", nullable=false) ), 
        @AttributeOverride(name="roomIdRoom", column=@Column(name="Room_idRoom", nullable=false) ), 
        @AttributeOverride(name="professorIdProfessor", column=@Column(name="Professor_idProfessor", nullable=false) ), 
        @AttributeOverride(name="professorUserIdUser", column=@Column(name="Professor_User_idUser", nullable=false) ), 
        @AttributeOverride(name="segnalationStateIdSegnalationState", column=@Column(name="SegnalationState_idSegnalationState", nullable=false) ) } )
    public SegnalationId getId() {
        return this.id;
    }
    
    public void setId(SegnalationId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( { 
        @JoinColumn(name="Professor_idProfessor", referencedColumnName="idProfessor", nullable=false, insertable=false, updatable=false), 
        @JoinColumn(name="Professor_User_idUser", referencedColumnName="User_idUser", nullable=false, insertable=false, updatable=false) } )
    public Professor getProfessor() {
        return this.professor;
    }
    
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Room_idRoom", nullable=false, insertable=false, updatable=false)
    public Room getRoom() {
        return this.room;
    }
    
    public void setRoom(Room room) {
        this.room = room;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SegnalationState_idSegnalationState", nullable=false, insertable=false, updatable=false)
    public SegnalationState getSegnalationState() {
        return this.segnalationState;
    }
    
    public void setSegnalationState(SegnalationState segnalationstate) {
        this.segnalationState = segnalationstate;
    }

    
    @Column(name="note", length=500)
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }




}


