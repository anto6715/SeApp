package it.unisalento.se.saw.services;

import it.unisalento.se.saw.domain.Student;
import it.unisalento.se.saw.domain.StudentId;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.*;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.exceptions.SecretaryNotFoundException;
import it.unisalento.se.saw.exceptions.StudentNotFoundException;
import it.unisalento.se.saw.exceptions.UserNotFoundException;
import it.unisalento.se.saw.repositories.UserRepository;
import org.junit.Assert;
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
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    StudentService studentService;

    @Mock
    ProfessorService professorService;

    @Mock
    SecretaryService secretaryService;


    @InjectMocks
    UserService userService;

    @Test
    public void getAllTest()   {


        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");

        List<User> users = new ArrayList<>();
        users.add(user);


        when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> userDTOS = userService.getAll();

        assertEquals(user.getIdUser(), (Integer) userDTOS.iterator().next().getIdUser());
    }

    @Test
    public void getByIdTest() throws UserNotFoundException {


        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");


        when(userRepository.getOne(1)).thenReturn(user);

        UserDTO userDTO = userService.getById(1);

        assertEquals(user.getIdUser(), (Integer)userDTO.getIdUser());
    }

    @Test
    public void getByIdErrorTest()   {


        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");


        when(userRepository.getOne(1)).thenReturn(user);

        try {
            UserDTO userDTO = userService.getById(2);
            assertEquals(user.getIdUser(), (Integer)userDTO.getIdUser());
        } catch (UserNotFoundException e) {
            assertEquals("User not found", e.getMessage());
        }


    }


    @Test
    public void getStudentByUidTest() throws UserNotFoundException, StudentNotFoundException, ProfessorNotFoundException, SecretaryNotFoundException {
        User user = new User("Antonio","Mariani","email","password",2,"uid",1,null,null,null);


        StudentId studentId = new StudentId();
        studentId.setUserIdUser(1);
        studentId.setIdStudent(1);

        Student student = new Student();
        student.setUser(user);
        student.setId(studentId);

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setIdUser(1);
        studentDTO.setEmail("email");
        studentDTO.setAge(2);
        studentDTO.setUid("uid");
        studentDTO.setUserType(1);
        studentDTO.setToken("token");
        studentDTO.setId(1);

        when(userRepository.findUserByUid("uid")).thenReturn(user);

        when(studentService.getByUid("uid")).thenReturn(studentDTO);

        StudentDTO s = (StudentDTO) userService.getByUid("uid");
        assertEquals("uid", s.getUid());
    }

    @Test
    public void getProfByUidTest() throws ProfessorNotFoundException, UserNotFoundException, SecretaryNotFoundException, StudentNotFoundException {
        User user = new User();
        user.setIdUser(1);
        user.setUserType(3);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");

        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setUid("uid");

        when(userRepository.findUserByUid("uid")).thenReturn(user);
        when(professorService.getByUid("uid")).thenReturn(professorDTO);

        ProfessorDTO p = (ProfessorDTO) userService.getByUid("uid");
        assertEquals("uid",professorDTO.getUid());
    }

    @Test
    public void getSecretaryByUidTest() throws SecretaryNotFoundException, UserNotFoundException, ProfessorNotFoundException, StudentNotFoundException {
        User user = new User();
        user.setIdUser(1);
        user.setUserType(2);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");

        SecretaryDTO secretaryDTO = new SecretaryDTO();
        secretaryDTO.setUid("uid");

        when(userRepository.findUserByUid("uid")).thenReturn(user);
        when(secretaryService.getByUid("uid")).thenReturn(secretaryDTO);

        SecretaryDTO p = (SecretaryDTO) userService.getByUid("uid");
        assertEquals("uid",secretaryDTO.getUid());
    }

    @Test
    public void saveTest() {
        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");

        UserDTO userDTO = new UserDTO();
        userDTO.setUserType(1);
        userDTO.setToken("token");
        userDTO.setUid("uid");
        userDTO.setAge(2);
        userDTO.setEmail("email");
        userDTO.setIdUser(1);

        when(userRepository.save(any(User.class))).thenReturn(user);
        UserDTO u = userService.save(userDTO);
        assertEquals(user.getIdUser(), (Integer) u.getIdUser());
    }

    @Test
    public void addFcmToken() {
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setIdUser(1);
        tokenDTO.setToken("token");

        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");

        when(userRepository.getOne(1)).thenReturn(user);

        when(userRepository.save(any(User.class))).thenReturn(user);

        TokenDTO tDTO = userService.addFcmToken(tokenDTO);
        assertEquals(user.getIdUser(), (Integer) tDTO.getIdUser());
        assertEquals(user.getToken(), tDTO.getToken());
    }
}
