package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.ISecretaryServices;
import it.unisalento.se.saw.Iservices.IUserServices;
import it.unisalento.se.saw.domain.Secretary;
import it.unisalento.se.saw.domain.SecretaryId;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.SecretaryDTO;
import it.unisalento.se.saw.dto.UserDTO;
import it.unisalento.se.saw.exceptions.SecretaryNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.DomainFactory.Domain;
import it.unisalento.se.saw.models.FactoryProducer;
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

    @Transactional
    public Secretary getById(int id) throws SecretaryNotFoundException {
        try {
            Secretary secretary = secretaryRepository.findSecretaryById_IdSecretary(id);
            return secretary;
        } catch (Exception e) {
            throw new SecretaryNotFoundException();
        }
    }

    @Transactional
    public Secretary save(SecretaryDTO secretaryDTO) {
        AbstractFactory domainFactory = FactoryProducer.getFactory("DOMAIN");
        AbstractFactory dtoFactory = FactoryProducer.getFactory("DTO");

        Domain<SecretaryDTO,User> domainSecretaryUser = domainFactory.getDomain("USER");
        Domain<UserDTO,User> domainUserDTOUser = domainFactory.getDomain("USER");
        DTO<User, UserDTO> dto = dtoFactory.getDTO("USER");

        User user = domainSecretaryUser.create(secretaryDTO);
        User saveUser = domainUserDTOUser.create(userServices.save(dto.create(user)));

        SecretaryId secretaryId = new SecretaryId();
        secretaryId.setUserIdUser(user.getIdUser());

        Secretary secretary = new Secretary();
        secretary.setUser(user);
        secretary.setId(secretaryId);
        return secretaryRepository.save(secretary);
    }

    @Transactional
    public Secretary getByUid(String uid) throws SecretaryNotFoundException{
        try {
            return secretaryRepository.findSecretaryByUserUid(uid);
        } catch (Exception e){
            throw new SecretaryNotFoundException();
        }
    }
}
