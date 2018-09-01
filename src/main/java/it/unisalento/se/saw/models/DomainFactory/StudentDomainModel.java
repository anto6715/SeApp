package it.unisalento.se.saw.models.DomainFactory;

import it.unisalento.se.saw.Iservices.ICourseServices;
import it.unisalento.se.saw.Iservices.IUserServices;
import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.domain.Student;
import it.unisalento.se.saw.domain.StudentId;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.CourseDTO;
import it.unisalento.se.saw.dto.StudentDTO;
import it.unisalento.se.saw.dto.UserDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.FactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentDomainModel implements Domain<StudentDTO, Student> {

    @Autowired
    IUserServices userServices;

    @Autowired
    ICourseServices courseServices;

    @Override
    public Student create(StudentDTO studentDTO) {
        AbstractFactory domainFactory = FactoryProducer.getFactory("DOMAIN");
        AbstractFactory dtoFactory = FactoryProducer.getFactory("DTO");

        Domain<StudentDTO, User> domain = domainFactory.getDomain("USER");
        Domain<UserDTO,User> domainUserDTOUser = domainFactory.getDomain("User");
        Domain<CourseDTO, Course> domainCourse = domainFactory.getDomain("COURSE");
        DTO<User,UserDTO> dto = dtoFactory.getDTO("User");

        User user = domain.create(studentDTO);
        System.out.println(studentDTO.getUid());
        System.out.println(user.getUid());

        User saveUser = domainUserDTOUser.create(userServices.save(dto.create(user)));
        Course course = null;
        try {
            course = domainCourse.create(courseServices.getById(studentDTO.getIdCourse()));
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
        student.setId(new StudentId(0,studentDTO.getIdCourse(),user.getIdUser()));
        student.setCourse(course);
        return student;
    }
}
