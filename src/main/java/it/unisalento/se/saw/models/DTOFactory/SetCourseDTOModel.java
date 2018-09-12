package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.dto.CourseDTO;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.FactoryProducer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetCourseDTOModel implements DTO<List<Course>, List<CourseDTO>> {
    @Override
    public List<CourseDTO> create(List<Course> courses) {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<Course, CourseDTO> dto = abstractFactory.getDTO("course");
        List<CourseDTO> courseDTOS = new ArrayList<>(0);
        for (Course course: courses){
            courseDTOS.add(dto.create(course));
        }
        return courseDTOS;
    }
}
