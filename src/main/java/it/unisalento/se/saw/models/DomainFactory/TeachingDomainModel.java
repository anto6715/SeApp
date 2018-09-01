package it.unisalento.se.saw.models.DomainFactory;

import it.unisalento.se.saw.Iservices.ICourseServices;
import it.unisalento.se.saw.Iservices.IProfessorServices;
import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.domain.Professor;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.domain.TeachingId;
import it.unisalento.se.saw.dto.TeachingDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

public class TeachingDomainModel implements Domain<TeachingDTO, Teaching> {
    @Autowired
    ICourseServices courseServices;

    @Autowired
    IProfessorServices professorServices;

    @Override
    public Teaching create(TeachingDTO teachingDTO) {
        Course course = null;
        try {
            course = courseServices.getById(teachingDTO.getIdCourse());
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
        }
        Professor professor = null;
        try {
            professor = professorServices.getById(teachingDTO.getIdProfessor());
        } catch (ProfessorNotFoundException e) {
            e.printStackTrace();
        }

        TeachingId teachingId = new TeachingId();
        teachingId.setCourseIdCourse(course.getIdCourse());
        teachingId.setProfessorIdProfessor(professor.getId().getIdProfessor());
        teachingId.setProfessorUserIdUser(professor.getId().getUserIdUser());


        Teaching teaching = new Teaching();
        teaching.setCredits(teachingDTO.getCredits());
        teaching.setName(teachingDTO.getName());
        teaching.setYear(teachingDTO.getYear());
        teaching.setCourse(course);
        teaching.setProfessor(professor);

        teaching.setId(teachingId);
        return teaching;
    }
}
