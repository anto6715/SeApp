package it.unisalento.se.saw.models;

import it.unisalento.se.saw.models.DTOFactory.DtoFactory;
import it.unisalento.se.saw.models.DomainFactory.DomainFactory;

import java.util.HashMap;

public class FactoryProducer {

    private static final HashMap factoryMap = new HashMap();

    public static AbstractFactory getFactory(String choice) {
        AbstractFactory abstractFactory = (AbstractFactory) factoryMap.get(choice);

        if (abstractFactory == null) {
            if(choice.equalsIgnoreCase("DTO")) {
                AbstractFactory dtoFactory = new DtoFactory();
                factoryMap.put(choice, dtoFactory);
                return new DtoFactory();
            }

            if(choice.equalsIgnoreCase("DOMAIN")) {
                AbstractFactory domainFactory = new DomainFactory();
                factoryMap.put(choice, domainFactory);
                return domainFactory;
            }
        }

        return abstractFactory;
    }
}
