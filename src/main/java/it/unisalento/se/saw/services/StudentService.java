package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.ICourseServices;
import it.unisalento.se.saw.Iservices.IStudentServices;
import it.unisalento.se.saw.Iservices.IUserServices;
import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.domain.Student;
import it.unisalento.se.saw.domain.StudentId;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.StudentDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.StudentNotFoundException;
import it.unisalento.se.saw.exceptions.UserNotFoundException;
import it.unisalento.se.saw.models.Domain;
import it.unisalento.se.saw.models.DomainFactory;
import it.unisalento.se.saw.repositories.StudentRepository;
import it.unisalento.se.saw.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OneToOne;
import java.util.List;

@Service
public class StudentService implements IStudentServices {


    @Autowired
    StudentRepository studentRepository;

    @Autowired
    IUserServices userServices;

    @Autowired
    ICourseServices courseServices;
    @Autowired
    UserRepository userRepository;


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

    @Transactional
    public List<Student> getByName(String name) {
        return studentRepository.findStudentsByUser_NameAndUserUserType(name,1);
    }


    @Transactional
    public Student getByUid(String uid) throws StudentNotFoundException{
        try {
            return studentRepository.findStudentByUserUid(uid);
        } catch (Exception e){
            throw new StudentNotFoundException();
        }
    }
    @Transactional
    public Student save(StudentDTO studentDTO) throws CourseNotFoundException {
        DomainFactory domainFactory = new DomainFactory();
        Domain<StudentDTO,User> domain = domainFactory.getDomain("USER");
        User user = domain.create(studentDTO);

        User saveUser = userServices.save(user);
        Course course = courseServices.getById(studentDTO.getIdCourse());

        StudentId studentId = new StudentId();
        studentId.setCourseIdCourse(studentDTO.getIdCourse());
        studentId.setUserIdUser(saveUser.getIdUser());

        Student student = new Student();
        student.setMatricola(studentDTO.getMatricola());
        student.setYear(studentDTO.getYear());
        student.setYearStart(studentDTO.getYearStart());
        student.setUser(saveUser);
        student.setId(new StudentId(0,studentDTO.getIdCourse(),user.getIdUser()));
        student.setCourse(course);

        return studentRepository.save(student);
    }

    @Transactional(rollbackFor = UserNotFoundException.class)
    public void removeById(int id) throws StudentNotFoundException {
        try {
            Student student = studentRepository.findStudentById_IdStudent(id);
            studentRepository.delete(student);
            userServices.removeUserById(student.getId().getUserIdUser());
        } catch (Exception e){
            throw new StudentNotFoundException();
        }
    }

}
