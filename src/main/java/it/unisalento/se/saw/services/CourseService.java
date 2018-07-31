package it.unisalento.se.saw.services;


import it.unisalento.se.saw.Iservices.ICourseServices;
import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.UserNotFoundException;
import it.unisalento.se.saw.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService implements ICourseServices {

    @Autowired
    CourseRepository courseRepository;

    @Transactional(readOnly = true)
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course getByName(String name) throws CourseNotFoundException {
        try {
            Course course = courseRepository.getCourseByName(name);
            return course;
        } catch (Exception e) {
            throw  new CourseNotFoundException();
        }
    }

    @Transactional
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course getById(int id) throws CourseNotFoundException {
        try {
            Course course = courseRepository.getOne(id);
            return course;
        } catch (Exception e) {
            throw new CourseNotFoundException();
        }
    }

    @Override
    public void removeCourseById(int id) throws CourseNotFoundException {
        try{
            Course course = courseRepository.getOne(id);
            courseRepository.delete(course);
        } catch (Exception e) {
            throw new CourseNotFoundException();
        }

    }
}
