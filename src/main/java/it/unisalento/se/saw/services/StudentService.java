package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.ICourseServices;
import it.unisalento.se.saw.Iservices.IStudentServices;
import it.unisalento.se.saw.Iservices.IUserServices;
import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.domain.Student;
import it.unisalento.se.saw.domain.StudentId;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.CourseDTO;
import it.unisalento.se.saw.dto.StudentDTO;
import it.unisalento.se.saw.dto.UserDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.StudentNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.DomainFactory.Domain;
import it.unisalento.se.saw.models.FactoryProducer;
import it.unisalento.se.saw.repositories.StudentRepository;
import it.unisalento.se.saw.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.Set;

@Service
public class StudentService implements IStudentServices {


    @Autowired
    StudentRepository studentRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ICourseServices courseServices;


    AbstractFactory domainFactory = FactoryProducer.getFactory("DOMAIN");
    AbstractFactory dtoFactory = FactoryProducer.getFactory("DTO");


    @Transactional(readOnly = true)
    public List<StudentDTO> getAll() {
        DTO<List<Student>, List<StudentDTO>> dto = dtoFactory.getDTO("SetStudent");
        return dto.create(studentRepository.findAll());
    }

    @Transactional(readOnly = true)
    public StudentDTO getById(int id) throws StudentNotFoundException {
        try{
            DTO<Student, StudentDTO> dto = dtoFactory.getDTO("Student");
            return dto.create(studentRepository.findStudentById_IdStudent(id));
        } catch (Exception e) {
            throw new StudentNotFoundException();
        }

    }

    @Transactional(readOnly = true)
    public Student getDomainById(int id) throws StudentNotFoundException {
        Student student = studentRepository.findStudentById_IdStudent(id);
        if (student != null)
            return student;
        else throw new StudentNotFoundException();
    }

    public List<StudentDTO> getByCourse(int course){
        DTO<List<Student>, List<StudentDTO>> dto = dtoFactory.getDTO("SetStudent");
        return dto.create(studentRepository.findStudentsByCourse_IdCourse(course));
    }

    @Transactional
    public StudentDTO getByUid(String uid) throws StudentNotFoundException{
        DTO<Student, StudentDTO> dto = dtoFactory.getDTO("Student");
        Student student = studentRepository.findStudentByUserUid(uid);
        if (student != null)
            return dto.create(student);
        else
            throw new StudentNotFoundException();
    }

    @Transactional
    public StudentDTO save(StudentDTO studentDTO) {
        Domain<StudentDTO, User> domain = domainFactory.getDomain("USER");
        DTO<Student,StudentDTO> dtoStudent = dtoFactory.getDTO("Student");

        User user = domain.create(studentDTO);

        User saveUser = userRepository.save(user);
        Course course = null;
        try {
            course = courseServices.getDomainById(studentDTO.getIdCourse());
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
        }

        StudentId studentId = new StudentId();
        studentId.setCourseIdCourse(studentDTO.getIdCourse());
        studentId.setUserIdUser(saveUser.getIdUser());

        Student student = new Student();
        student.setMatricola(studentDTO.getMatricola());
        student.setYear(studentDTO.getYear());
        student.setYearStart(studentDTO.getYearStart());
        student.setUser(saveUser);
        student.setId(new StudentId(0,studentDTO.getIdCourse(),saveUser.getIdUser()));
        student.setCourse(course);
        return dtoStudent.create(studentRepository.save(student));
    }

}
