package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.Student;
import it.unisalento.se.saw.dto.StudentDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.StudentNotFoundException;

import java.util.List;
import java.util.Set;

public interface IStudentServices {

    public List<StudentDTO> getAll();
    public List<StudentDTO> getByCourse(int course);
    public StudentDTO getById(int id) throws StudentNotFoundException;
    public Student getDomainById(int id) throws StudentNotFoundException;
    public StudentDTO getByUid(String uid) throws StudentNotFoundException;
    StudentDTO save(StudentDTO studentDTO);

}
