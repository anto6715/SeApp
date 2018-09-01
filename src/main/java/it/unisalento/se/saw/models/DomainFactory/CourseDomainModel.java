package it.unisalento.se.saw.models.DomainFactory;

import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.dto.CourseDTO;

public class CourseDomainModel implements Domain<CourseDTO, Course> {
    @Override
    public Course create(CourseDTO courseDTO) {
        Course course = new Course();
        course.setCredits(courseDTO.getCredits());
        course.setLanguage(courseDTO.getLanguage());
        course.setLenght(courseDTO.getLenght());
        course.setLocation(courseDTO.getLocation());
        course.setName(courseDTO.getName());
        course.setType(courseDTO.getType());
        return course;
    }
}
