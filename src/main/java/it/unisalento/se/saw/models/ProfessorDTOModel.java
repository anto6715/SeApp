package it.unisalento.se.saw.models;

import it.unisalento.se.saw.domain.Professor;
import it.unisalento.se.saw.dto.ProfessorDTO;

public class ProfessorDTOModel implements DTO<Professor, ProfessorDTO> {
    @Override
    public ProfessorDTO create(Professor professor) {
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setAge(professor.getUser().getAge());
        professorDTO.setEmail(professor.getUser().getEmail());
        professorDTO.setName(professor.getUser().getName());
        professorDTO.setSurname(professor.getUser().getSurname());
        professorDTO.setUid(professor.getUser().getUid());
        professorDTO.setId(professor.getId().getIdProfessor());
        professorDTO.setUserType(professor.getUser().getUserType());
        return professorDTO;
    }
}
