package it.unisalento.se.saw.services;

import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.domain.Student;
import it.unisalento.se.saw.domain.StudentId;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.StudentDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.StudentNotFoundException;
import it.unisalento.se.saw.repositories.StudentRepository;
import it.unisalento.se.saw.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    CourseService courseService;

    @InjectMocks
    StudentService studentService;

    @Test
    public void getAllTest() {

        /***************************Course***************************/
        Course course = new Course();
        course.setCredits(1);
        course.setLanguage("language");
        course.setLenght(2);
        course.setLocation("location");
        course.setName("name");
        course.setType("type");
        course.setIdCourse(3);
        /******************************************************/

        /*****************User*************************************/
        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");
        /******************************************************/

        /************************Student******************************/
        StudentId studentId = new StudentId();
        studentId.setIdStudent(1);
        studentId.setUserIdUser(1);
        studentId.setCourseIdCourse(3);

        Student student = new Student();
        student.setId(studentId);
        student.setUser(user);
        student.setCourse(course);
        student.setMatricola("matricola");
        student.setYear(1);
        student.setYearStart(1);
        /******************************************************/
        List<Student> students = new ArrayList<>();
        students.add(student);

        when(studentRepository.findAll()).thenReturn(students);
        Set<StudentDTO> studentDTOS = studentService.getAll();
        assertEquals(student.getId().getIdStudent(), studentDTOS.iterator().next().getId());
    }

    @Test
    public void getByCourseTest() {

        /***************************Course***************************/
        Course course = new Course();
        course.setCredits(1);
        course.setLanguage("language");
        course.setLenght(2);
        course.setLocation("location");
        course.setName("name");
        course.setType("type");
        course.setIdCourse(3);
        /******************************************************/

        /*****************User*************************************/
        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");
        /******************************************************/

        /************************Student******************************/
        StudentId studentId = new StudentId();
        studentId.setIdStudent(1);
        studentId.setUserIdUser(1);
        studentId.setCourseIdCourse(3);

        Student student = new Student();
        student.setId(studentId);
        student.setUser(user);
        student.setCourse(course);
        student.setMatricola("matricola");
        student.setYear(1);
        student.setYearStart(1);
        /******************************************************/
        List<Student> students = new ArrayList<>();
        students.add(student);

        when(studentRepository.findStudentsByCourse_IdCourse(3)).thenReturn(students);
        Set<StudentDTO> studentDTOS = studentService.getByCourse(3);
        assertEquals(student.getId().getIdStudent(), studentDTOS.iterator().next().getId());
    }

    @Test
    public void getByIdTest() throws StudentNotFoundException {

        /***************************Course***************************/
        Course course = new Course();
        course.setCredits(1);
        course.setLanguage("language");
        course.setLenght(2);
        course.setLocation("location");
        course.setName("name");
        course.setType("type");
        course.setIdCourse(3);
        /******************************************************/

        /*****************User*************************************/
        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");
        /******************************************************/

        /************************Student******************************/
        StudentId studentId = new StudentId();
        studentId.setIdStudent(1);
        studentId.setUserIdUser(1);
        studentId.setCourseIdCourse(3);

        Student student = new Student(studentId,course,user);
        student.setMatricola("matricola");
        student.setYear(1);
        student.setYearStart(1);
        /******************************************************/

        when(studentRepository.findStudentById_IdStudent(1)).thenReturn(student);
        StudentDTO studentDTO = studentService.getById(1);
        assertEquals(student.getId().getIdStudent(), studentDTO.getId());


    }

    @Test
    public void getByUidTest() throws StudentNotFoundException {

        /***************************Course***************************/
        Course course = new Course();
        course.setCredits(1);
        course.setLanguage("language");
        course.setLenght(2);
        course.setLocation("location");
        course.setName("name");
        course.setType("type");
        course.setIdCourse(3);
        /******************************************************/

        /*****************User*************************************/
        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");
        /******************************************************/

        /************************Student******************************/
        StudentId studentId = new StudentId();
        studentId.setIdStudent(1);
        studentId.setUserIdUser(1);
        studentId.setCourseIdCourse(3);

        Student student = new Student(studentId,course,user,"matricola",1,1,null);
        /******************************************************/

        when(studentRepository.findStudentByUserUid("uid")).thenReturn(student);
        StudentDTO studentDTO = studentService.getByUid("uid");
        assertEquals(student.getId().getIdStudent(), studentDTO.getId());


    }

    @Test
    public void getByIdErrorTest() throws StudentNotFoundException {

        /***************************Course***************************/
        Course course = new Course();
        course.setCredits(1);
        course.setLanguage("language");
        course.setLenght(2);
        course.setLocation("location");
        course.setName("name");
        course.setType("type");
        course.setIdCourse(3);
        /******************************************************/

        /*****************User*************************************/
        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");
        /******************************************************/

        /************************Student******************************/
        StudentId studentId = new StudentId();
        studentId.setIdStudent(1);
        studentId.setUserIdUser(1);
        studentId.setCourseIdCourse(3);

        Student student = new Student();
        student.setId(studentId);
        student.setUser(user);
        student.setCourse(course);
        student.setMatricola("matricola");
        student.setYear(1);
        student.setYearStart(1);
        /******************************************************/

        when(studentRepository.findStudentById_IdStudent(1)).thenReturn(student);

        try{
            StudentDTO studentDTO = studentService.getById(2);
            assertEquals(student.getId().getIdStudent(), studentDTO.getId());
            Assert.fail();
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
            assertEquals("Student not found", e.getMessage());

        }

    }

    @Test
    public void getDomainByIdTest() throws StudentNotFoundException {

        /***************************Course***************************/
        Course course = new Course();
        course.setCredits(1);
        course.setLanguage("language");
        course.setLenght(2);
        course.setLocation("location");
        course.setName("name");
        course.setType("type");
        course.setIdCourse(3);
        /******************************************************/

        /*****************User*************************************/
        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");
        /******************************************************/

        /************************Student******************************/
        StudentId studentId = new StudentId();
        studentId.setIdStudent(1);
        studentId.setUserIdUser(1);
        studentId.setCourseIdCourse(3);

        Student student = new Student();
        student.setId(studentId);
        student.setUser(user);
        student.setCourse(course);
        student.setMatricola("matricola");
        student.setYear(1);
        student.setYearStart(1);
        /******************************************************/

        when(studentRepository.findStudentById_IdStudent(1)).thenReturn(student);
        Student s = studentService.getDomainById(1);
        assertEquals(student,s);


    }

    @Test
    public void saveTest() throws StudentNotFoundException, CourseNotFoundException {

        /***************************Course***************************/
        Course course = new Course();
        course.setCredits(1);
        course.setLanguage("language");
        course.setLenght(2);
        course.setLocation("location");
        course.setName("name");
        course.setType("type");
        course.setIdCourse(3);
        /******************************************************/

        /*****************User*************************************/
        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");
        /******************************************************/

        /************************Student******************************/
        StudentId studentId = new StudentId();
        studentId.setIdStudent(1);
        studentId.setUserIdUser(1);
        studentId.setCourseIdCourse(3);

        Student student = new Student();
        student.setId(studentId);
        student.setUser(user);
        student.setCourse(course);
        student.setMatricola("matricola");
        student.setYear(1);
        student.setYearStart(1);
        /******************************************************/

        /*****************StudentDTO*************************************/
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setUid("uid");
        studentDTO.setYear(1);
        studentDTO.setYearStart(1);
        studentDTO.setMatricola("matricola");
        studentDTO.setId(1);
        studentDTO.setIdUser(1);
        studentDTO.setIdCourse(3);
        /******************************************************/

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(courseService.getDomainById(3)).thenReturn(course);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        StudentDTO s = studentService.save(studentDTO);
        assertEquals(s.getId(), studentDTO.getId());


    }



}
