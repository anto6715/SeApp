package it.unisalento.se.saw.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisalento.se.saw.Iservices.ISegnalationStateServices;
import it.unisalento.se.saw.dto.SegnalationStateDTO;
import it.unisalento.se.saw.exceptions.SegnalationNotFoundException;
import it.unisalento.se.saw.exceptions.SegnalationStateNotFoundException;
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
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class SegnalationStateRestControllerTest {

    public static final MediaType APPLICATION_JSON_UTF = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    private MockMvc mockMvc;

    @Mock
    ISegnalationStateServices segnalationStateServicesMock;

    @InjectMocks
    SegnalationStateRestController segnalationStateRestController;

    @Before
    public void setUp(){
        mockMvc = Tools.getMockMvc(segnalationStateRestController);
    }

    @Test
    public void getAllTest() throws  Exception {
        SegnalationStateDTO segnalationStateDTO = new SegnalationStateDTO();
        segnalationStateDTO.setId(1);
        segnalationStateDTO.setState("inviata");
        Set<SegnalationStateDTO> segnalationStateDTOS = new HashSet<>(0);
        segnalationStateDTOS.add(segnalationStateDTO);

        when(segnalationStateServicesMock.getAll()).thenReturn(segnalationStateDTOS);

        mockMvc.perform(get("/segnalationState/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].state", is("inviata")));

        verify(segnalationStateServicesMock, times(1)).getAll();
        verifyNoMoreInteractions(segnalationStateServicesMock);
    }

    @Test
    public void getByIdTest() throws  Exception {
        SegnalationStateDTO segnalationStateDTO = new SegnalationStateDTO();
        segnalationStateDTO.setId(1);
        segnalationStateDTO.setState("inviata");

        when(segnalationStateServicesMock.getById(1)).thenReturn(segnalationStateDTO);

        mockMvc.perform(get("/segnalationState/getById/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.state", is("inviata")));

        verify(segnalationStateServicesMock, times(1)).getById(1);
        verifyNoMoreInteractions(segnalationStateServicesMock);
    }

    @Test
    public void getByIdErrorTest() throws  Exception {
        SegnalationStateDTO segnalationStateDTO = new SegnalationStateDTO();
        segnalationStateDTO.setId(1);
        segnalationStateDTO.setState("inviata");

        when(segnalationStateServicesMock.getById(1)).thenThrow(new SegnalationStateNotFoundException());

        mockMvc.perform(get("/segnalationState/getById/{id}",1))
                .andExpect(status().isOk());

        verify(segnalationStateServicesMock, times(1)).getById(1);
        verifyNoMoreInteractions(segnalationStateServicesMock);
    }

    @Test
    public void saveTest() throws  Exception {
        SegnalationStateDTO segnalationStateDTO = new SegnalationStateDTO();
        segnalationStateDTO.setId(1);
        segnalationStateDTO.setState("inviata");
        String json = Tools.getJson(segnalationStateDTO);

        when(segnalationStateServicesMock.save(any(SegnalationStateDTO.class))).thenReturn(segnalationStateDTO);

        mockMvc.perform(post("/segnalationState/save")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.state", is("inviata")));

        verify(segnalationStateServicesMock, times(1)).save(refEq(segnalationStateDTO));
        verifyNoMoreInteractions(segnalationStateServicesMock);
    }
}
