package it.unisalento.se.saw.services;


import it.unisalento.se.saw.Iservices.ICourseServices;
import it.unisalento.se.saw.Iservices.IProfessorServices;
import it.unisalento.se.saw.Iservices.ITeachingServices;
import it.unisalento.se.saw.domain.Teaching;
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
    public TeachingDTO save(TeachingDTO teachingDTO) throws CourseNotFoundException, ProfessorNotFoundException {
        AbstractFactory abstractDomainFactory = FactoryProducer.getFactory("DOMAIN");
        AbstractFactory abstractDTOFactory = FactoryProducer.getFactory("DTO");
        Domain<TeachingDTO, Teaching> domainTeaching = abstractDomainFactory.getDomain("Teaching");
        DTO<Teaching, TeachingDTO> dtoTeaching = abstractDTOFactory.getDTO("Teaching");
        return dtoTeaching.create(teachingRepository.save(domainTeaching.create(teachingDTO)));
    }

    public void remove(int id) throws TeachingNotFoundException{
        try {
            Teaching teaching = teachingRepository.findTeachingById_IdTeaching(id);
            teachingRepository.delete(teaching);
        } catch (Exception e) {
            throw new TeachingNotFoundException();
        }

    }
}
