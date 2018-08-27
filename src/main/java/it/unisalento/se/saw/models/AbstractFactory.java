package it.unisalento.se.saw.models;

import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.DomainFactory.Domain;

public abstract class AbstractFactory {
    public abstract DTO getDTO(String dtoType);
    public abstract Domain getDomain(String domainType);
}
