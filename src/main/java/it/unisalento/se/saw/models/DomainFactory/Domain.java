package it.unisalento.se.saw.models.DomainFactory;

public interface Domain<T, D> {

    public D create(T o);
}
