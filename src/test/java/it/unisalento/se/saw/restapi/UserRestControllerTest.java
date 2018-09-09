package it.unisalento.se.saw.restapi;
import it.unisalento.se.saw.Iservices.IStudentServices;
import it.unisalento.se.saw.Iservices.IUserServices;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.StudentDTO;
import it.unisalento.se.saw.dto.TokenDTO;
import it.unisalento.se.saw.dto.UserDTO;
import it.unisalento.se.saw.exceptions.UserNotFoundException;
import it.unisalento.se.saw.tools.Tools;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class UserRestControllerTest {

    public static final MediaType APPLICATION_JSON_UTF = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
            );

    private MockMvc mockMvc;

    @Mock
    private IUserServices userServicesMock;

    @Mock
    private IStudentServices studentServices;

    @InjectMocks
    UserRestController userRestController;

    @Before
    public void setUp(){
        mockMvc = Tools.getMockMvc(userRestController);
    }

    @Test
    public void findUserByIdTest() throws Exception {

        UserDTO userDTO = new UserDTO();
        userDTO.setUserType(1);
        userDTO.setToken(null);
        userDTO.setUid("MQqa7A80zxQPvY5VV6oeFSBM33o1");
        userDTO.setAge(25);
        userDTO.setEmail("prova@email.it");
        userDTO.setSurname("Mariani");
        userDTO.setName("Antonio");
        userDTO.setIdUser(1);



        when(userServicesMock.getById(32)).thenReturn(userDTO);


        mockMvc.perform(get("/user/getById/{id}",32))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF))
                .andExpect(jsonPath("$.idUser", is(1)))
                .andExpect(jsonPath("$.name", is("Antonio")))
                .andExpect(jsonPath("$.surname", is("Mariani")))
                .andExpect(jsonPath("$.email", is("prova@email.it")))
                .andExpect(jsonPath("$.age", is(25)))
                .andExpect(jsonPath("$.uid", is("MQqa7A80zxQPvY5VV6oeFSBM33o1")))
                .andExpect(jsonPath("$.token", is(nullValue())))
                .andExpect(jsonPath("$.userType", is(1)));

        verify(userServicesMock, times(1)).getById(32);
        verifyNoMoreInteractions(userServicesMock);
    }

    @Test
    public void findUserByIdErrorTest() throws Exception {

        UserDTO userDTO = new UserDTO();
        userDTO.setUserType(1);
        userDTO.setToken(null);
        userDTO.setUid("MQqa7A80zxQPvY5VV6oeFSBM33o1");
        userDTO.setAge(25);
        userDTO.setEmail("prova@email.it");
        userDTO.setSurname("Mariani");
        userDTO.setName("Antonio");
        userDTO.setIdUser(1);



        when(userServicesMock.getById(30)).thenThrow(new UserNotFoundException());


        mockMvc.perform(get("/user/getById/{id}",30))
                .andExpect(status().isOk());


        verify(userServicesMock,times(1)).getById(30);
        verifyNoMoreInteractions(userServicesMock);
    }

    @Test
    public void addTokenTest() throws Exception {
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken("123");
        tokenDTO.setIdUser(32);
        String json = Tools.getJson(tokenDTO);

        when(userServicesMock.addFcmToken(any(TokenDTO.class))).thenReturn(tokenDTO);

        mockMvc.perform(post("/user/addFcmToken")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUser", is(32)));

        verify(userServicesMock, times(1)).addFcmToken(refEq(tokenDTO));
        verifyNoMoreInteractions(userServicesMock);
    }

    @Test
    public void getAllUserTest() throws Exception {
        User user = new User();
        user.setIdUser(1);
        user.setName("Antonio");
        user.setSurname("Mariani");
        user.setEmail("prova@email.it");
        user.setAge(25);
        user.setUid("MQqa7A80zxQPvY5VV6oeFSBM33o1");
        user.setToken(null);
        user.setUserType(1);

        List<User> users = new ArrayList<>();
        users.add(user);


        UserDTO userDTO = new UserDTO();
        userDTO.setUserType(1);
        userDTO.setToken(null);
        userDTO.setUid("MQqa7A80zxQPvY5VV6oeFSBM33o1");
        userDTO.setAge(25);
        userDTO.setEmail("prova@email.it");
        userDTO.setSurname("Mariani");
        userDTO.setName("Antonio");
        userDTO.setIdUser(1);
        Set<UserDTO> userDTOS = new HashSet<>(0);
        userDTOS.add(userDTO);

        when(userServicesMock.getAll()).thenReturn(userDTOS);

        mockMvc.perform(get("/user/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF))
                .andExpect(jsonPath("$[0].idUser", is(1)))
                .andExpect(jsonPath("$[0].name", is("Antonio")))
                .andExpect(jsonPath("$[0].surname", is("Mariani")))
                .andExpect(jsonPath("$[0].email", is("prova@email.it")))
                .andExpect(jsonPath("$[0].age", is(25)))
                .andExpect(jsonPath("$[0].uid", is("MQqa7A80zxQPvY5VV6oeFSBM33o1")))
                .andExpect(jsonPath("$[0].token", is(nullValue())))
                .andExpect(jsonPath("$[0].userType", is(1)));

        verify(userServicesMock, times(1)).getAll();
        verifyNoMoreInteractions(userServicesMock);

    }

    @Test
    public void getUserByUidTest() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setIdUser(32);
        studentDTO.setName("Antonio");
        studentDTO.setSurname("Mariani");
        studentDTO.setEmail("prova@email.it");
        studentDTO.setAge(25);
        studentDTO.setUid("prova");
        studentDTO.setUserType(1);
        studentDTO.setToken(null);
        studentDTO.setId(1);
        studentDTO.setMatricola("2002");
        studentDTO.setYear(1);
        studentDTO.setYearStart(1);
        studentDTO.setIdCourse(1);
        studentDTO.setCourseDTO(null);



        when(userServicesMock.getByUid("prova")).thenReturn(studentDTO);


        mockMvc.perform(get("/user/getByUid/{uid}","prova"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF))
                .andExpect(jsonPath("$.idUser", is(32)))
                .andExpect(jsonPath("$.name", is("Antonio")))
                .andExpect(jsonPath("$.surname", is("Mariani")))
                .andExpect(jsonPath("$.email", is("prova@email.it")))
                .andExpect(jsonPath("$.age", is(25)))
                .andExpect(jsonPath("$.uid", is("prova")))
                .andExpect(jsonPath("$.token", is(nullValue())))
                .andExpect(jsonPath("$.userType", is(1)))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.matricola", is("2002")))
                .andExpect(jsonPath("$.year", is(1)))
                .andExpect(jsonPath("$.yearStart", is(1)))
                .andExpect(jsonPath("$.idCourse", is(1)))
                .andExpect(jsonPath("$.courseDTO", is(nullValue())));

        verify(userServicesMock, times(1)).getByUid("prova");
        verifyNoMoreInteractions(userServicesMock);
    }

    @Test
    public void getUserByUidErrorTest() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setIdUser(32);
        studentDTO.setName("Antonio");
        studentDTO.setSurname("Mariani");
        studentDTO.setEmail("prova@email.it");
        studentDTO.setAge(25);
        studentDTO.setUid("prova");
        studentDTO.setUserType(1);
        studentDTO.setToken(null);
        studentDTO.setId(1);
        studentDTO.setMatricola("2002");
        studentDTO.setYear(1);
        studentDTO.setYearStart(1);
        studentDTO.setIdCourse(1);
        studentDTO.setCourseDTO(null);



        when(userServicesMock.getByUid("prova")).thenThrow(new UserNotFoundException());


        mockMvc.perform(get("/user/getByUid/{uid}","prova"));

        verify(userServicesMock, times(1)).getByUid("prova");
        verifyNoMoreInteractions(userServicesMock);
    }

    @Test
    public void saveTest() throws Exception {

        UserDTO userDTO = new UserDTO();
        userDTO.setUserType(1);
        userDTO.setToken(null);
        userDTO.setUid("MQqa7A80zxQPvY5VV6oeFSBM33o1");
        userDTO.setAge(25);
        userDTO.setEmail("prova@email.it");
        userDTO.setSurname("Mariani");
        userDTO.setName("Antonio");
        userDTO.setIdUser(1);
        String json = Tools.getJson(userDTO);


        when(userServicesMock.save(any(UserDTO.class))).thenReturn(userDTO);


        mockMvc.perform(post("/user/save")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF))
                .andExpect(jsonPath("$.idUser", is(1)))
                .andExpect(jsonPath("$.name", is("Antonio")))
                .andExpect(jsonPath("$.surname", is("Mariani")))
                .andExpect(jsonPath("$.email", is("prova@email.it")))
                .andExpect(jsonPath("$.age", is(25)))
                .andExpect(jsonPath("$.uid", is("MQqa7A80zxQPvY5VV6oeFSBM33o1")))
                .andExpect(jsonPath("$.token", is(nullValue())))
                .andExpect(jsonPath("$.userType", is(1)));

        verify(userServicesMock, times(1)).save(refEq(userDTO));
        verifyNoMoreInteractions(userServicesMock);
    }


}
