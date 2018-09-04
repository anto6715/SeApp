package it.unisalento.se.saw.services;

import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.dto.CourseDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.ExamNotFoundException;
import it.unisalento.se.saw.repositories.CourseRepository;
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
public class CourseServiceTest {

    @Mock
    CourseRepository courseRepository;

    @InjectMocks
    CourseService courseService;

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

        List<Course> courses = new ArrayList<>();
        courses.add(course);

        when(courseRepository.findAll()).thenReturn(courses);

        Set<CourseDTO> courseDTOS = courseService.getAll();
        assertEquals(course.getIdCourse(), (Integer) courseDTOS.iterator().next().getId());
    }

    @Test
    public void getByIdTest() throws CourseNotFoundException {
        /***************************Course***************************/
        Course course = new Course("Computer","type",1,10,"location","language", null,null,null);
        course.setIdCourse(3);
        /******************************************************/

        when(courseRepository.getOne(1)).thenReturn(course);

        CourseDTO courseDTO = courseService.getById(1);
        assertEquals(course.getCredits(), (Integer) courseDTO.getCredits());
    }

    @Test
    public void getByIdErrorTest() throws CourseNotFoundException {
        /***************************Course***************************/
        Course course = new Course("Computer","type",1,10,"location","language", null,null,null);
        course.setIdCourse(3);
        /******************************************************/

        when(courseRepository.getOne(1)).thenReturn(course);

        try {
            CourseDTO courseDTO = courseService.getById(3);
            assertEquals(course.getCredits(), (Integer) courseDTO.getCredits());
        } catch (CourseNotFoundException e) {
            assertEquals("Course not found", e.getMessage());
        }

    }

    @Test
    public void getDomainByIdTest() throws CourseNotFoundException {
        /***************************Course***************************/
        Course course = new Course("Computer","type",1,10,"location","language", null,null,null);
        course.setIdCourse(3);
        /******************************************************/

        when(courseRepository.getOne(1)).thenReturn(course);

        Course c = courseService.getDomainById(1);
        assertEquals(course,c);
    }

    @Test
    public void getByNameTest() throws CourseNotFoundException {
        /***************************Course***************************/
        Course course = new Course("Computer","type",1,10,"location","language", null,null,null);
        course.setIdCourse(3);
        /******************************************************/

        when(courseRepository.getCourseByName("Computer")).thenReturn(course);

        CourseDTO courseDTO = courseService.getByName("Computer");
        assertEquals(course.getName(), courseDTO.getName());
    }

    @Test
    public void getByNameErrorTest() throws CourseNotFoundException {
        /***************************Course***************************/
        Course course = new Course("Computer","type",1,10,"location","language", null,null,null);
        course.setIdCourse(3);
        /******************************************************/

        when(courseRepository.getCourseByName("Computer")).thenReturn(course);

        try {
            CourseDTO courseDTO = courseService.getByName("Computerr");
            assertEquals(course.getName(), courseDTO.getName());
        } catch (CourseNotFoundException e) {
            assertEquals("Course not found", e.getMessage());
        }

    }

    @Test
    public void saveTest() throws CourseNotFoundException {
        /***************************Course***************************/
        Course course = new Course("Computer","type",1,10,"location","language", null,null,null);
        course.setIdCourse(3);
        /******************************************************/

        /***************************CourseDTO***************************/
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName("Computer");
        courseDTO.setLanguage("language");
        courseDTO.setLocation("location");
        courseDTO.setCredits(10);
        courseDTO.setId(1);
        /******************************************************/

        when(courseRepository.save(any(Course.class))).thenReturn(course);

        CourseDTO c = courseService.save(courseDTO);
        assertEquals(courseDTO.getName(), c.getName());
    }
}
