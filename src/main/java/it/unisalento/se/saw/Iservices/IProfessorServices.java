package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.Professor;
import it.unisalento.se.saw.dto.ProfessorDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;

import java.util.List;
import java.util.Set;

public interface IProfessorServices {

    public List<ProfessorDTO> getAll();
    public ProfessorDTO save(ProfessorDTO professorDTO) throws CourseNotFoundException;
    public ProfessorDTO getById(int id) throws ProfessorNotFoundException;
    public Professor getDomainById(int id) throws ProfessorNotFoundException;
    public ProfessorDTO getByUid(String uid) throws ProfessorNotFoundException;
    public List<ProfessorDTO> getByIdCourse(int id) throws ProfessorNotFoundException;
}
