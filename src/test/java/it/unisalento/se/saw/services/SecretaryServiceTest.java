package it.unisalento.se.saw.services;

import it.unisalento.se.saw.domain.Secretary;
import it.unisalento.se.saw.domain.SecretaryId;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.SecretaryDTO;
import it.unisalento.se.saw.exceptions.SecretaryNotFoundException;
import it.unisalento.se.saw.repositories.SecretaryRepository;
import it.unisalento.se.saw.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SecretaryServiceTest {

    @Mock
    SecretaryRepository secretaryRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    SecretaryService secretaryService;

    @Test
    public void getByIdTest() throws SecretaryNotFoundException {

        /*****************User*************************************/
        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");
        /******************************************************/

        /************************Secretary******************************/
        SecretaryId secretaryId = new SecretaryId();
        secretaryId.setIdSecretary(1);
        secretaryId.setUserIdUser(1);

        Secretary secretary = new Secretary();
        secretary.setId(secretaryId);
        secretary.setUser(user);
        /******************************************************/

        when(secretaryRepository.findSecretaryById_IdSecretary(1)).thenReturn(secretary);

        SecretaryDTO secretaryDTO = secretaryService.getById(1);

        assertEquals(secretary.getId().getIdSecretary(), secretaryDTO.getId());
        assertEquals(secretary.getUser().getIdUser(), (Integer)secretaryDTO.getIdUser());
    }

    @Test
    public void getByUidTest() throws SecretaryNotFoundException {

        /*****************User*************************************/
        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");
        /******************************************************/

        /************************Secretary******************************/
        SecretaryId secretaryId = new SecretaryId(1,1);

        Secretary secretary = new Secretary(secretaryId,user);
        /******************************************************/

        when(secretaryRepository.findSecretaryByUserUid("uid")).thenReturn(secretary);

        SecretaryDTO secretaryDTO = secretaryService.getByUid("uid");

        assertEquals(secretary.getId().getUserIdUser(), secretaryDTO.getIdUser());
        assertEquals(secretary.getUser().getAge(), (Integer)secretaryDTO.getAge());
    }

    @Test
    public void saveTest() throws SecretaryNotFoundException {

        /*****************User*************************************/
        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");
        /******************************************************/

        /************************Secretary******************************/
        SecretaryId secretaryId = new SecretaryId();
        secretaryId.setIdSecretary(1);
        secretaryId.setUserIdUser(1);

        Secretary secretary = new Secretary();
        secretary.setId(secretaryId);
        secretary.setUser(user);
        /******************************************************/

        /************************SecretaryDTO******************************/
        SecretaryDTO secretaryDTO = new SecretaryDTO();
        secretaryDTO.setUid("uid");
        secretaryDTO.setIdUser(1);
        secretaryDTO.setToken("token");
        secretaryDTO.setUserType(2);
        /******************************************************/

        when(userRepository.save(any(User.class))).thenReturn(user);

        SecretaryDTO s = secretaryService.save(secretaryDTO);

        assertEquals(s.getAge(), secretaryDTO.getAge());
    }

}
