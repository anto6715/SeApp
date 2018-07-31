package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.StudentDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.StudentNotFoundException;
import it.unisalento.se.saw.domain.Student;
import it.unisalento.se.saw.exceptions.UserNotFoundException;

import java.util.List;

public interface IStudentServices {

    public List<Student> getAll();
    public List<Student> getByCourse(int course);
    public Student getById(int id) throws StudentNotFoundException;
    public Student getByName(String name) throws StudentNotFoundException, UserNotFoundException;

    Student save(StudentDTO studentDTO) throws CourseNotFoundException;

    public void removeById(int id) throws StudentNotFoundException;
}
