package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.Professor;
import it.unisalento.se.saw.domain.ProfessorId;
import it.unisalento.se.saw.dto.ProfessorDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;

import java.util.List;
import java.util.Set;

public interface IProfessorServices {

    public List<Professor> getAll();
    public Professor save(ProfessorDTO professorDTO) throws CourseNotFoundException;
    public Professor getById(int id) throws ProfessorNotFoundException;
    public List<Professor> getByName(String name) throws ProfessorNotFoundException;
    public List<Professor> getBySurname(String surname) throws ProfessorNotFoundException;
    public Set<Professor> getByIdCourse(int id) throws ProfessorNotFoundException;
}
