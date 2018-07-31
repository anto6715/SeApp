package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;

import java.util.List;

public interface ICourseServices {

    public List<Course> getAll();
    public Course getByName(String name) throws CourseNotFoundException;
    public Course save(Course course);
    public Course getById(int id) throws CourseNotFoundException;
    public void removeCourseById(int id) throws CourseNotFoundException;
}
