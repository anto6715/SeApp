package it.unisalento.se.saw.models;

public abstract class AbstractFactory {
    public abstract DTO getDTO(String dtoType);
    public abstract Domain getDomain(String domainType);
}
