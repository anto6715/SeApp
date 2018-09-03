package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.IProfessorServices;
import it.unisalento.se.saw.dto.ProfessorDTO;
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
public class ProfessorRestControllerTest {

    public static final MediaType APPLICATION_JSON_UTF = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    private MockMvc mockMvc;

    @Mock
    IProfessorServices professorServicesMock;

    @InjectMocks
    ProfessorRestController professorRestController;


    @Before
    public void setUp(){
        mockMvc = Tools.getMockMvc(professorRestController);
    }

    @Test
    public void getAllTest() throws Exception {
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setCourse(1);
        professorDTO.setIdUser(2);
        professorDTO.setUserType(3);
        professorDTO.setAge(4);
        professorDTO.setEmail("email");
        professorDTO.setName("name");
        professorDTO.setSurname("surname");
        professorDTO.setToken("token");
        professorDTO.setUid("uid");
        professorDTO.setId(1);
        Set<ProfessorDTO> professorDTOS = new HashSet<>(0);
        professorDTOS.add(professorDTO);

        when(professorServicesMock.getAll()).thenReturn(professorDTOS);

        mockMvc.perform(get("/professor/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].course", is(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].idUser", is(2)))
                .andExpect(jsonPath("$[0].userType", is(3)))
                .andExpect(jsonPath("$[0].age", is(4)))
                .andExpect(jsonPath("$[0].email", is("email")))
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].surname", is("surname")))
                .andExpect(jsonPath("$[0].token", is("token")))
                .andExpect(jsonPath("$[0].uid", is("uid")));

        verify(professorServicesMock, times(1)).getAll();
        verifyNoMoreInteractions(professorServicesMock);
    }

    @Test
    public void getByIdCourseTest() throws Exception {
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setCourse(1);
        professorDTO.setIdUser(2);
        professorDTO.setUserType(3);
        professorDTO.setAge(4);
        professorDTO.setId(1);
        professorDTO.setEmail("email");
        professorDTO.setName("name");
        professorDTO.setSurname("surname");
        professorDTO.setToken("token");
        professorDTO.setUid("uid");
        Set<ProfessorDTO> professorDTOS = new HashSet<>(0);
        professorDTOS.add(professorDTO);

        when(professorServicesMock.getByIdCourse(1)).thenReturn(professorDTOS);

        mockMvc.perform(get("/professor/getByCourse/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].course", is(1)))
                .andExpect(jsonPath("$[0].idUser", is(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].userType", is(3)))
                .andExpect(jsonPath("$[0].age", is(4)))
                .andExpect(jsonPath("$[0].email", is("email")))
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].surname", is("surname")))
                .andExpect(jsonPath("$[0].token", is("token")))
                .andExpect(jsonPath("$[0].uid", is("uid")));

        verify(professorServicesMock, times(1)).getByIdCourse(1);
        verifyNoMoreInteractions(professorServicesMock);
    }

    @Test
    public void getByIdTest() throws Exception {
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setCourse(1);
        professorDTO.setIdUser(2);
        professorDTO.setUserType(3);
        professorDTO.setAge(4);
        professorDTO.setEmail("email");
        professorDTO.setName("name");
        professorDTO.setSurname("surname");
        professorDTO.setToken("token");
        professorDTO.setUid("uid");
        professorDTO.setId(1);

        when(professorServicesMock.getById(1)).thenReturn(professorDTO);

        mockMvc.perform(get("/professor/getById/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.course", is(1)))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.idUser", is(2)))
                .andExpect(jsonPath("$.userType", is(3)))
                .andExpect(jsonPath("$.age", is(4)))
                .andExpect(jsonPath("$.email", is("email")))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.surname", is("surname")))
                .andExpect(jsonPath("$.token", is("token")))
                .andExpect(jsonPath("$.uid", is("uid")));

        verify(professorServicesMock, times(1)).getById(1);
        verifyNoMoreInteractions(professorServicesMock);
    }

    @Test
    public void saveTest() throws Exception {
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setCourse(1);
        professorDTO.setIdUser(2);
        professorDTO.setUserType(3);
        professorDTO.setAge(4);
        professorDTO.setEmail("email");
        professorDTO.setName("name");
        professorDTO.setSurname("surname");
        professorDTO.setToken("token");
        professorDTO.setUid("uid");
        professorDTO.setId(1);

        String json = Tools.getJson(professorDTO);

        when(professorServicesMock.save(any(ProfessorDTO.class))).thenReturn(professorDTO);

        mockMvc.perform(post("/professor/save")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.course", is(1)))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.idUser", is(2)))
                .andExpect(jsonPath("$.userType", is(3)))
                .andExpect(jsonPath("$.age", is(4)))
                .andExpect(jsonPath("$.email", is("email")))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.surname", is("surname")))
                .andExpect(jsonPath("$.token", is("token")))
                .andExpect(jsonPath("$.uid", is("uid")));

        verify(professorServicesMock, times(1)).save(refEq(professorDTO));
        verifyNoMoreInteractions(professorServicesMock);
    }
}
