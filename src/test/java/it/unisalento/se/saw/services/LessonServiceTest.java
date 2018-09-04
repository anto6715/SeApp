package it.unisalento.se.saw.services;

import it.unisalento.se.saw.domain.*;
import it.unisalento.se.saw.dto.LessonDTO;
import it.unisalento.se.saw.exceptions.LessonNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;
import it.unisalento.se.saw.repositories.LessonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LessonServiceTest {

    @Mock
    LessonRepository lessonRepository;

    @Mock
    TeachingService teachingService;

    @Mock
    RoomService roomService;

    @InjectMocks
    LessonService lessonService;

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

        /****************************Room**************************/
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");
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

        /**************************Lesson****************************/
        LessonId lessonId = new LessonId(1,1,3,1,1,1);

        Lesson lesson = new Lesson(lessonId, room, teaching);
        /******************************************************/

        List<Lesson> lessons = new ArrayList<>();
        lessons.add(lesson);

        when(lessonRepository.findAll()).thenReturn(lessons);

        Set<LessonDTO> lessonDTOS = lessonService.getAll();

        assertEquals(lesson.getId().getIdLesson(), lessonDTOS.iterator().next().getId());
    }

    @Test
    public void getByDateTest() {

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

        /****************************Room**************************/
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");
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

        /**************************Lesson****************************/
        LessonId lessonId = new LessonId(1,1,3,1,1,1);

        Lesson lesson = new Lesson(lessonId, room, teaching);
        /******************************************************/

        List<Lesson> lessons = new ArrayList<>();
        lessons.add(lesson);
        Date date = new Date();
        when(lessonRepository.findLessonsByDateAndId_TeachingCourseIdCourse(date,3)).thenReturn(lessons);

        Set<LessonDTO> lessonDTOS = lessonService.getByDate(date,3);

        assertEquals(lesson.getId().getRoomIdRoom(), lessonDTOS.iterator().next().getIdRoom());
    }

    @Test
    public void getByDateAndIdProfessorTest() {

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

        /****************************Room**************************/
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");
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

        /**************************Lesson****************************/
        LessonId lessonId = new LessonId(1,1,3,1,1,1);

        Lesson lesson = new Lesson(lessonId, room, teaching);
        /******************************************************/

        List<Lesson> lessons = new ArrayList<>();
        lessons.add(lesson);
        Date date = new Date();
        when(lessonRepository.findLessonsByDateAndId_TeachingProfessorIdProfessor(date,1)).thenReturn(lessons);

        Set<LessonDTO> lessonDTOS = lessonService.getByDateAndIdProfessor(date,1);

        assertEquals(lesson.getId().getTeachingIdTeaching(), lessonDTOS.iterator().next().getIdTeaching());
    }

    @Test
    public void getByRoomTest() {

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

        /****************************Room**************************/
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");
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

        /**************************Lesson****************************/
        LessonId lessonId = new LessonId(1,1,3,1,1,1);

        Lesson lesson = new Lesson(lessonId, room, teaching);
        /******************************************************/

        List<Lesson> lessons = new ArrayList<>();
        lessons.add(lesson);

        when(lessonRepository.findLessonsById_RoomIdRoom(1)).thenReturn(lessons);

        Set<LessonDTO> lessonDTOS = lessonService.getByRoom(1);

        assertEquals(lesson.getDate(), lessonDTOS.iterator().next().getDate());
    }

    @Test
    public void getByIdTest() throws LessonNotFoundException {

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

        /****************************Room**************************/
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");
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

        /**************************Lesson****************************/
        LessonId lessonId = new LessonId(1,1,3,1,1,1);

        Lesson lesson = new Lesson(lessonId, room, teaching);
        /******************************************************/

        when(lessonRepository.findLessonById_IdLesson(1)).thenReturn(lesson);

        LessonDTO lessonDTO = lessonService.getById(1);

        assertEquals(lesson.getTeaching().getId().getIdTeaching(), lessonDTO.getIdTeaching());
    }

    @Test
    public void getByIdErrorTest() throws LessonNotFoundException {

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

        /****************************Room**************************/
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");
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

        /**************************Lesson****************************/
        LessonId lessonId = new LessonId(1,1,3,1,1,1);

        Lesson lesson = new Lesson(lessonId, room, teaching);
        /******************************************************/

        when(lessonRepository.findLessonById_IdLesson(1)).thenReturn(lesson);

        try {
            LessonDTO lessonDTO = lessonService.getById(2);
            assertEquals(lesson.getTeaching().getId().getIdTeaching(), lessonDTO.getIdTeaching());
        } catch (LessonNotFoundException e) {
            assertEquals("Lesson not found", e.getMessage());
        }


    }

    @Test
    public void saveTest() throws LessonNotFoundException, RoomNotFoundException, TeachingNotFoundException {

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

        /****************************Room**************************/
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");
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

        /**************************Lesson****************************/
        LessonId lessonId = new LessonId(1,1,3,1,1,1);

        Lesson lesson = new Lesson(lessonId, room, teaching,new Date(),new Date(),new Date(),null,null);
        /******************************************************/

        /**************************LessonDTO****************************/
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setId(1);
        lessonDTO.setDate(new Date());
        lessonDTO.setIdRoom(1);
        lessonDTO.setIdTeaching(1);
        /******************************************************/

        when(roomService.getDomainById(1)).thenReturn(room);
        when(teachingService.getDomainById(1)).thenReturn(teaching);
        when(lessonRepository.save(any(Lesson.class))).thenReturn(lesson);

        LessonDTO l = lessonService.save(lessonDTO);

        assertEquals(l.getId(), lessonDTO.getId());
    }
}
