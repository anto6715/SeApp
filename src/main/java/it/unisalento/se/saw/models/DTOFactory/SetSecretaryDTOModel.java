package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.Secretary;
import it.unisalento.se.saw.dto.SecretaryDTO;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.FactoryProducer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetSecretaryDTOModel implements DTO<List<Secretary>, Set<SecretaryDTO>> {
    @Override
    public Set<SecretaryDTO> create(List<Secretary> secretaries) {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<Secretary, SecretaryDTO> dto = abstractFactory.getDTO("Secretary");
        Set<SecretaryDTO> secretaryDTOS = new HashSet<>(0);
        for (Secretary secretary: secretaries) {
            secretaryDTOS.add(dto.create(secretary));
        }
        return secretaryDTOS;
    }
}
