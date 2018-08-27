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
        return null;
    }


}