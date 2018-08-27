package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.domain.Professor;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.dto.CourseDTO;
import it.unisalento.se.saw.dto.ProfessorDTO;
import it.unisalento.se.saw.dto.TeachingDTO;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.FactoryProducer;

public class TeachingDTOModel implements DTO<Teaching, TeachingDTO> {
    @Override
    public TeachingDTO create(Teaching teaching) {
        AbstractFactory abstractFactory = FactoryProducer.getFactory(("DTO"));

        DTO<Professor, ProfessorDTO> dtoProfessor = abstractFactory.getDTO("PROFESSOR");
        ProfessorDTO professorDTO = dtoProfessor.create(teaching.getProfessor());

        DTO<Course, CourseDTO> dtoCourse = abstractFactory.getDTO("COURSE");
        CourseDTO courseDTO = dtoCourse.create(teaching.getCourse());

        TeachingDTO teachingDTO = new TeachingDTO();
        teachingDTO.setId(teaching.getId().getIdTeaching());
        teachingDTO.setIdCourse(teaching.getId().getCourseIdCourse());
        teachingDTO.setIdProfessor(teaching.getId().getProfessorIdProfessor());
        teachingDTO.setName(teaching.getName());
        teachingDTO.setCredits(teaching.getCredits());
        teachingDTO.setYear(teaching.getYear());
        teachingDTO.setProfessorDTO(professorDTO);
        teachingDTO.setCourseDTO(courseDTO);
        return teachingDTO;
    }
}
