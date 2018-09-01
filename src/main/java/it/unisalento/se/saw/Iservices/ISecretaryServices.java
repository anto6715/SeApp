package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.Secretary;
import it.unisalento.se.saw.dto.SecretaryDTO;
import it.unisalento.se.saw.exceptions.SecretaryNotFoundException;

import java.util.List;

public interface ISecretaryServices {

    public List<Secretary> getAll();
    public Secretary getById(int id) throws SecretaryNotFoundException;
    public Secretary save(SecretaryDTO secretaryDTO);
    public Secretary getByUid(String uid) throws SecretaryNotFoundException;
}
