package it.unisalento.se.saw.services;


import it.unisalento.se.saw.Iservices.ICourseServices;
import it.unisalento.se.saw.Iservices.IProfessorServices;
import it.unisalento.se.saw.Iservices.ITeachingServices;
import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.domain.Professor;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.domain.TeachingId;
import it.unisalento.se.saw.dto.TeachingDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;
import it.unisalento.se.saw.repositories.TeachingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeachingService implements ITeachingServices {

    @Autowired
    TeachingRepository teachingRepository;

    @Autowired
    ICourseServices courseServices;

    @Autowired
    IProfessorServices professorServices;

    @Transactional(readOnly = true)
    public List<Teaching> getAll() {
        return teachingRepository.findAll();
    }

    public Teaching getById(int id) throws TeachingNotFoundException {
        try {
            return teachingRepository.findTeachingById_IdTeaching(id);
        } catch (Exception e) {
            throw new TeachingNotFoundException();
        }
    }

    public Teaching getByName(String name) throws TeachingNotFoundException {
        try {
            return teachingRepository.findTeachingByName(name);
        } catch (Exception e) {
            throw new TeachingNotFoundException();
        }
    }

    public List<Teaching> getByIdCourse(int id) {
        return teachingRepository.findTeachingsById_CourseIdCourse(id);
    }


    @Transactional
    public Teaching save(TeachingDTO teachingDTO) throws CourseNotFoundException, ProfessorNotFoundException {

        Course course = courseServices.getById(teachingDTO.getIdCourse());
        Professor professor = professorServices.getById(teachingDTO.getIdProfessor());

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


        return teachingRepository.saveAndFlush(teaching);
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
