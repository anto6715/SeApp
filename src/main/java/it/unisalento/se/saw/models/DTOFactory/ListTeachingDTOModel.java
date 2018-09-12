package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.dto.TeachingDTO;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.FactoryProducer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListTeachingDTOModel implements DTO<List<Teaching>, List<TeachingDTO>> {
    @Override


    public List<TeachingDTO> create(List<Teaching> teachings) {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<Teaching, TeachingDTO> dto = abstractFactory.getDTO("Teaching");
        List<TeachingDTO> teachingDTOS = new ArrayList<>();
        for (Teaching teaching: teachings) {
            teachingDTOS.add(dto.create(teaching));
        }
        return teachingDTOS;
    }
}
