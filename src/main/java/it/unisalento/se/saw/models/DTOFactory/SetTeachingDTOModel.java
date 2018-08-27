package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.dto.TeachingDTO;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.FactoryProducer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetTeachingDTOModel implements DTO<List<Teaching>, Set<TeachingDTO>> {
    @Override


    public Set<TeachingDTO> create(List<Teaching> teachings) {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<Teaching, TeachingDTO> dto = abstractFactory.getDTO("Teaching");
        Set<TeachingDTO> teachingDTOS = new HashSet<>(0);
        for (Teaching teaching: teachings) {
            teachingDTOS.add(dto.create(teaching));
        }
        return teachingDTOS;
    }
}
