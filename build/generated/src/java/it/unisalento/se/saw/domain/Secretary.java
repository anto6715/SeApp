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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Secretary generated by hbm2java
 */
@Entity
@Table(name="secretary"
    ,catalog="mydb"
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Secretary  implements java.io.Serializable {


     private SecretaryId id;
     private User user;

    public Secretary() {
    }

    public Secretary(SecretaryId id, User user) {
       this.id = id;
       this.user = user;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="idSecretary", column=@Column(name="idSecretary", nullable=false) ), 
        @AttributeOverride(name="userIdUser", column=@Column(name="User_idUser", nullable=false) ) } )
    public SecretaryId getId() {
        return this.id;
    }
    
    public void setId(SecretaryId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="User_idUser", nullable=false, insertable=false, updatable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }




}


