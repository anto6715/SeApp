package it.unisalento.se.saw.restapi;
;
import it.unisalento.se.saw.Iservices.ICourseServices;
import it.unisalento.se.saw.Iservices.IProfessorServices;
import it.unisalento.se.saw.dto.CourseDTO;
import it.unisalento.se.saw.tools.Tools;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CourseRestControllerTest {
    public static final MediaType APPLICATION_JSON_UTF = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    private MockMvc mockMvc;

    @Mock
    ICourseServices courseServicesMock;

    @Mock
    IProfessorServices professorServicesMock;

    @InjectMocks
    CourseRestController courseRestController;

    @Before
    public void setUp(){
        mockMvc = Tools.getMockMvc(courseRestController);
    }

    @Test
    public void getAllTest() throws Exception{
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(1);
        courseDTO.setLenght(2);
        courseDTO.setType("Triennale");
        courseDTO.setCredits(3);
        courseDTO.setLocation("Lecce");
        courseDTO.setLanguage("English");
        courseDTO.setName("name");
        Set<CourseDTO> courseDTOS = new HashSet<>(0);
        courseDTOS.add(courseDTO);

        when(courseServicesMock.getAll()).thenReturn(courseDTOS);

        mockMvc.perform(get("/course/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].lenght", is(2)))
                .andExpect(jsonPath("$[0].type", is("Triennale")))
                .andExpect(jsonPath("$[0].credits", is(3)))
                .andExpect(jsonPath("$[0].location", is("Lecce")))
                .andExpect(jsonPath("$[0].language", is("English")))
                .andExpect(jsonPath("$[0].name", is("name")));

        verify(courseServicesMock, times(1)).getAll();
        verifyNoMoreInteractions(courseServicesMock);
    }

    @Test
    public void getByIdTest() throws Exception {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(1);
        courseDTO.setLenght(2);
        courseDTO.setType("Triennale");
        courseDTO.setCredits(3);
        courseDTO.setLocation("Lecce");
        courseDTO.setLanguage("English");
        courseDTO.setName("name");

        when(courseServicesMock.getById(1)).thenReturn(courseDTO);

        mockMvc.perform(get("/course/getById/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.lenght", is(2)))
                .andExpect(jsonPath("$.type", is("Triennale")))
                .andExpect(jsonPath("$.credits", is(3)))
                .andExpect(jsonPath("$.location", is("Lecce")))
                .andExpect(jsonPath("$.language", is("English")))
                .andExpect(jsonPath("$.name", is("name")));

        verify(courseServicesMock, times(1)).getById(1);
        verifyNoMoreInteractions(courseServicesMock);
    }

    @Test
    public void saveTest() throws Exception {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(1);
        courseDTO.setLenght(2);
        courseDTO.setType("Triennale");
        courseDTO.setCredits(3);
        courseDTO.setLocation("Lecce");
        courseDTO.setLanguage("English");
        courseDTO.setName("name");
        String json = Tools.getJson(courseDTO);

        when(courseServicesMock.save(any(CourseDTO.class))).thenReturn(courseDTO);

        mockMvc.perform(post("/course/save",1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.lenght", is(2)))
                .andExpect(jsonPath("$.type", is("Triennale")))
                .andExpect(jsonPath("$.credits", is(3)))
                .andExpect(jsonPath("$.location", is("Lecce")))
                .andExpect(jsonPath("$.language", is("English")))
                .andExpect(jsonPath("$.name", is("name")));

        verify(courseServicesMock, times(1)).save(refEq(courseDTO));
        verifyNoMoreInteractions(courseServicesMock);
    }
}
