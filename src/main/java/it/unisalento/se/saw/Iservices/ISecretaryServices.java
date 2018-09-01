package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.dto.SecretaryDTO;
import it.unisalento.se.saw.exceptions.SecretaryNotFoundException;

import java.util.Set;

public interface ISecretaryServices {

    public SecretaryDTO getById(int id) throws SecretaryNotFoundException;
    public SecretaryDTO save(SecretaryDTO secretaryDTO);
    public SecretaryDTO getByUid(String uid) throws SecretaryNotFoundException;
}
