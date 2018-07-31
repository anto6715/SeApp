package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.dto.StudentDTO;
import it.unisalento.se.saw.exceptions.StudentNotFoundException;
import it.unisalento.se.saw.domain.Student;
import it.unisalento.se.saw.exceptions.UserNotFoundException;

import java.util.List;

public interface IStudentServices {

    public List<Student> getAll();
    public List<Student> getByCourse(int course);
    public Student getById(int id) throws StudentNotFoundException;
    public StudentDTO getByName(String name) throws StudentNotFoundException, UserNotFoundException;
    public Student getByCourse(String course) throws StudentNotFoundException;
    public Student save(Student student);
    public void removeById(int id) throws StudentNotFoundException;
    public void removeByMatricola(int matricola) throws StudentNotFoundException;
}
