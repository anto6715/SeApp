package it.unisalento.se.saw.models.DomainFactory;

import it.unisalento.se.saw.Iservices.IUserServices;
import it.unisalento.se.saw.domain.Secretary;
import it.unisalento.se.saw.domain.SecretaryId;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.SecretaryDTO;
import it.unisalento.se.saw.dto.UserDTO;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.FactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;

public class SecretaryDomainModel implements Domain<SecretaryDTO, Secretary> {
    @Autowired
    IUserServices userServices;

    @Override
    public Secretary create(SecretaryDTO secretaryDTO) {
        AbstractFactory domainFactory = FactoryProducer.getFactory("DOMAIN");
        AbstractFactory dtoFactory = FactoryProducer.getFactory("DTO");

        Domain<SecretaryDTO, User> domainSecretaryUser = domainFactory.getDomain("USER");
        Domain<UserDTO,User> domainUserDTOUser = domainFactory.getDomain("USER");
        DTO<User, UserDTO> dto = dtoFactory.getDTO("USER");

        User user = domainSecretaryUser.create(secretaryDTO);
        User saveUser = domainUserDTOUser.create(userServices.save(dto.create(user)));

        SecretaryId secretaryId = new SecretaryId();
        secretaryId.setUserIdUser(user.getIdUser());
        secretaryId.setIdSecretary(secretaryDTO.getId());

        Secretary secretary = new Secretary();
        secretary.setUser(user);
        secretary.setId(secretaryId);
        return secretary;
    }
}
