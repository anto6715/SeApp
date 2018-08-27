package it.unisalento.se.saw.models.DTOFactory;

public interface DTO<T, D> {

    public D create(T o);
}
