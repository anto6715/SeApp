package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.dto.CourseDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;

import java.util.List;
import java.util.Set;

public interface ICourseServices {

    public Set<CourseDTO> getAll();
    public CourseDTO getByName(String name) throws CourseNotFoundException;
    public CourseDTO save(CourseDTO courseDTO);
    public CourseDTO getById(int id) throws CourseNotFoundException;
    public Course getDomainById(int id);
}
