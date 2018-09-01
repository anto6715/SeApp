package it.unisalento.se.saw.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisalento.se.saw.Iservices.ITeachingServices;
import it.unisalento.se.saw.dto.TeachingDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TeachingRestControllerTest {

    public static final MediaType APPLICATION_JSON_UTF = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    private MockMvc mockMvc;

    @Mock
    ITeachingServices teachingServicesMock;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(new TeachingRestController(teachingServicesMock))
                .setViewResolvers(viewResolver())
                .build();
    }

    @Test
    public void getById() throws Exception {

        TeachingDTO teachingDTO = new TeachingDTO();
        teachingDTO.setCourseDTO(null);
        teachingDTO.setProfessorDTO(null);
        teachingDTO.setYear(1);
        teachingDTO.setName("name");
        teachingDTO.setIdProfessor(1);
        teachingDTO.setIdCourse(3);
        teachingDTO.setCredits(2);
        teachingDTO.setId(10);

        when(teachingServicesMock.getById(1)).thenReturn(teachingDTO);
        mockMvc.perform(get("/teaching/getById/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.idProfessor", is(1)))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.credits", is(2)))
                .andExpect(jsonPath("$.idCourse", is(3)))
                .andExpect(jsonPath("$.year", is(1)))
                .andExpect(jsonPath("$.professorDTO", is(nullValue())))
                .andExpect(jsonPath("$.courseDTO", is(nullValue())));

        verify(teachingServicesMock, times(1)).getById(1);
        verifyNoMoreInteractions(teachingServicesMock);
    }

    @Test
    public void getByNameAndIdCourse() throws Exception {

        TeachingDTO teachingDTO = new TeachingDTO();
        teachingDTO.setCourseDTO(null);
        teachingDTO.setProfessorDTO(null);
        teachingDTO.setYear(1);
        teachingDTO.setName("name");
        teachingDTO.setIdProfessor(1);
        teachingDTO.setIdCourse(3);
        teachingDTO.setCredits(2);
        teachingDTO.setId(10);

        when(teachingServicesMock.getByNameAndIdCourse("name",3)).thenReturn(teachingDTO);
        mockMvc.perform(get("/teaching/getByNameAndIdCourse/{name}_{idCourse}","name",3)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.idProfessor", is(1)))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.credits", is(2)))
                .andExpect(jsonPath("$.idCourse", is(3)))
                .andExpect(jsonPath("$.year", is(1)))
                .andExpect(jsonPath("$.professorDTO", is(nullValue())))
                .andExpect(jsonPath("$.courseDTO", is(nullValue())));

        verify(teachingServicesMock, times(1)).getByNameAndIdCourse("name",3);
        verifyNoMoreInteractions(teachingServicesMock);
    }

    @Test
    public void getByNameAndIdProf() throws Exception {

        TeachingDTO teachingDTO = new TeachingDTO();
        teachingDTO.setCourseDTO(null);
        teachingDTO.setProfessorDTO(null);
        teachingDTO.setYear(1);
        teachingDTO.setName("name");
        teachingDTO.setIdProfessor(1);
        teachingDTO.setIdCourse(3);
        teachingDTO.setCredits(2);
        teachingDTO.setId(10);

        when(teachingServicesMock.getByNameAndIdProf("name",1)).thenReturn(teachingDTO);
        mockMvc.perform(get("/teaching/getByNameAndIdProf/{name}_{idProf}","name",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.idProfessor", is(1)))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.credits", is(2)))
                .andExpect(jsonPath("$.idCourse", is(3)))
                .andExpect(jsonPath("$.year", is(1)))
                .andExpect(jsonPath("$.professorDTO", is(nullValue())))
                .andExpect(jsonPath("$.courseDTO", is(nullValue())));

        verify(teachingServicesMock, times(1)).getByNameAndIdProf("name",1);
        verifyNoMoreInteractions(teachingServicesMock);
    }

    @Test
    public void save() throws Exception {

        TeachingDTO teachingDTO = new TeachingDTO();
        teachingDTO.setCourseDTO(null);
        teachingDTO.setProfessorDTO(null);
        teachingDTO.setYear(1);
        teachingDTO.setName("name");
        teachingDTO.setIdProfessor(1);
        teachingDTO.setIdCourse(3);
        teachingDTO.setCredits(2);
        teachingDTO.setId(10);

        String json = new ObjectMapper().writeValueAsString(teachingDTO);

        when(teachingServicesMock.save(any(TeachingDTO.class))).thenReturn(teachingDTO);
        mockMvc.perform(post("/teaching/save")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.idProfessor", is(1)))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.credits", is(2)))
                .andExpect(jsonPath("$.idCourse", is(3)))
                .andExpect(jsonPath("$.year", is(1)))
                .andExpect(jsonPath("$.professorDTO", is(nullValue())))
                .andExpect(jsonPath("$.courseDTO", is(nullValue())));

        verify(teachingServicesMock, times(1)).save(refEq(teachingDTO));
        verifyNoMoreInteractions(teachingServicesMock);
    }

    @Test
    public void getAll() throws Exception {
        TeachingDTO teachingDTO = new TeachingDTO();
        teachingDTO.setCourseDTO(null);
        teachingDTO.setProfessorDTO(null);
        teachingDTO.setYear(1);
        teachingDTO.setName("name");
        teachingDTO.setIdProfessor(1);
        teachingDTO.setIdCourse(3);
        teachingDTO.setCredits(2);
        teachingDTO.setId(10);

        Set<TeachingDTO> teachingDTOS = new HashSet<>(0);
        teachingDTOS.add(teachingDTO);

        String json = new ObjectMapper().writeValueAsString(teachingDTO);

        when(teachingServicesMock.getAll()).thenReturn(teachingDTOS);
        mockMvc.perform(get("/teaching/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(10)))
                .andExpect(jsonPath("$[0].idProfessor", is(1)))
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].credits", is(2)))
                .andExpect(jsonPath("$[0].idCourse", is(3)))
                .andExpect(jsonPath("$[0].year", is(1)))
                .andExpect(jsonPath("$[0].professorDTO", is(nullValue())))
                .andExpect(jsonPath("$[0].courseDTO", is(nullValue())));

        verify(teachingServicesMock, times(1)).getAll();
        verifyNoMoreInteractions(teachingServicesMock);
    }

    @Test
    public void getAllByIdCourse() throws Exception {
        TeachingDTO teachingDTO = new TeachingDTO();
        teachingDTO.setCourseDTO(null);
        teachingDTO.setProfessorDTO(null);
        teachingDTO.setYear(1);
        teachingDTO.setName("name");
        teachingDTO.setIdProfessor(1);
        teachingDTO.setIdCourse(3);
        teachingDTO.setCredits(2);
        teachingDTO.setId(10);

        Set<TeachingDTO> teachingDTOS = new HashSet<>(0);
        teachingDTOS.add(teachingDTO);

        String json = new ObjectMapper().writeValueAsString(teachingDTO);

        when(teachingServicesMock.getByIdCourse(3)).thenReturn(teachingDTOS);
        mockMvc.perform(get("/teaching/getByIdCourse/{id}",3))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(10)))
                .andExpect(jsonPath("$[0].idProfessor", is(1)))
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].credits", is(2)))
                .andExpect(jsonPath("$[0].idCourse", is(3)))
                .andExpect(jsonPath("$[0].year", is(1)))
                .andExpect(jsonPath("$[0].professorDTO", is(nullValue())))
                .andExpect(jsonPath("$[0].courseDTO", is(nullValue())));

        verify(teachingServicesMock, times(1)).getByIdCourse(3);
        verifyNoMoreInteractions(teachingServicesMock);
    }

    @Test
    public void getAllByIdProf() throws Exception {
        TeachingDTO teachingDTO = new TeachingDTO();
        teachingDTO.setCourseDTO(null);
        teachingDTO.setProfessorDTO(null);
        teachingDTO.setYear(1);
        teachingDTO.setName("name");
        teachingDTO.setIdProfessor(1);
        teachingDTO.setIdCourse(3);
        teachingDTO.setCredits(2);
        teachingDTO.setId(10);

        Set<TeachingDTO> teachingDTOS = new HashSet<>(0);
        teachingDTOS.add(teachingDTO);

        String json = new ObjectMapper().writeValueAsString(teachingDTO);

        when(teachingServicesMock.getByProf(1)).thenReturn(teachingDTOS);
        mockMvc.perform(get("/teaching/getByIdProf/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(10)))
                .andExpect(jsonPath("$[0].idProfessor", is(1)))
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].credits", is(2)))
                .andExpect(jsonPath("$[0].idCourse", is(3)))
                .andExpect(jsonPath("$[0].year", is(1)))
                .andExpect(jsonPath("$[0].professorDTO", is(nullValue())))
                .andExpect(jsonPath("$[0].courseDTO", is(nullValue())));

        verify(teachingServicesMock, times(1)).getByProf(1);
        verifyNoMoreInteractions(teachingServicesMock);
    }


    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
