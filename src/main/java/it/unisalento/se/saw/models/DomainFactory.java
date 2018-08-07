package it.unisalento.se.saw.models;

public class DomainFactory {

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
