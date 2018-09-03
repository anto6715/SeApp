package it.unisalento.se.saw.services;

import it.unisalento.se.saw.domain.*;
import it.unisalento.se.saw.dto.ProfessorDTO;
import it.unisalento.se.saw.dto.RoomDTO;
import it.unisalento.se.saw.dto.SegnalationDTO;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.exceptions.SegnalationNotFoundException;
import it.unisalento.se.saw.exceptions.SegnalationStateNotFoundException;
import it.unisalento.se.saw.repositories.SegnalationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class SegnalationServiceTest {

    @Mock
    SegnalationRepository segnalationRepository;

    @Mock
    RoomService roomService;

    @Mock
    ProfessorService professorService;

    @Mock
    SegnalationStateService segnalationStateService;

    @InjectMocks
    SegnalationService segnalationService;

    @Test
    public void getAllTest() {

        /*****************User*************************************/
        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");
        /******************************************************/

        /*************************Professor*****************************/

        ProfessorId professorId = new ProfessorId();
        professorId.setUserIdUser(1);
        professorId.setIdProfessor(1);

        Professor professor = new Professor();
        professor.setUser(user);
        professor.setId(professorId);

        /******************************************************/

        /**************************Room****************************/
        Room room = new Room(1,"location","name",1,1,
                null,null,null,null);
        room.setIdRoom(1);
        /******************************************************/

        /***************************SegnalationState***************************/
        SegnalationState segnalationState = new SegnalationState();
        segnalationState.setIdSegnalationState(1);
        segnalationState.setState("state");
        /******************************************************/

        /**************************Segnalation****************************/
        SegnalationId segnalationId = new SegnalationId(1,1,1,1,1);
        Segnalation segnalation = new Segnalation(segnalationId, professor,room,segnalationState);
        /******************************************************/
        List<Segnalation> segnalations = new ArrayList<>();
        segnalations.add(segnalation);

        when(segnalationRepository.findAll()).thenReturn(segnalations);


        Set<SegnalationDTO> segnalationDTOS = segnalationService.getAll();
        assertEquals(segnalation.getDescription(), segnalationDTOS.iterator().next().getDescription());
    }

    @Test
    public void getByRoomTest() {

        /*****************User*************************************/
        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");
        /******************************************************/

        /*************************Professor*****************************/

        ProfessorId professorId = new ProfessorId();
        professorId.setUserIdUser(1);
        professorId.setIdProfessor(1);

        Professor professor = new Professor();
        professor.setUser(user);
        professor.setId(professorId);

        /******************************************************/

        /**************************Room****************************/
        Room room = new Room(1,"location","name",1,1,
                null,null,null,null);
        room.setIdRoom(1);
        /******************************************************/

        /***************************SegnalationState***************************/
        SegnalationState segnalationState = new SegnalationState();
        segnalationState.setIdSegnalationState(1);
        segnalationState.setState("state");
        /******************************************************/

        /**************************Segnalation****************************/
        SegnalationId segnalationId = new SegnalationId(1,1,1,1,1);
        Segnalation segnalation = new Segnalation(segnalationId, professor,room,segnalationState);
        /******************************************************/
        List<Segnalation> segnalations = new ArrayList<>();
        segnalations.add(segnalation);

        when(segnalationRepository.findSegnalationsById_RoomIdRoom(1)).thenReturn(segnalations);

        Set<SegnalationDTO> segnalationDTOS = segnalationService.getByRoom(1);
        assertEquals(segnalation.getNote(), segnalationDTOS.iterator().next().getNote());
    }

    @Test
    public void getByProfessorTest() {

        /*****************User*************************************/
        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");
        /******************************************************/

        /*************************Professor*****************************/

        ProfessorId professorId = new ProfessorId();
        professorId.setUserIdUser(1);
        professorId.setIdProfessor(1);

        Professor professor = new Professor();
        professor.setUser(user);
        professor.setId(professorId);

        /******************************************************/

        /**************************Room****************************/
        Room room = new Room(1,"location","name",1,1,
                null,null,null,null);
        room.setIdRoom(1);
        /******************************************************/

        /***************************SegnalationState***************************/
        SegnalationState segnalationState = new SegnalationState();
        segnalationState.setIdSegnalationState(1);
        segnalationState.setState("state");
        /******************************************************/

        /**************************Segnalation****************************/
        SegnalationId segnalationId = new SegnalationId(1,1,1,1,1);
        Segnalation segnalation = new Segnalation(segnalationId, professor,room,segnalationState,"note","description");
        /******************************************************/
        List<Segnalation> segnalations = new ArrayList<>();
        segnalations.add(segnalation);

        when(segnalationRepository.findSegnalationsById_ProfessorIdProfessor(1)).thenReturn(segnalations);

        Set<SegnalationDTO> segnalationDTOS = segnalationService.getByProfessor(1);
        assertEquals(segnalation.getId().getIdSegnalation(), segnalationDTOS.iterator().next().getId());
    }

    @Test
    public void getByIdTest() throws SegnalationNotFoundException {

        /*****************User*************************************/
        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");
        /******************************************************/

        /*************************Professor*****************************/

        ProfessorId professorId = new ProfessorId();
        professorId.setUserIdUser(1);
        professorId.setIdProfessor(1);

        Professor professor = new Professor();
        professor.setUser(user);
        professor.setId(professorId);

        /******************************************************/

        /**************************Room****************************/
        Room room = new Room(1,"location","name",1,1,
                null,null,null,null);
        room.setIdRoom(1);
        /******************************************************/

        /***************************SegnalationState***************************/
        SegnalationState segnalationState = new SegnalationState();
        segnalationState.setIdSegnalationState(1);
        segnalationState.setState("state");
        /******************************************************/

        /**************************Segnalation****************************/
        SegnalationId segnalationId = new SegnalationId(1,1,1,1,1);
        Segnalation segnalation = new Segnalation();
        segnalation.setSegnalationState(segnalationState);
        segnalation.setId(segnalationId);
        segnalation.setNote("note");
        segnalation.setRoom(room);
        segnalation.setDescription("description");
        segnalation.setProfessor(professor);
        /******************************************************/
        when(segnalationRepository.findSegnalationById_IdSegnalation(1)).thenReturn(segnalation);

        SegnalationDTO segnalationDTO = segnalationService.getById(1);
        assertEquals(segnalation.getId().getProfessorIdProfessor(), segnalationDTO.getProfessorDTO().getId());
    }

    @Test
    public void getByIdErrorTest() throws SegnalationNotFoundException {

        /*****************User*************************************/
        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");
        /******************************************************/

        /*************************Professor*****************************/

        ProfessorId professorId = new ProfessorId();
        professorId.setUserIdUser(1);
        professorId.setIdProfessor(1);

        Professor professor = new Professor();
        professor.setUser(user);
        professor.setId(professorId);

        /******************************************************/

        /**************************Room****************************/
        Room room = new Room(1,"location","name",1,1,
                null,null,null,null);
        room.setIdRoom(1);
        /******************************************************/

        /***************************SegnalationState***************************/
        SegnalationState segnalationState = new SegnalationState();
        segnalationState.setIdSegnalationState(1);
        segnalationState.setState("state");
        /******************************************************/

        /**************************Segnalation****************************/
        SegnalationId segnalationId = new SegnalationId(1,1,1,1,1);
        Segnalation segnalation = new Segnalation();
        segnalation.setSegnalationState(segnalationState);
        segnalation.setId(segnalationId);
        segnalation.setNote("note");
        segnalation.setRoom(room);
        segnalation.setDescription("description");
        segnalation.setProfessor(professor);
        /******************************************************/
        when(segnalationRepository.findSegnalationById_IdSegnalation(1)).thenReturn(segnalation);

        try {
            SegnalationDTO segnalationDTO = segnalationService.getById(2);
            assertEquals(segnalation.getId().getIdSegnalation(), segnalationDTO.getId());
        } catch (Exception e) {
            assertEquals("Segnalation not found", e.getMessage());
        }

    }

    @Test
    public void saveTest() throws SegnalationNotFoundException, ProfessorNotFoundException, RoomNotFoundException, SegnalationStateNotFoundException {

        /*****************User*************************************/
        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");
        /******************************************************/

        /*************************Professor*****************************/

        ProfessorId professorId = new ProfessorId();
        professorId.setUserIdUser(1);
        professorId.setIdProfessor(1);

        Professor professor = new Professor();
        professor.setUser(user);
        professor.setId(professorId);

        /******************************************************/

        /**************************Room****************************/
        Room room = new Room(1,"location","name",1,1,
                null,null,null,null);
        room.setIdRoom(1);
        /******************************************************/

        /***************************SegnalationState***************************/
        SegnalationState segnalationState = new SegnalationState();
        segnalationState.setIdSegnalationState(1);
        segnalationState.setState("state");
        /******************************************************/

        /**************************Segnalation****************************/
        SegnalationId segnalationId = new SegnalationId(1,1,1,1,1);
        Segnalation segnalation = new Segnalation();
        segnalation.setSegnalationState(segnalationState);
        segnalation.setId(segnalationId);
        segnalation.setNote("note");
        segnalation.setRoom(room);
        segnalation.setDescription("description");
        segnalation.setProfessor(professor);
        /******************************************************/

        /*************************ProfessorDTO*****************************/
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(1);

        /******************************************************/

        /****************************RoomDTO**************************/
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(1);

        /******************************************************/

        /**************************SegnalationDTO****************************/
        SegnalationDTO segnalationDTO = new SegnalationDTO();
        segnalationDTO.setId(1);
        segnalationDTO.setDescription("description");
        segnalationDTO.setIdState(1);
        segnalationDTO.setProfessorDTO(professorDTO);
        segnalationDTO.setRoomDTO(roomDTO);

        /******************************************************/

        when(professorService.getDomainById(1)).thenReturn(professor);
        when(roomService.getDomainById(1)).thenReturn(room);
        when(segnalationStateService.getDomainById(1)).thenReturn(segnalationState);
        when(segnalationRepository.save(any(Segnalation.class))).thenReturn(segnalation);



        SegnalationDTO s = segnalationService.save(segnalationDTO);
        assertEquals(segnalationDTO.getId(), s.getId());




    }
}
