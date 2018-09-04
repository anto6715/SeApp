package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.ICourseServices;
import it.unisalento.se.saw.Iservices.IProfessorServices;
import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.domain.Professor;
import it.unisalento.se.saw.domain.ProfessorId;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.ProfessorDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.models.*;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.DomainFactory.Domain;
import it.unisalento.se.saw.repositories.ProfessorRepository;
import it.unisalento.se.saw.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProfessorService implements IProfessorServices {

    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ICourseServices courseServices;


    AbstractFactory domainFactory = FactoryProducer.getFactory("DOMAIN");
    AbstractFactory dtoFactory = FactoryProducer.getFactory("DTO");


    @Transactional(readOnly = true)
    public Set<ProfessorDTO> getAll() {
        DTO<List<Professor>, Set<ProfessorDTO>> dto = dtoFactory.getDTO("SetProfessor");
        return dto.create(professorRepository.findAll());
    }

    @Transactional
    public ProfessorDTO save(ProfessorDTO professorDTO) throws CourseNotFoundException {
        DTO<Professor,ProfessorDTO> dtoProf = dtoFactory.getDTO("Professor");
        Domain<ProfessorDTO,User> domainProfDTOUser = domainFactory.getDomain("USER");

        User saveUser = userRepository.save(domainProfDTOUser.create(professorDTO));
        Course course = courseServices.getDomainById(professorDTO.getCourse());

        ProfessorId professorId = new ProfessorId();
        professorId.setUserIdUser(saveUser.getIdUser());

        Professor professor = new Professor();
        professor.setUser(saveUser);
        professor.setId(professorId);

        //professor save non restituisce idProfessor necessario per salvare in professor_has_couse
        Professor professorTemp = professorRepository.save(professor);
        Professor professorHasCourse = professorRepository.findProfessorById_UserIdUser(professorTemp.getId().getUserIdUser());
        professorHasCourse.getCourses().add(course);
        return dtoProf.create(professorRepository.saveAndFlush(professorHasCourse));
    }

    @Transactional
    public ProfessorDTO getById(int id) throws ProfessorNotFoundException {
       try {
           DTO<Professor,ProfessorDTO> dto = dtoFactory.getDTO("Professor");
           return dto.create(professorRepository.findProfessorById_IdProfessor(id));
       } catch (Exception e) {
           throw new ProfessorNotFoundException();
       }
    }

    @Transactional
    public Professor getDomainById(int id) throws ProfessorNotFoundException {
        return professorRepository.findProfessorById_IdProfessor(id);
    }

    @Transactional
    public ProfessorDTO getByUid(String uid) throws ProfessorNotFoundException {
        try {
            DTO<Professor,ProfessorDTO> dto = dtoFactory.getDTO("Professor");
            return dto.create(professorRepository.findProfessorByUserUid(uid));
        } catch (Exception e) {
            throw new ProfessorNotFoundException();
        }
    }

    public Set<ProfessorDTO> getByIdCourse(int id) throws ProfessorNotFoundException {
        try {
            DTO<List<Professor>, Set<ProfessorDTO>> dto = dtoFactory.getDTO("SetProfessor");
            Course course = courseServices.getDomainById(id);
            List<Professor> professors = new ArrayList<>( course.getProfessors());
            return dto.create(professors);
        } catch (Exception e) {
            throw new ProfessorNotFoundException();
        }
    }

}
