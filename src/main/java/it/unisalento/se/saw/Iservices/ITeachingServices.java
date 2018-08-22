package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.dto.TeachingDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;

import java.util.List;

public interface ITeachingServices {

    public List<Teaching> getAll();
    public Teaching getById(int id) throws TeachingNotFoundException;
    public Teaching getByNameAndIdCourse(String name, int idCourse) throws TeachingNotFoundException;
    public Teaching getByNameAndIdProf(String name, int idCourse) throws TeachingNotFoundException;
    public List<Teaching> getByIdCourse(int id);
    public List<Teaching> getByProf(int id);
    public Teaching save(TeachingDTO teachingDTO) throws CourseNotFoundException, ProfessorNotFoundException;
    public void remove(int id) throws TeachingNotFoundException;

}
