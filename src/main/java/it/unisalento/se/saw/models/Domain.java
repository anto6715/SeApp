package it.unisalento.se.saw.models;

public interface Domain<T, D> {

    public D create(T o);
}
