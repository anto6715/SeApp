package it.unisalento.se.saw.services;


import it.unisalento.se.saw.Iservices.ICourseServices;
import it.unisalento.se.saw.Iservices.IProfessorServices;
import it.unisalento.se.saw.Iservices.ITeachingServices;
import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.domain.Professor;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.domain.TeachingId;
import it.unisalento.se.saw.dto.CourseDTO;
import it.unisalento.se.saw.dto.TeachingDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.DomainFactory.Domain;
import it.unisalento.se.saw.models.FactoryProducer;
import it.unisalento.se.saw.repositories.TeachingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class TeachingService implements ITeachingServices {

    @Autowired
    TeachingRepository teachingRepository;

    @Autowired
    ICourseServices courseServices;

    @Autowired
    IProfessorServices professorServices;

    AbstractFactory abstractDTOFactory = FactoryProducer.getFactory("DTO");
    AbstractFactory abstractDomainFactory = FactoryProducer.getFactory("DOMAIN");

    @Transactional(readOnly = true)
    public Set<TeachingDTO> getAll() {
        DTO<List<Teaching>, Set<TeachingDTO>> dto = this.abstractDTOFactory.getDTO("SETTEACHING");
        return dto.create(teachingRepository.findAll());
    }

    public TeachingDTO getById(int id) throws TeachingNotFoundException {
        try {
            DTO<Teaching, TeachingDTO> dto = this.abstractDTOFactory.getDTO("Teaching");
            return dto.create(teachingRepository.findTeachingById_IdTeaching(id));
        } catch (Exception e) {
            throw new TeachingNotFoundException();
        }
    }

    public Teaching getDomainById(int id) throws TeachingNotFoundException {
        return teachingRepository.findTeachingById_IdTeaching(id);
    }

    public TeachingDTO getByNameAndIdCourse(String name, int idCourse) throws TeachingNotFoundException {
        try {
            DTO<Teaching, TeachingDTO> dto = this.abstractDTOFactory.getDTO("Teaching");
            return dto.create(teachingRepository.findTeachingByNameAndAndCourse_IdCourse(name, idCourse));
        } catch (Exception e) {
            throw new TeachingNotFoundException();
        }
    }
    @Transactional
    public TeachingDTO getByNameAndIdProf(String name, int idProf) throws TeachingNotFoundException {
        try {
            DTO<Teaching, TeachingDTO> dto = this.abstractDTOFactory.getDTO("Teaching");
            return dto.create(teachingRepository.findTeachingByNameAndProfessor_Id_IdProfessor(name, idProf));
        } catch (Exception e) {
            throw new TeachingNotFoundException();
        }
    }

    public Set<TeachingDTO> getByIdCourse(int id) {
        DTO<List<Teaching>, Set<TeachingDTO>> dto = this.abstractDTOFactory.getDTO("SETTEACHING");
        return dto.create(teachingRepository.findTeachingsById_CourseIdCourse(id));
    }

    public Set<TeachingDTO> getByProf(int id) {
        DTO<List<Teaching>, Set<TeachingDTO>> dto = this.abstractDTOFactory.getDTO("SETTEACHING");
        return dto.create(teachingRepository.findTeachingsById_ProfessorIdProfessor(id));
    }


    @Transactional
    public TeachingDTO save(TeachingDTO teachingDTO) {
        DTO<Teaching, TeachingDTO> dtoTeaching = abstractDTOFactory.getDTO("Teaching");
        Domain<CourseDTO, Course> courseDomain = abstractDomainFactory.getDomain("COURSE");
        Course course = null;
        try {
            course = courseServices.getDomainById(teachingDTO.getIdCourse());
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
        }

        Professor professor = null;
        try {
            professor = professorServices.getDomainById(teachingDTO.getIdProfessor());
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
        return dtoTeaching.create(teachingRepository.save(teaching));
    }
}
