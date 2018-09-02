package it.unisalento.se.saw.restapi;
import it.unisalento.se.saw.Iservices.ISecretaryServices;
import it.unisalento.se.saw.dto.SecretaryDTO;
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

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class SecretaryRestControllerTest {

    public static final MediaType APPLICATION_JSON_UTF = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    private MockMvc mockMvc;

    @Mock
    ISecretaryServices secretaryServicesMock;

    @InjectMocks
    SecretaryRestController secretaryRestController;

    @Before
    public void setUp(){
        mockMvc = Tools.getMockMvc(secretaryRestController);
    }

    @Test
    public void getByIdTest() throws Exception {
        SecretaryDTO secretaryDTO = new SecretaryDTO();
        secretaryDTO.setAge(1);
        secretaryDTO.setEmail("email");
        secretaryDTO.setName("name");
        secretaryDTO.setSurname("surname");
        secretaryDTO.setId(5);
        secretaryDTO.setUid("uid");
        secretaryDTO.setUserType(2);
        secretaryDTO.setIdUser(4);
        secretaryDTO.setToken("token");

        when(secretaryServicesMock.getById(5)).thenReturn(secretaryDTO);

        mockMvc.perform(get("/secretary/getById/{id}",5))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.age", is(1)))
                .andExpect(jsonPath("$.email", is("email")))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.surname", is("surname")))
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.uid", is("uid")))
                .andExpect(jsonPath("$.userType", is(2)))
                .andExpect(jsonPath("$.idUser", is(4)))
                .andExpect(jsonPath("$.token", is("token")));

        verify(secretaryServicesMock, times(1)).getById(5);
        verifyNoMoreInteractions(secretaryServicesMock);
    }

    @Test
    public void getByUidTest() throws Exception {
        SecretaryDTO secretaryDTO = new SecretaryDTO();
        secretaryDTO.setAge(1);
        secretaryDTO.setEmail("email");
        secretaryDTO.setName("name");
        secretaryDTO.setSurname("surname");
        secretaryDTO.setId(5);
        secretaryDTO.setUid("uid");
        secretaryDTO.setUserType(2);
        secretaryDTO.setIdUser(4);
        secretaryDTO.setToken("token");

        when(secretaryServicesMock.getByUid("uid")).thenReturn(secretaryDTO);

        mockMvc.perform(get("/secretary/getByUid/{uid}","uid"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.age", is(1)))
                .andExpect(jsonPath("$.email", is("email")))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.surname", is("surname")))
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.uid", is("uid")))
                .andExpect(jsonPath("$.userType", is(2)))
                .andExpect(jsonPath("$.idUser", is(4)))
                .andExpect(jsonPath("$.token", is("token")));

        verify(secretaryServicesMock, times(1)).getByUid("uid");
        verifyNoMoreInteractions(secretaryServicesMock);
    }

    @Test
    public void saveTest() throws Exception {
        SecretaryDTO secretaryDTO = new SecretaryDTO();
        secretaryDTO.setAge(1);
        secretaryDTO.setEmail("email");
        secretaryDTO.setName("name");
        secretaryDTO.setSurname("surname");
        secretaryDTO.setId(5);
        secretaryDTO.setUid("uid");
        secretaryDTO.setUserType(2);
        secretaryDTO.setIdUser(4);
        secretaryDTO.setToken("token");
        String json = Tools.getJson(secretaryDTO);

        when(secretaryServicesMock.save(any(SecretaryDTO.class))).thenReturn(secretaryDTO);

        mockMvc.perform(post("/secretary/save")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.age", is(1)))
                .andExpect(jsonPath("$.email", is("email")))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.surname", is("surname")))
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.uid", is("uid")))
                .andExpect(jsonPath("$.userType", is(2)))
                .andExpect(jsonPath("$.idUser", is(4)))
                .andExpect(jsonPath("$.token", is("token")));

        verify(secretaryServicesMock, times(1)).save(refEq(secretaryDTO));
        verifyNoMoreInteractions(secretaryServicesMock);
    }
}
