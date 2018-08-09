package it.unisalento.se.saw.domain;
// Generated 8-ago-2018 18.06.28 by Hibernate Tools 5.2.0.Final


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SecretaryId generated by hbm2java
 */
@Embeddable
public class SecretaryId  implements java.io.Serializable {


     private int idSecretary;
     private int userIdUser;

    public SecretaryId() {
    }

    public SecretaryId(int idSecretary, int userIdUser) {
       this.idSecretary = idSecretary;
       this.userIdUser = userIdUser;
    }
   


    @Column(name="idSecretary", nullable=false)
    public int getIdSecretary() {
        return this.idSecretary;
    }
    
    public void setIdSecretary(int idSecretary) {
        this.idSecretary = idSecretary;
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
		 if ( !(other instanceof SecretaryId) ) return false;
		 SecretaryId castOther = ( SecretaryId ) other; 
         
		 return (this.getIdSecretary()==castOther.getIdSecretary())
 && (this.getUserIdUser()==castOther.getUserIdUser());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdSecretary();
         result = 37 * result + this.getUserIdUser();
         return result;
   }   


}


