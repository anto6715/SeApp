package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.IStudentServices;
import it.unisalento.se.saw.Iservices.IUserServices;
import it.unisalento.se.saw.domain.Student;
import it.unisalento.se.saw.domain.StudentId;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.StudentDTO;
import it.unisalento.se.saw.exceptions.StudentNotFoundException;
import it.unisalento.se.saw.exceptions.UserNotFoundException;
import it.unisalento.se.saw.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OneToOne;
import java.util.List;

@Service
public class StudentService implements IStudentServices {


    @Autowired
    StudentRepository studentRepository;

    @Autowired
    IUserServices userServices;


    @Transactional(readOnly = true)
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    @OneToOne
    public Student getById(int id) throws StudentNotFoundException {
        try {
            Student student = studentRepository.findStudentById_IdStudent(id);
            return student;
        } catch (Exception e) {
            throw new StudentNotFoundException();
        }
    }

    public List<Student> getByCourse(int course){
        return studentRepository.findStudentsByCourse_IdCourse(course);
    }

    @Override
    public StudentDTO getByName(String name) throws StudentNotFoundException, UserNotFoundException {
        try {
            User user = userServices.getByName(name);
            Student student = studentRepository.findStudentById_UserIdUser(user.getIdUser());
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setYearStart(student.getYearStart());
            studentDTO.setYear(student.getYear());
            studentDTO.setMatricola(student.getMatricola());
            studentDTO.setAge(user.getAge());
            studentDTO.setEmail(user.getEmail());
            studentDTO.setPassword(user.getPassword());
            studentDTO.setName(user.getName());
            studentDTO.setSurname(user.getSurname());
            studentDTO.setUid(user.getUid());

            return studentDTO;
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public Student getByCourse(String course) throws StudentNotFoundException {
        return null;
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void removeById(int id) throws StudentNotFoundException {

    }

    @Override
    public void removeByMatricola(int matricola) throws StudentNotFoundException {
    }
}
