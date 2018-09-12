package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.Professor;
import it.unisalento.se.saw.dto.ProfessorDTO;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.FactoryProducer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetProfessorDTOModel implements DTO<List<Professor>, List<ProfessorDTO>> {
    @Override
    public List<ProfessorDTO> create(List<Professor> professors) {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<Professor, ProfessorDTO> dto = abstractFactory.getDTO("Professor");
        List<ProfessorDTO> professorDTOS = new ArrayList<>();
        for (Professor professor: professors) {
            professorDTOS.add(dto.create(professor));
        }
        return professorDTOS;
    }
}
