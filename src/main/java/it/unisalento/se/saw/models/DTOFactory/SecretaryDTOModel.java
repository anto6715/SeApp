package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.Secretary;
import it.unisalento.se.saw.dto.SecretaryDTO;

public class SecretaryDTOModel implements DTO<Secretary, SecretaryDTO> {
    @Override
    public SecretaryDTO create(Secretary secretary) {
        SecretaryDTO secretaryDTO = new SecretaryDTO();
        if (secretary != null) {
        secretaryDTO.setAge(secretary.getUser().getAge());
        secretaryDTO.setEmail(secretary.getUser().getEmail());
        secretaryDTO.setName(secretary.getUser().getName());
        secretaryDTO.setSurname(secretary.getUser().getSurname());
        secretaryDTO.setUserType(secretary.getUser().getUserType());
        secretaryDTO.setUid(secretary.getUser().getUid());
        secretaryDTO.setId(secretary.getId().getIdSecretary());
        secretaryDTO.setIdUser(secretary.getUser().getIdUser());}
        return secretaryDTO;
    }
}
