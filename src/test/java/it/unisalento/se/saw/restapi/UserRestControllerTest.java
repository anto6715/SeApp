package it.unisalento.se.saw.restapi;
import it.unisalento.se.saw.Iservices.IUserServices;
import it.unisalento.se.saw.domain.Student;
import it.unisalento.se.saw.domain.User;
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
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(new UserRestController(userServicesMock))
                .setViewResolvers(viewResolver())
                .build();
    }

    @Test
    public void findUserByIdTest() throws Exception {
        User user = new User();
        user.setIdUser(1);
        user.setName("Antonio");
        user.setSurname("Mariani");
        user.setEmail("prova@email.it");
        user.setAge(25);
        user.setUid("MQqa7A80zxQPvY5VV6oeFSBM33o1");
        user.setToken(null);
        user.setUserType(1);

        when(userServicesMock.getById(32)).thenReturn(user);


        mockMvc.perform(get("/user/getById/{id}",32))
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
    public void findStudentByUidTest() throws Exception {
        User user = new User();
        Student student = new Student();
        when(userServicesMock.getByUid("MQqa7A80zxQPvY5VV6oeFSBM33o1")).thenReturn(user);


        mockMvc.perform(get("/user/getByUid/{uid}","MQqa7A80zxQPvY5VV6oeFSBM33o1"))
                .andExpect(content().contentType(APPLICATION_JSON_UTF))
                .andExpect(jsonPath("$.idUser", is(1)))
                .andExpect(jsonPath("$.name", is("Antonio")))
                .andExpect(jsonPath("$.surname", is("Mariani")))
                .andExpect(jsonPath("$.email", is("prova@email.it")))
                .andExpect(jsonPath("$.age", is(25)))
                .andExpect(jsonPath("$.uid", is("MQqa7A80zxQPvY5VV6oeFSBM33o1")))
                .andExpect(jsonPath("$.token", is(nullValue())))
                .andExpect(jsonPath("$.userType", is(1)));

        verify(userServicesMock, times(1)).getByUid("MQqa7A80zxQPvY5VV6oeFSBM33o1");
        verifyNoMoreInteractions(userServicesMock);
    }


    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
