package it.unisalento.se.saw.models.DomainFactory;

import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;

public class DomainFactory extends AbstractFactory {

    @Override
    public DTO getDTO(String dtoType) {
        return null;
    }

    public Domain getDomain(String domainType){
        if(domainType == null){
            return null;
        }
        if(domainType.equalsIgnoreCase("USER")){
            return new UserDomainModel();
        }

        if(domainType.equalsIgnoreCase("TEACHING")){
            return new TeachingDomainModel();
        }
        if(domainType.equalsIgnoreCase("COURSE")){
            return new CourseDomainModel();
        }

        if(domainType.equalsIgnoreCase("STUDENT")){
            return new StudentDomainModel();
        }

        if(domainType.equalsIgnoreCase("SEGNALATIONSTATE")){
            return new SegnalationStateDomainModel();
        }
        if(domainType.equalsIgnoreCase("SEGNALATION")){
            return new SegnalationDomainModel();
        }
        if(domainType.equalsIgnoreCase("SECRETARY")){
            return new SecretaryDomainModel();
        }
        if(domainType.equalsIgnoreCase("PROFESSOR")){
            return new ProfessorDomainModel();
        }
        return null;
    }


}
