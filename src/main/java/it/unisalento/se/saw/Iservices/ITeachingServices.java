package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.dto.TeachingDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;

import java.util.List;
import java.util.Set;

public interface ITeachingServices {

    public List<TeachingDTO> getAll();
    public TeachingDTO getById(int id) throws TeachingNotFoundException;
    public TeachingDTO getByNameAndIdCourse(String name, int idCourse) throws TeachingNotFoundException;
    public TeachingDTO getByNameAndIdProf(String name, int idCourse) throws TeachingNotFoundException;
    public List<TeachingDTO> getByIdCourse(int id);
    public List<TeachingDTO> getByProf(int id);
    public TeachingDTO save(TeachingDTO teachingDTO);
    public Teaching getDomainById(int id) throws TeachingNotFoundException;

}
