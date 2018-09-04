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
import it.unisalento.se.saw.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class SecretaryService implements ISecretaryServices {
    @Autowired
    SecretaryRepository secretaryRepository;

    @Autowired
    UserRepository userRepository;

    AbstractFactory domainFactory = FactoryProducer.getFactory("DOMAIN");
    AbstractFactory dtoFactory = FactoryProducer.getFactory("DTO");


    @Transactional
    public SecretaryDTO getById(int id) throws SecretaryNotFoundException {
        DTO<Secretary, SecretaryDTO> dto = dtoFactory.getDTO("Secretary");
        return dto.create(secretaryRepository.findSecretaryById_IdSecretary(id));

    }

    @Transactional
    public SecretaryDTO save(SecretaryDTO secretaryDTO) {
        DTO<Secretary, SecretaryDTO> dtoSecretary = dtoFactory.getDTO("Secretary");
        Domain<SecretaryDTO, User> domainSecretaryUser = domainFactory.getDomain("USER");

        User saveUser = userRepository.save(domainSecretaryUser.create(secretaryDTO));
        SecretaryId secretaryId = new SecretaryId();
        secretaryId.setUserIdUser(saveUser.getIdUser());
        secretaryId.setIdSecretary(secretaryDTO.getId());

        Secretary secretary = new Secretary();
        secretary.setUser(saveUser);
        secretary.setId(secretaryId);
        return dtoSecretary.create(secretaryRepository.save(secretary));
    }

    @Transactional
    public SecretaryDTO getByUid(String uid) throws SecretaryNotFoundException{

            DTO<Secretary, SecretaryDTO> dto = dtoFactory.getDTO("Secretary");
            return dto.create(secretaryRepository.findSecretaryByUserUid(uid));

    }
}
