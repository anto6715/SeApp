package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.ISecretaryServices;
import it.unisalento.se.saw.Iservices.IUserServices;
import it.unisalento.se.saw.domain.Secretary;
import it.unisalento.se.saw.domain.SecretaryId;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.SecretaryDTO;
import it.unisalento.se.saw.exceptions.SecretaryNotFoundException;
import it.unisalento.se.saw.repositories.SecretaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SecretaryService implements ISecretaryServices {
    @Autowired
    SecretaryRepository secretaryRepository;

    @Autowired
    IUserServices userServices;

    @Transactional(readOnly = true)
    public List<Secretary> getAll() {
        return secretaryRepository.findAll();
    }

    @Override
    public Secretary getById(int id) throws SecretaryNotFoundException {
        try {
            Secretary secretary = secretaryRepository.findSecretaryById_IdSecretary(id);
            return secretary;
        } catch (Exception e) {
            throw new SecretaryNotFoundException();
        }
    }

    @Override
    public Secretary save(SecretaryDTO secretaryDTO) {

        User user = new User();
        user.setName(secretaryDTO.getName());
        user.setSurname(secretaryDTO.getSurname());
        user.setAge(secretaryDTO.getAge());
        user.setEmail(secretaryDTO.getEmail());
        user.setPassword(secretaryDTO.getPassword());
        user.setUid(secretaryDTO.getUid());
        user.setUserType(2);
        User saveUser = userServices.save(user);

        SecretaryId secretaryId = new SecretaryId();
        secretaryId.setUserIdUser(user.getIdUser());

        Secretary secretary = new Secretary();
        secretary.setUser(user);
        secretary.setId(secretaryId);
        return secretaryRepository.save(secretary);
    }

    @Override
    public void removeById(int id) throws SecretaryNotFoundException {
        try{
            Secretary secretary = secretaryRepository.findSecretaryById_IdSecretary(id);
            secretaryRepository.delete(secretary);
            userServices.removeUserById(secretary.getId().getUserIdUser());
        } catch (Exception e) {
            throw new SecretaryNotFoundException();
        }

    }
}
