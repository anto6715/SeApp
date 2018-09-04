package it.unisalento.se.saw.services;

import it.unisalento.se.saw.domain.*;
import it.unisalento.se.saw.dto.ExamDTO;
import it.unisalento.se.saw.exceptions.ExamNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;
import it.unisalento.se.saw.repositories.ExamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExamServiceTest {

    @Mock
    ExamRepository examRepository;

    @Mock
    RoomService roomService;

    @Mock
    TeachingService teachingService;

    @InjectMocks
    ExamService examService;

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

        /*************************Professor*****************************/

        ProfessorId professorId = new ProfessorId();
        professorId.setUserIdUser(1);
        professorId.setIdProfessor(1);

        Professor professor = new Professor();
        professor.setUser(user);
        professor.setId(professorId);

        /******************************************************/

        /*****************Teaching*************************************/
        TeachingId teachingId = new TeachingId(1,3,1,1);

        Teaching teaching = new Teaching();
        teaching.setName("Analisi");
        teaching.setProfessor(professor);
        teaching.setCourse(course);
        teaching.setId(teachingId);
        teaching.setCredits(10);
        teaching.setYear(1);
        /******************************************************/

        /****************************Room**************************/
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");
        /******************************************************/

        /*************************Exam*****************************/
        ExamId examId = new ExamId();
        examId.setRoomIdRoom(1);
        examId.setTeachingCourseIdCourse(3);
        examId.setTeachingProfessorIdProfessor(1);
        examId.setTeachingProfessorUserIdUser(1);
        examId.setIdExam(1);
        examId.setTeachingIdTeaching(1);

        Exam exam = new Exam();
        exam.setId(examId);
        exam.setRoom(room);
        exam.setTeaching(teaching);
        Date date = new Date();
        exam.setData(date);
        exam.setTime(date);
        /******************************************************/

        List<Exam> exams = new ArrayList<>();
        exams.add(exam);
        System.out.println(exams.size());
        when(examRepository.findAll()).thenReturn(exams);

        Set<ExamDTO> examDTOS = examService.getAll();

        assertEquals(exam.getId().getIdExam(), examDTOS.iterator().next().getId());
    }

    @Test
    public void getByIdTest() throws ExamNotFoundException {

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

        /*************************Professor*****************************/

        ProfessorId professorId = new ProfessorId();
        professorId.setUserIdUser(1);
        professorId.setIdProfessor(1);

        Professor professor = new Professor();
        professor.setUser(user);
        professor.setId(professorId);

        /******************************************************/

        /*****************Teaching*************************************/
        TeachingId teachingId = new TeachingId(1,3,1,1);

        Teaching teaching = new Teaching();
        teaching.setName("Analisi");
        teaching.setProfessor(professor);
        teaching.setCourse(course);
        teaching.setId(teachingId);
        teaching.setCredits(10);
        teaching.setYear(1);
        /******************************************************/

        /****************************Room**************************/
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");
        /******************************************************/

        /*************************Exam*****************************/
        ExamId examId = new ExamId(1,1,1,3,1,1);


        Exam exam = new Exam(examId,room,teaching);
        Date date = new Date();
        exam.setData(date);
        exam.setTime(date);
        /******************************************************/

        when(examRepository.findExamById_IdExam(1)).thenReturn(exam);

        ExamDTO examDTO = examService.getById(1);

        assertEquals(exam.getId().getRoomIdRoom(), examDTO.getIdRoom());
    }

    @Test
    public void getByIdErrorTest() throws ExamNotFoundException {

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

        /*************************Professor*****************************/

        ProfessorId professorId = new ProfessorId();
        professorId.setUserIdUser(1);
        professorId.setIdProfessor(1);

        Professor professor = new Professor();
        professor.setUser(user);
        professor.setId(professorId);

        /******************************************************/

        /*****************Teaching*************************************/
        TeachingId teachingId = new TeachingId(1,3,1,1);

        Teaching teaching = new Teaching();
        teaching.setName("Analisi");
        teaching.setProfessor(professor);
        teaching.setCourse(course);
        teaching.setId(teachingId);
        teaching.setCredits(10);
        teaching.setYear(1);
        /******************************************************/

        /****************************Room**************************/
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");
        /******************************************************/

        /*************************Exam*****************************/
        ExamId examId = new ExamId(1,1,1,3,1,1);


        Exam exam = new Exam(examId,room,teaching);
        Date date = new Date();
        exam.setData(date);
        exam.setTime(date);
        /******************************************************/

        when(examRepository.findExamById_IdExam(1)).thenReturn(exam);

        try {
            ExamDTO examDTO = examService.getById(2);
            assertEquals(exam.getId().getRoomIdRoom(), examDTO.getIdRoom());
        } catch (ExamNotFoundException e ) {
            assertEquals("Exam not found", e.getMessage());
        }


    }

    @Test
    public void saveTest() throws ExamNotFoundException, RoomNotFoundException, TeachingNotFoundException {

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

        /*************************Professor*****************************/

        ProfessorId professorId = new ProfessorId();
        professorId.setUserIdUser(1);
        professorId.setIdProfessor(1);

        Professor professor = new Professor();
        professor.setUser(user);
        professor.setId(professorId);

        /******************************************************/

        /*****************Teaching*************************************/
        TeachingId teachingId = new TeachingId(1,3,1,1);

        Teaching teaching = new Teaching();
        teaching.setName("Analisi");
        teaching.setProfessor(professor);
        teaching.setCourse(course);
        teaching.setId(teachingId);
        teaching.setCredits(10);
        teaching.setYear(1);
        /******************************************************/

        /****************************Room**************************/
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");
        /******************************************************/

        /*************************Exam*****************************/
        ExamId examId = new ExamId(1,1,1,3,1,1);

        Date date = new Date();
        Exam exam = new Exam(examId,room,teaching,date,date);
        /******************************************************/

        /*************************ExamDTO*****************************/
        ExamDTO examDTO = new ExamDTO();
        examDTO.setId(1);
        examDTO.setIdRoom(1);
        examDTO.setDate(date);
        examDTO.setTime(date);
        examDTO.setIdTeaching(1);
        /******************************************************/

        when(roomService.getDomainById(1)).thenReturn(room);
        when(teachingService.getDomainById(1)).thenReturn(teaching);
        when(examRepository.save(any(Exam.class))).thenReturn(exam);

        ExamDTO e = examService.save(examDTO);

        assertEquals(examDTO.getDate(), e.getDate());
    }
}
