package it.unisalento.se.saw.domain;
// Generated 4-ago-2018 10.43.02 by Hibernate Tools 5.2.0.Final


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
 * SegnalationState generated by hbm2java
 */
@Entity
@Table(name="SegnalationState"
    ,catalog="mydb"
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SegnalationState  implements java.io.Serializable {


     private Integer idSegnalationState;
     private String state;
     @JsonBackReference
     private Set<Segnalation> segnalations = new HashSet<Segnalation>(0);

    public SegnalationState() {
    }

    public SegnalationState(String state, Set<Segnalation> segnalations) {
       this.state = state;
       this.segnalations = segnalations;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idSegnalationState", unique=true, nullable=false)
    public Integer getIdSegnalationState() {
        return this.idSegnalationState;
    }
    
    public void setIdSegnalationState(Integer idSegnalationState) {
        this.idSegnalationState = idSegnalationState;
    }

    
    @Column(name="state", length=45)
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="segnalationState")
    public Set<Segnalation> getSegnalations() {
        return this.segnalations;
    }
    
    public void setSegnalations(Set<Segnalation> segnalations) {
        this.segnalations = segnalations;
    }




}


