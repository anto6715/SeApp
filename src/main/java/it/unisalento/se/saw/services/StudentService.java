package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.IStudentServices;
import it.unisalento.se.saw.domain.Student;
import it.unisalento.se.saw.dto.StudentDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.StudentNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.DomainFactory.Domain;
import it.unisalento.se.saw.models.FactoryProducer;
import it.unisalento.se.saw.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OneToOne;
import java.util.List;
import java.util.Set;

@Service
public class StudentService implements IStudentServices {


    @Autowired
    StudentRepository studentRepository;


    AbstractFactory domainFactory = FactoryProducer.getFactory("DOMAIN");
    AbstractFactory dtoFactory = FactoryProducer.getFactory("DTO");


    @Transactional(readOnly = true)
    public Set<StudentDTO> getAll() {
        DTO<List<Student>, Set<StudentDTO>> dto = dtoFactory.getDTO("SetStudent");
        return dto.create(studentRepository.findAll());
    }

    @Transactional(readOnly = true)
    @OneToOne
    public StudentDTO getById(int id) throws StudentNotFoundException {
        try {
            DTO<Student, StudentDTO> dto = dtoFactory.getDTO("Student");
            return dto.create(studentRepository.findStudentById_IdStudent(id));
        } catch (Exception e) {
            throw new StudentNotFoundException();
        }
    }

    public Set<StudentDTO> getByCourse(int course){
        DTO<List<Student>, Set<StudentDTO>> dto = dtoFactory.getDTO("SetStudent");
        return dto.create(studentRepository.findStudentsByCourse_IdCourse(course));
    }



    @Transactional
    public StudentDTO getByUid(String uid) throws StudentNotFoundException{
        try {
            DTO<Student, StudentDTO> dto = dtoFactory.getDTO("Student");
            return dto.create(studentRepository.findStudentByUserUid(uid));
        } catch (Exception e){
            throw new StudentNotFoundException();
        }
    }
    @Transactional
    public StudentDTO save(StudentDTO studentDTO) throws CourseNotFoundException {

        Domain<StudentDTO,Student> domain = domainFactory.getDomain("Student");
        DTO<Student, StudentDTO> dto = dtoFactory.getDTO("Student");
        return dto.create(studentRepository.save(domain.create(studentDTO)));
    }

}
