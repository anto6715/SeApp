package it.unisalento.se.saw.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisalento.se.saw.Iservices.IStudentServices;
import it.unisalento.se.saw.dto.StudentDTO;
import it.unisalento.se.saw.tools.Tools;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class StudentRestControllerTest {

    public static final MediaType APPLICATION_JSON_UTF = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    private MockMvc mockMvc;

    @Mock
    IStudentServices studentServicesMock;

    @InjectMocks
    StudentRestController studentRestController;

    @Before
    public void setUp(){
        mockMvc = Tools.getMockMvc(studentRestController);
    }

    @Test
    public void getAllTest() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setCourseDTO(null);
        studentDTO.setIdCourse(1);
        studentDTO.setYearStart(2);
        studentDTO.setId(3);
        studentDTO.setMatricola("matricola");
        studentDTO.setYear(4);
        studentDTO.setUid("uid");
        Set<StudentDTO> studentDTOS = new HashSet<>(0);
        studentDTOS.add(studentDTO);

        when(studentServicesMock.getAll()).thenReturn(studentDTOS);

        mockMvc.perform(get("/student/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].courseDTO", is(nullValue())))
                .andExpect(jsonPath("$[0].idCourse", is(1)))
                .andExpect(jsonPath("$[0].yearStart", is(2)))
                .andExpect(jsonPath("$[0].id", is(3)))
                .andExpect(jsonPath("$[0].matricola", is("matricola")))
                .andExpect(jsonPath("$[0].year", is(4)))
                .andExpect(jsonPath("$[0].uid", is("uid")));

        verify(studentServicesMock, times(1)).getAll();
        verifyNoMoreInteractions(studentServicesMock);
    }

    @Test
    public void getByCourseTest() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setCourseDTO(null);
        studentDTO.setIdCourse(1);
        studentDTO.setYearStart(2);
        studentDTO.setId(3);
        studentDTO.setMatricola("matricola");
        studentDTO.setYear(4);
        studentDTO.setUid("uid");
        Set<StudentDTO> studentDTOS = new HashSet<>(0);
        studentDTOS.add(studentDTO);

        when(studentServicesMock.getByCourse(1)).thenReturn(studentDTOS);

        mockMvc.perform(get("/student/getByCourse/{idCourse}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].courseDTO", is(nullValue())))
                .andExpect(jsonPath("$[0].idCourse", is(1)))
                .andExpect(jsonPath("$[0].yearStart", is(2)))
                .andExpect(jsonPath("$[0].id", is(3)))
                .andExpect(jsonPath("$[0].matricola", is("matricola")))
                .andExpect(jsonPath("$[0].year", is(4)))
                .andExpect(jsonPath("$[0].uid", is("uid")));

        verify(studentServicesMock, times(1)).getByCourse(1);
        verifyNoMoreInteractions(studentServicesMock);
    }

    @Test
    public void getByIdTest() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setCourseDTO(null);
        studentDTO.setIdCourse(1);
        studentDTO.setYearStart(2);
        studentDTO.setId(3);
        studentDTO.setMatricola("matricola");
        studentDTO.setYear(4);
        studentDTO.setUid("uid");

        when(studentServicesMock.getById(3)).thenReturn(studentDTO);

        mockMvc.perform(get("/student/getById/{id}",3))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.courseDTO", is(nullValue())))
                .andExpect(jsonPath("$.idCourse", is(1)))
                .andExpect(jsonPath("$.yearStart", is(2)))
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.matricola", is("matricola")))
                .andExpect(jsonPath("$.year", is(4)))
                .andExpect(jsonPath("$.uid", is("uid")));

        verify(studentServicesMock, times(1)).getById(3);
        verifyNoMoreInteractions(studentServicesMock);
    }

    @Test
    public void getByUidTest() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setCourseDTO(null);
        studentDTO.setIdCourse(1);
        studentDTO.setYearStart(2);
        studentDTO.setId(3);
        studentDTO.setMatricola("matricola");
        studentDTO.setYear(4);
        studentDTO.setUid("uid");

        when(studentServicesMock.getByUid("uid")).thenReturn(studentDTO);

        mockMvc.perform(get("/student/getByUid/{uid}","uid"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.courseDTO", is(nullValue())))
                .andExpect(jsonPath("$.idCourse", is(1)))
                .andExpect(jsonPath("$.yearStart", is(2)))
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.matricola", is("matricola")))
                .andExpect(jsonPath("$.year", is(4)))
                .andExpect(jsonPath("$.uid", is("uid")));

        verify(studentServicesMock, times(1)).getByUid("uid");
        verifyNoMoreInteractions(studentServicesMock);
    }

    @Test
    public void saveTest() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setCourseDTO(null);
        studentDTO.setIdCourse(1);
        studentDTO.setYearStart(2);
        studentDTO.setId(3);
        studentDTO.setMatricola("matricola");
        studentDTO.setYear(4);
        studentDTO.setUid("uid");
        String json = Tools.getJson(studentDTO);

        when(studentServicesMock.save(any(StudentDTO.class))).thenReturn(studentDTO);

        mockMvc.perform(post("/student/save")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.courseDTO", is(nullValue())))
                .andExpect(jsonPath("$.idCourse", is(1)))
                .andExpect(jsonPath("$.yearStart", is(2)))
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.matricola", is("matricola")))
                .andExpect(jsonPath("$.year", is(4)))
                .andExpect(jsonPath("$.uid", is("uid")));

        verify(studentServicesMock, times(1)).save(refEq(studentDTO));
        verifyNoMoreInteractions(studentServicesMock);
    }


}
