package it.unisalento.se.saw.models;

import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.dto.CourseDTO;

public class CourseDTOModel implements DTO<Course, CourseDTO> {
    @Override
    public CourseDTO create(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getIdCourse());
        courseDTO.setCredits(course.getCredits());
        courseDTO.setLanguage(course.getLanguage());
        courseDTO.setLenght(course.getLenght());
        courseDTO.setLocation(course.getLocation());
        courseDTO.setName(course.getName());
        courseDTO.setType(course.getType());
        return courseDTO;
    }
}
