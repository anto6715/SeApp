package it.unisalento.se.saw.services;

import it.unisalento.se.saw.domain.*;
import it.unisalento.se.saw.dto.TeachingDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;
import it.unisalento.se.saw.repositories.TeachingRepository;
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
public class TeachingServicesTest {

    @Mock
    TeachingRepository teachingRepository;

    @Mock
    CourseService courseService;

    @Mock
    ProfessorService professorService;

    @InjectMocks
    TeachingService teachingService;

    @Test
    public void getAllTest() {


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


        List<Teaching> teachings = new ArrayList<>();
        teachings.add(teaching);

        when(teachingRepository.findAll()).thenReturn(teachings);

        Set<TeachingDTO> teachingDTOS = teachingService.getAll();
        assertEquals("Analisi",teachingDTOS.iterator().next().getName());
    }

    @Test
    public void getByIdTest() throws TeachingNotFoundException {
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

        /*****************Teaching*************************************/
        TeachingId teachingId = new TeachingId();
        teachingId.setIdTeaching(1);
        teachingId.setCourseIdCourse(3);
        teachingId.setProfessorIdProfessor(1);
        teachingId.setProfessorUserIdUser(1);

        Teaching teaching = new Teaching();
        teaching.setName("Analisi");
        teaching.setProfessor(professor);
        teaching.setCourse(course);
        teaching.setId(teachingId);
        teaching.setCredits(10);
        teaching.setYear(1);
        /******************************************************/

        when(teachingRepository.findTeachingById_IdTeaching(1)).thenReturn(teaching);

        TeachingDTO teachingDTO = teachingService.getById(1);

        assertEquals(teaching.getId().getIdTeaching(), teachingDTO.getId());
        assertEquals(teaching.getId().equals(teachingId),true);

    }

    @Test
    public void getByIdErrorTest() throws TeachingNotFoundException {
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

        /*****************Teaching*************************************/
        TeachingId teachingId = new TeachingId();
        teachingId.setIdTeaching(1);
        teachingId.setCourseIdCourse(3);
        teachingId.setProfessorIdProfessor(1);
        teachingId.setProfessorUserIdUser(1);

        Teaching teaching = new Teaching();
        teaching.setName("Analisi");
        teaching.setProfessor(professor);
        teaching.setCourse(course);
        teaching.setId(teachingId);
        teaching.setCredits(10);
        teaching.setYear(1);
        /******************************************************/

        when(teachingRepository.findTeachingById_IdTeaching(1)).thenReturn(teaching);

        try {
            TeachingDTO teachingDTO = teachingService.getById(3);
            assertEquals(teaching.getId().getIdTeaching(), teachingDTO.getId());
            Assert.fail();
        } catch (TeachingNotFoundException e) {
            System.out.println(e.getMessage());
            assertEquals("Teaching not found", e.getMessage());
        }



    }

    @Test
    public void getDomainById() throws TeachingNotFoundException {

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

        /*****************Teaching*************************************/
        TeachingId teachingId = new TeachingId();
        teachingId.setIdTeaching(1);
        teachingId.setCourseIdCourse(3);
        teachingId.setProfessorIdProfessor(1);
        teachingId.setProfessorUserIdUser(1);

        Teaching teaching = new Teaching();
        teaching.setName("Analisi");
        teaching.setProfessor(professor);
        teaching.setCourse(course);
        teaching.setId(teachingId);
        teaching.setCredits(10);
        teaching.setYear(1);
        /******************************************************/

        when(teachingRepository.findTeachingById_IdTeaching(1)).thenReturn(teaching);
        Teaching t = teachingService.getDomainById(1);
        assertEquals(teaching,t);

    }

    @Test
    public void getDomainByErrorId() throws TeachingNotFoundException {

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

        /*****************Teaching*************************************/
        TeachingId teachingId = new TeachingId();
        teachingId.setIdTeaching(1);
        teachingId.setCourseIdCourse(3);
        teachingId.setProfessorIdProfessor(1);
        teachingId.setProfessorUserIdUser(1);

        Teaching teaching = new Teaching();
        teaching.setName("Analisi");
        teaching.setProfessor(professor);
        teaching.setCourse(course);
        teaching.setId(teachingId);
        teaching.setCredits(10);
        teaching.setYear(1);
        /******************************************************/

        when(teachingRepository.findTeachingById_IdTeaching(1)).thenReturn(teaching);
        try{
            Teaching t = teachingService.getDomainById(2);
            assertEquals(teaching,t);
        } catch (TeachingNotFoundException e) {
            assertEquals("Teaching not found", e.getMessage());
        }


    }

    @Test
    public void getByNameAndIdCourseTest() throws TeachingNotFoundException {
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

        /*****************Teaching*************************************/
        TeachingId teachingId = new TeachingId();
        teachingId.setIdTeaching(1);
        teachingId.setCourseIdCourse(3);
        teachingId.setProfessorIdProfessor(1);
        teachingId.setProfessorUserIdUser(1);

        Teaching teaching = new Teaching();
        teaching.setName("Analisi");
        teaching.setProfessor(professor);
        teaching.setCourse(course);
        teaching.setId(teachingId);
        teaching.setCredits(10);
        teaching.setYear(1);
        /******************************************************/

        when(teachingRepository.findTeachingByNameAndAndCourse_IdCourse("Analisi", 3)).thenReturn(teaching);

        TeachingDTO t = teachingService.getByNameAndIdCourse("Analisi", 3);

        assertEquals(teaching.getId().getIdTeaching(), t.getId());
    }

    @Test
    public void getByNameAndIdCourseErrorTest() throws TeachingNotFoundException {
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

        /*****************Teaching*************************************/
        TeachingId teachingId = new TeachingId();
        teachingId.setIdTeaching(1);
        teachingId.setCourseIdCourse(3);
        teachingId.setProfessorIdProfessor(1);
        teachingId.setProfessorUserIdUser(1);

        Teaching teaching = new Teaching();
        teaching.setName("Analisi");
        teaching.setProfessor(professor);
        teaching.setCourse(course);
        teaching.setId(teachingId);
        teaching.setCredits(10);
        teaching.setYear(1);
        /******************************************************/

        when(teachingRepository.findTeachingByNameAndAndCourse_IdCourse("Analisi", 3)).thenReturn(teaching);

        try{
            TeachingDTO t = teachingService.getByNameAndIdCourse("Analisi", 4);
            assertEquals(teaching.getId().getIdTeaching(), t.getId());
            Assert.fail();
        } catch (TeachingNotFoundException e) {
            assertEquals("Teaching not found", e.getMessage());
        }


    }

    @Test
    public void getByNameAndIdProfTest() throws TeachingNotFoundException {
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

        /*****************Teaching*************************************/
        TeachingId teachingId = new TeachingId();
        teachingId.setIdTeaching(1);
        teachingId.setCourseIdCourse(3);
        teachingId.setProfessorIdProfessor(1);
        teachingId.setProfessorUserIdUser(1);

        Teaching teaching = new Teaching();
        teaching.setName("Analisi");
        teaching.setProfessor(professor);
        teaching.setCourse(course);
        teaching.setId(teachingId);
        teaching.setCredits(10);
        teaching.setYear(1);
        /******************************************************/

        when(teachingRepository.findTeachingByNameAndProfessor_Id_IdProfessor("Analisi", 1)).thenReturn(teaching);

        TeachingDTO t = teachingService.getByNameAndIdProf("Analisi", 1);

        assertEquals(teaching.getId().getIdTeaching(), t.getId());
    }

    @Test
    public void getByNameAndIdProfErrorTest() throws TeachingNotFoundException {
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

        /*****************Teaching*************************************/
        TeachingId teachingId = new TeachingId();
        teachingId.setIdTeaching(1);
        teachingId.setCourseIdCourse(3);
        teachingId.setProfessorIdProfessor(1);
        teachingId.setProfessorUserIdUser(1);

        Teaching teaching = new Teaching();
        teaching.setName("Analisi");
        teaching.setProfessor(professor);
        teaching.setCourse(course);
        teaching.setId(teachingId);
        teaching.setCredits(10);
        teaching.setYear(1);
        /******************************************************/

        when(teachingRepository.findTeachingByNameAndProfessor_Id_IdProfessor("Analisi", 1)).thenReturn(teaching);

        try {
            TeachingDTO t = teachingService.getByNameAndIdProf("Analisi", 4);
            assertEquals(teaching.getId().getIdTeaching(), t.getId());
            Assert.fail();
        } catch (TeachingNotFoundException e) {
            assertEquals("Teaching not found", e.getMessage());
        }


    }

    @Test
    public void getByIdCourseTest() {


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

        /*****************Teaching*************************************/
        TeachingId teachingId = new TeachingId();
        teachingId.setIdTeaching(1);
        teachingId.setCourseIdCourse(3);
        teachingId.setProfessorIdProfessor(1);
        teachingId.setProfessorUserIdUser(1);

        Teaching teaching = new Teaching();
        teaching.setName("Analisi");
        teaching.setProfessor(professor);
        teaching.setCourse(course);
        teaching.setId(teachingId);
        teaching.setCredits(10);
        teaching.setYear(1);
        /******************************************************/


        List<Teaching> teachings = new ArrayList<>();
        teachings.add(teaching);

        when(teachingRepository.findTeachingsById_CourseIdCourse(3)).thenReturn(teachings);

        Set<TeachingDTO> teachingDTOS = teachingService.getByIdCourse(3);
        assertEquals("Analisi",teachingDTOS.iterator().next().getName());
    }

    @Test
    public void getByProfTest() {


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

        /*****************Teaching*************************************/
        TeachingId teachingId = new TeachingId();
        teachingId.setIdTeaching(1);
        teachingId.setCourseIdCourse(3);
        teachingId.setProfessorIdProfessor(1);
        teachingId.setProfessorUserIdUser(1);

        Teaching teaching = new Teaching(teachingId,course,professor);
        teaching.setName("Analisi");
        teaching.setCredits(10);
        teaching.setYear(1);
        /******************************************************/


        List<Teaching> teachings = new ArrayList<>();
        teachings.add(teaching);

        when(teachingRepository.findTeachingsById_ProfessorIdProfessor(1)).thenReturn(teachings);

        Set<TeachingDTO> teachingDTOS = teachingService.getByProf(1);
        assertEquals("Analisi",teachingDTOS.iterator().next().getName());
    }

    @Test
    public void save() throws CourseNotFoundException, ProfessorNotFoundException {

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

        /*****************Teaching*************************************/
        TeachingId teachingId = new TeachingId();
        teachingId.setIdTeaching(1);
        teachingId.setCourseIdCourse(3);
        teachingId.setProfessorIdProfessor(1);
        teachingId.setProfessorUserIdUser(1);

        Teaching teaching = new Teaching(teachingId,course,
                professor,"Antonio",10,1,null,null);

        /******************************************************/

        /***************************TeachingDTO***************************/
        TeachingDTO teachingDTO = new TeachingDTO();
        teachingDTO.setId(1);
        teachingDTO.setCredits(10);
        teachingDTO.setIdCourse(3);
        teachingDTO.setIdProfessor(1);
        teachingDTO.setName("Analisi");
        teachingDTO.setYear(1);
        /******************************************************/

        when(courseService.getDomainById(3)).thenReturn(course);
        when(professorService.getDomainById(1)).thenReturn(professor);

        when(teachingRepository.save(any(Teaching.class))).thenReturn(teaching);

        TeachingDTO t = teachingService.save(teachingDTO);
        assertEquals(t.getIdCourse(), teachingDTO.getIdCourse());



    }
}
