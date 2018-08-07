package it.unisalento.se.saw.models;

public interface DTO<T, D> {

    public D create(T o);
}
