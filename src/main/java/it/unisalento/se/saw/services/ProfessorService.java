package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.ICourseServices;
import it.unisalento.se.saw.Iservices.IProfessorServices;
import it.unisalento.se.saw.Iservices.IUserServices;
import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.domain.Professor;
import it.unisalento.se.saw.domain.ProfessorId;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.ProfessorDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.models.*;
import it.unisalento.se.saw.models.DomainFactory.Domain;
import it.unisalento.se.saw.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProfessorService implements IProfessorServices {

    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    IUserServices userServices;
    @Autowired
    ICourseServices courseServices;

    @Transactional(readOnly = true)
    public List<Professor> getAll() {
        return professorRepository.findAll();
    }

    @Transactional
    public Professor save(ProfessorDTO professorDTO) throws CourseNotFoundException {

        AbstractFactory domainFactory = FactoryProducer.getFactory("DOMAIN");
        Domain<ProfessorDTO,User> domain = domainFactory.getDomain("USER");
        User user = domain.create(professorDTO);

        User saveUser = userServices.save(user);
        Course course = courseServices.getById(professorDTO.getCourse());

        ProfessorId professorId = new ProfessorId();
        professorId.setUserIdUser(user.getIdUser());

        Professor professor = new Professor();
        professor.setUser(user);
        professor.setId(professorId);

        //professor save non restituisce idProfessor necessario per salvare in professor_has_couse
        Professor professorTemp = professorRepository.save(professor);
        Professor professorHasCourse = professorRepository.findProfessorById_UserIdUser(professorTemp.getId().getUserIdUser());
        professorHasCourse.getCourses().add(course);
        return professorRepository.save(professorHasCourse);
    }

    @Transactional
    public Professor getById(int id) throws ProfessorNotFoundException {
       try {
           return professorRepository.findProfessorById_IdProfessor(id);
       } catch (Exception e) {
           throw new ProfessorNotFoundException();
       }
    }

    @Transactional
    public Professor getByUid(String uid) throws ProfessorNotFoundException {
        try {
            return professorRepository.findProfessorByUserUid(uid);
        } catch (Exception e) {
            throw new ProfessorNotFoundException();
        }
    }

    @Transactional
    public List<Professor> getByName(String name) throws ProfessorNotFoundException {
        try {
            return professorRepository.findProfessorsByUser_Name(name);
        } catch (Exception e) {
            throw new ProfessorNotFoundException();
        }
    }

    public List<Professor> getBySurname(String surnname) throws ProfessorNotFoundException {
        try {
            return professorRepository.findProfessorsByUser_Surname(surnname);
        } catch (Exception e) {
            throw new ProfessorNotFoundException();
        }
    }
    public Set<Professor> getByIdCourse(int id) throws ProfessorNotFoundException {
        try {

            Course course = courseServices.getById(id);
            return course.getProfessors();
        } catch (Exception e) {
            throw new ProfessorNotFoundException();
        }
    }

}
