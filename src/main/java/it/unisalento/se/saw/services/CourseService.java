package it.unisalento.se.saw.services;


import it.unisalento.se.saw.Iservices.ICourseServices;
import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.CourseDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.UserNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.DomainFactory.Domain;
import it.unisalento.se.saw.models.FactoryProducer;
import it.unisalento.se.saw.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CourseService implements ICourseServices {

    @Autowired
    CourseRepository courseRepository;

    AbstractFactory abstractDTOFactory = FactoryProducer.getFactory("DTO");
    AbstractFactory abstractDomainFactory = FactoryProducer.getFactory("DOMAIN");
    DTO<List<Course>, List<CourseDTO>> listCourseDto = abstractDTOFactory.getDTO("LISTCOURSE");
    DTO<Course, CourseDTO> courseDto = abstractDTOFactory.getDTO("COURSE");


    @Transactional(readOnly = true)
    public List<CourseDTO> getAll() {
        return listCourseDto.create(courseRepository.findAll());
    }

    @Override
    public CourseDTO getByName(String name) throws CourseNotFoundException {
        try {
            return courseDto.create(courseRepository.getCourseByName(name));
        } catch (Exception e) {
            throw  new CourseNotFoundException();
        }
    }

    @Transactional
    public CourseDTO save(CourseDTO courseDTO) {
        Domain<CourseDTO, Course> courseDomain = abstractDomainFactory.getDomain("Course");
        return courseDto.create(courseRepository.save(courseDomain.create(courseDTO)));
    }

    @Override
    public CourseDTO getById(int id) throws CourseNotFoundException {
        try {
            return courseDto.create(courseRepository.getOne(id));
        } catch (Exception e) {
            throw new CourseNotFoundException();
        }
    }

    public Course getDomainById(int id) throws CourseNotFoundException {
        return courseRepository.getOne(id);
    }

}
