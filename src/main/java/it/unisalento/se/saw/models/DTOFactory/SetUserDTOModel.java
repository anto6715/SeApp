package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.UserDTO;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.FactoryProducer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetUserDTOModel implements DTO<List<User>, List<UserDTO>> {
    @Override
    public List<UserDTO> create(List<User> users) {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<User, UserDTO> dto = abstractFactory.getDTO("User");
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user: users) {
            userDTOS.add(dto.create(user));
        }
        return userDTOS;
    }
}
