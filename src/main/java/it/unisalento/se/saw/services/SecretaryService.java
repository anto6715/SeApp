package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.ISecretaryServices;
import it.unisalento.se.saw.domain.Secretary;
import it.unisalento.se.saw.dto.SecretaryDTO;
import it.unisalento.se.saw.exceptions.SecretaryNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.DomainFactory.Domain;
import it.unisalento.se.saw.models.FactoryProducer;
import it.unisalento.se.saw.repositories.SecretaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class SecretaryService implements ISecretaryServices {
    @Autowired
    SecretaryRepository secretaryRepository;

    AbstractFactory domainFactory = FactoryProducer.getFactory("DOMAIN");
    AbstractFactory dtoFactory = FactoryProducer.getFactory("DTO");


    @Transactional
    public SecretaryDTO getById(int id) throws SecretaryNotFoundException {
        DTO<Secretary, SecretaryDTO> dto = dtoFactory.getDTO("Secretary");
        return dto.create(secretaryRepository.findSecretaryById_IdSecretary(id));

    }

    @Transactional
    public SecretaryDTO save(SecretaryDTO secretaryDTO) {
        Domain<SecretaryDTO, Secretary> domain = domainFactory.getDomain("Secretary");
        DTO<Secretary, SecretaryDTO> dto = dtoFactory.getDTO("Secretary");
        return dto.create(secretaryRepository.save(domain.create(secretaryDTO)));
    }

    @Transactional
    public SecretaryDTO getByUid(String uid) throws SecretaryNotFoundException{

            DTO<Secretary, SecretaryDTO> dto = dtoFactory.getDTO("Secretary");
            return dto.create(secretaryRepository.findSecretaryByUserUid(uid));

    }
}
