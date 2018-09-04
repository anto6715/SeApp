package it.unisalento.se.saw.services;

import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.domain.Professor;
import it.unisalento.se.saw.domain.ProfessorId;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.ProfessorDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.repositories.ProfessorRepository;
import it.unisalento.se.saw.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProfessorServiceTest {

    @Mock
    ProfessorRepository professorRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    CourseService courseService;

    @InjectMocks
    ProfessorService professorService;

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
        ProfessorId professorId = new ProfessorId(1,1);

        Professor professor = new Professor(professorId,user,null,null,null);
        /******************************************************/

        List<Professor> professors = new ArrayList<>();
        professors.add(professor);

        when(professorRepository.findAll()).thenReturn(professors);

        Set<ProfessorDTO> professorDTOS = professorService.getAll();
        assertEquals(professor.getId().getUserIdUser(), professorDTOS.iterator().next().getId());
    }

    @Test
    public void getByIdCourseTest() throws CourseNotFoundException, ProfessorNotFoundException {
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
        ProfessorId professorId = new ProfessorId(1,1);

        Professor professor = new Professor(professorId,user);
        /******************************************************/

        List<Professor> professors = new ArrayList<>();
        professors.add(professor);
        course.setProfessors(new HashSet<>(professors));

        when(courseService.getDomainById(3)).thenReturn(course);

        Set<ProfessorDTO> professorDTOS = professorService.getByIdCourse(3);
        assertEquals(professor.getId().getUserIdUser(), professorDTOS.iterator().next().getId());
    }

    @Test
    public void getByIdTest() throws ProfessorNotFoundException {
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
        ProfessorId professorId = new ProfessorId(1,1);

        Professor professor = new Professor(professorId,user,null,null,null);
        /******************************************************/

        when(professorRepository.findProfessorById_IdProfessor(1)).thenReturn(professor);

        ProfessorDTO professorDTO = professorService.getById(1);
        assertEquals(professor.getId().getIdProfessor(), professorDTO.getId());
    }

    @Test
    public void getByIdErrorTest() throws ProfessorNotFoundException {
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
        ProfessorId professorId = new ProfessorId(1,1);

        Professor professor = new Professor(professorId,user,null,null,null);
        /******************************************************/

        when(professorRepository.findProfessorById_IdProfessor(1)).thenReturn(professor);

        try{
            ProfessorDTO professorDTO = professorService.getById(3);
            assertEquals(professor.getId().getIdProfessor(), professorDTO.getId());
        } catch (ProfessorNotFoundException e) {
            assertEquals("Professor not found",e.getMessage());
        }

    }

    @Test
    public void getDomainByIdTest() throws ProfessorNotFoundException {
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
        ProfessorId professorId = new ProfessorId(1,1);

        Professor professor = new Professor(professorId,user,null,null,null);
        /******************************************************/

        when(professorRepository.findProfessorById_IdProfessor(1)).thenReturn(professor);

        Professor p = professorService.getDomainById(1);
        assertEquals(professor, p);
    }

    @Test
    public void getByUidTest() throws ProfessorNotFoundException {
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
        ProfessorId professorId = new ProfessorId(1,1);

        Professor professor = new Professor(professorId,user,null,null,null);
        /******************************************************/

        when(professorRepository.findProfessorByUserUid("uid")).thenReturn(professor);

        ProfessorDTO professorDTO = professorService.getByUid("uid");
        assertEquals(professor.getId().getIdProfessor(), professorDTO.getId());
    }

    @Test
    public void getByUidErrorTest() throws ProfessorNotFoundException {
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
        ProfessorId professorId = new ProfessorId(1,1);

        Professor professor = new Professor(professorId,user,null,null,null);
        /******************************************************/

        when(professorRepository.findProfessorByUserUid("uid")).thenReturn(professor);

        try{
            ProfessorDTO professorDTO = professorService.getByUid("uidsfafas");
            assertEquals(professor.getId().getIdProfessor(), professorDTO.getId());
        } catch (ProfessorNotFoundException e) {
            assertEquals("Professor not found", e.getMessage());
        }

    }

    @Test
    public void saveTest() throws ProfessorNotFoundException, CourseNotFoundException {
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
        ProfessorId professorId = new ProfessorId(1,1);

        Professor professor = new Professor(professorId,user);
        /******************************************************/

        /*************************ProfessorDTO*****************************/
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(1);
        professorDTO.setUid("uid");
        professorDTO.setCourse(3);
        /******************************************************/

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(courseService.getDomainById(3)).thenReturn(course);
        when(professorRepository.findProfessorById_UserIdUser(1)).thenReturn(professor);

        when(professorRepository.save(any(Professor.class))).thenReturn(professor);
        when(professorRepository.saveAndFlush(any(Professor.class))).thenReturn(professor);

        ProfessorDTO p = professorService.save(professorDTO);
        assertEquals(professorDTO.getCourse(),professorDTO.getCourse());
    }
}
