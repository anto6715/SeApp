package it.unisalento.se.saw.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisalento.se.saw.Iservices.ISegnalationServices;
import it.unisalento.se.saw.domain.Segnalation;
import it.unisalento.se.saw.dto.SegnalationDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class SegnalationRestControllerTest {

    public static final MediaType APPLICATION_JSON_UTF = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    private MockMvc mockMvc;

    @Mock
    ISegnalationServices segnalationServicesMock;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(new SegnalationRestController(segnalationServicesMock))
                .setViewResolvers(viewResolver())
                .build();
    }

    @Test
    public void getAllTest() throws Exception {
        SegnalationDTO segnalationDTO = new SegnalationDTO();
        segnalationDTO.setNote("note");
        segnalationDTO.setRoomDTO(null);
        segnalationDTO.setProfessorDTO(null);
        segnalationDTO.setIdState(1);
        segnalationDTO.setDescription("description");
        segnalationDTO.setId(2);

        Set<SegnalationDTO> segnalationDTOS = new HashSet<>(0);
        segnalationDTOS.add(segnalationDTO);

        when(segnalationServicesMock.getAll()).thenReturn(segnalationDTOS);

        mockMvc.perform(get("/segnalation/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].note", is("note")))
                .andExpect(jsonPath("$[0].roomDTO", is(nullValue())))
                .andExpect(jsonPath("$[0].professorDTO", is(nullValue())))
                .andExpect(jsonPath("$[0].idState", is(1)))
                .andExpect(jsonPath("$[0].description", is("description")));

        verify(segnalationServicesMock, times(1)).getAll();
        verifyNoMoreInteractions(segnalationServicesMock);
    }

    @Test
    public void getByRoomTest() throws Exception {
        SegnalationDTO segnalationDTO = new SegnalationDTO();
        segnalationDTO.setNote("note");
        segnalationDTO.setRoomDTO(null);
        segnalationDTO.setProfessorDTO(null);
        segnalationDTO.setIdState(1);
        segnalationDTO.setDescription("description");
        segnalationDTO.setId(2);

        Set<SegnalationDTO> segnalationDTOS = new HashSet<>(0);
        segnalationDTOS.add(segnalationDTO);

        when(segnalationServicesMock.getByRoom(10)).thenReturn(segnalationDTOS);

        mockMvc.perform(get("/segnalation/getByRoom/{id}",10))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].note", is("note")))
                .andExpect(jsonPath("$[0].roomDTO", is(nullValue())))
                .andExpect(jsonPath("$[0].professorDTO", is(nullValue())))
                .andExpect(jsonPath("$[0].idState", is(1)))
                .andExpect(jsonPath("$[0].description", is("description")));

        verify(segnalationServicesMock, times(1)).getByRoom(10);
        verifyNoMoreInteractions(segnalationServicesMock);
    }

    @Test
    public void getByProfTest() throws Exception {
        SegnalationDTO segnalationDTO = new SegnalationDTO();
        segnalationDTO.setNote("note");
        segnalationDTO.setRoomDTO(null);
        segnalationDTO.setProfessorDTO(null);
        segnalationDTO.setIdState(1);
        segnalationDTO.setDescription("description");
        segnalationDTO.setId(2);

        Set<SegnalationDTO> segnalationDTOS = new HashSet<>(0);
        segnalationDTOS.add(segnalationDTO);

        when(segnalationServicesMock.getByProfessor(1)).thenReturn(segnalationDTOS);

        mockMvc.perform(get("/segnalation/getByIdProfessor/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].note", is("note")))
                .andExpect(jsonPath("$[0].roomDTO", is(nullValue())))
                .andExpect(jsonPath("$[0].professorDTO", is(nullValue())))
                .andExpect(jsonPath("$[0].idState", is(1)))
                .andExpect(jsonPath("$[0].description", is("description")));

        verify(segnalationServicesMock, times(1)).getByProfessor(1);
        verifyNoMoreInteractions(segnalationServicesMock);
    }

    @Test
    public void getByIdTest() throws Exception {
        SegnalationDTO segnalationDTO = new SegnalationDTO();
        segnalationDTO.setNote("note");
        segnalationDTO.setRoomDTO(null);
        segnalationDTO.setProfessorDTO(null);
        segnalationDTO.setIdState(1);
        segnalationDTO.setDescription("description");
        segnalationDTO.setId(2);

        when(segnalationServicesMock.getById(1)).thenReturn(segnalationDTO);

        mockMvc.perform(get("/segnalation/getById/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.note", is("note")))
                .andExpect(jsonPath("$.roomDTO", is(nullValue())))
                .andExpect(jsonPath("$.professorDTO", is(nullValue())))
                .andExpect(jsonPath("$.idState", is(1)))
                .andExpect(jsonPath("$.description", is("description")));

        verify(segnalationServicesMock, times(1)).getById(1);
        verifyNoMoreInteractions(segnalationServicesMock);
    }

    @Test
    public void saveTest() throws Exception {
        SegnalationDTO segnalationDTO = new SegnalationDTO();
        segnalationDTO.setNote("note");
        segnalationDTO.setRoomDTO(null);
        segnalationDTO.setProfessorDTO(null);
        segnalationDTO.setIdState(1);
        segnalationDTO.setDescription("description");
        segnalationDTO.setId(2);

        String json = new ObjectMapper().writeValueAsString(segnalationDTO);

        when(segnalationServicesMock.save(any(SegnalationDTO.class))).thenReturn(segnalationDTO);

        mockMvc.perform(post("/segnalation/save")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.note", is("note")))
                .andExpect(jsonPath("$.roomDTO", is(nullValue())))
                .andExpect(jsonPath("$.professorDTO", is(nullValue())))
                .andExpect(jsonPath("$.idState", is(1)))
                .andExpect(jsonPath("$.description", is("description")));

        verify(segnalationServicesMock, times(1)).save(refEq(segnalationDTO));
        verifyNoMoreInteractions(segnalationServicesMock);
    }


    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
