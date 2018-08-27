package it.unisalento.se.saw.models;

import it.unisalento.se.saw.models.DTOFactory.DtoFactory;
import it.unisalento.se.saw.models.DomainFactory.DomainFactory;

public class FactoryProducer {
    public static AbstractFactory getFactory(String choice) {
        if(choice.equalsIgnoreCase("DTO")) {
            return new DtoFactory();
        }

        if(choice.equalsIgnoreCase("DOMAIN")) {
            return new DomainFactory();
        }
        return null;
    }
}
