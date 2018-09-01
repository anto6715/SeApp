package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.ICourseServices;
import it.unisalento.se.saw.Iservices.IProfessorServices;
import it.unisalento.se.saw.Iservices.IUserServices;
import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.domain.Professor;
import it.unisalento.se.saw.domain.ProfessorId;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.CourseDTO;
import it.unisalento.se.saw.dto.ProfessorDTO;
import it.unisalento.se.saw.dto.UserDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.models.*;
import it.unisalento.se.saw.models.DTOFactory.DTO;
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

    AbstractFactory domainFactory = FactoryProducer.getFactory("DOMAIN");
    AbstractFactory dtoFactory = FactoryProducer.getFactory("DTO");


    @Transactional(readOnly = true)
    public List<Professor> getAll() {
        return professorRepository.findAll();
    }

    @Transactional
    public Professor save(ProfessorDTO professorDTO) throws CourseNotFoundException {




        DTO<User, UserDTO> dto = dtoFactory.getDTO("User");
        Domain<ProfessorDTO,User> domainProfDTOUser = domainFactory.getDomain("USER");
        Domain<UserDTO,User> domainUserDTOUser = domainFactory.getDomain("USER");
        Domain<CourseDTO, Course> domainCourse = domainFactory.getDomain("COURSE");

        User user = domainProfDTOUser.create(professorDTO);

        User saveUser = domainUserDTOUser.create(userServices.save(dto.create(user)));
        Course course = domainCourse.create(courseServices.getById(professorDTO.getCourse()));

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
            Domain<CourseDTO, Course> domainCourse = domainFactory.getDomain("COURSE");
            Course course = domainCourse.create(courseServices.getById(id));
            return course.getProfessors();
        } catch (Exception e) {
            throw new ProfessorNotFoundException();
        }
    }

}
