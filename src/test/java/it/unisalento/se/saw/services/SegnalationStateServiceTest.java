package it.unisalento.se.saw.services;

import it.unisalento.se.saw.domain.SegnalationState;
import it.unisalento.se.saw.dto.SegnalationStateDTO;
import it.unisalento.se.saw.exceptions.SegnalationStateNotFoundException;
import it.unisalento.se.saw.repositories.SegnalationStateRepository;
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
public class SegnalationStateServiceTest {

    @Mock
    SegnalationStateRepository segnalationStateRepository;

    @InjectMocks
    SegnalationStateService segnalationStateService;

    @Test
    public void getAllTest() {
        SegnalationState segnalationState = new SegnalationState();
        segnalationState.setIdSegnalationState(1);
        segnalationState.setState("state");
        List<SegnalationState> segnalationStates = new ArrayList<>();
        segnalationStates.add(segnalationState);

        when(segnalationStateRepository.findAll()).thenReturn(segnalationStates);
        Set<SegnalationStateDTO> segnalationStateDTOS = segnalationStateService.getAll();
        assertEquals(segnalationState.getIdSegnalationState(), (Integer)segnalationStateDTOS.iterator().next().getId());
    }

    @Test
    public void getByIdTest() throws SegnalationStateNotFoundException {
        SegnalationState segnalationState = new SegnalationState();
        segnalationState.setIdSegnalationState(1);
        segnalationState.setState("state");

        when(segnalationStateRepository.getOne(1)).thenReturn(segnalationState);
        SegnalationStateDTO segnalationStateDTO = segnalationStateService.getById(1);
        assertEquals(segnalationState.getIdSegnalationState(), (Integer)segnalationStateDTO.getId());
    }

    @Test
    public void getByIdErrorTest() throws SegnalationStateNotFoundException {
        SegnalationState segnalationState = new SegnalationState("state",null);
        segnalationState.setIdSegnalationState(1);

        when(segnalationStateRepository.getOne(1)).thenReturn(segnalationState);
        try {
            SegnalationStateDTO segnalationStateDTO = segnalationStateService.getById(2);
            assertEquals(segnalationState.getIdSegnalationState(), (Integer)segnalationStateDTO.getId());
        } catch (Exception e) {
            assertEquals("SegnalationState not found", e.getMessage());
        }

    }

    @Test
    public void getDomainByIdTest() throws SegnalationStateNotFoundException {
        SegnalationState segnalationState = new SegnalationState();
        segnalationState.setIdSegnalationState(1);
        segnalationState.setState("state");

        when(segnalationStateRepository.getOne(1)).thenReturn(segnalationState);

        SegnalationState s = segnalationStateService.getDomainById(1);
        assertEquals(segnalationState.getIdSegnalationState(), segnalationState.getIdSegnalationState());

    }

    @Test
    public void saveTest() throws SegnalationStateNotFoundException {
        SegnalationState segnalationState = new SegnalationState();
        segnalationState.setIdSegnalationState(1);
        segnalationState.setState("state");

        SegnalationStateDTO segnalationStateDTO = new SegnalationStateDTO();
        segnalationStateDTO.setState("state");
        segnalationStateDTO.setId(1);

        when(segnalationStateRepository.save(any(SegnalationState.class))).thenReturn(segnalationState);

        SegnalationStateDTO s = segnalationStateService.save(segnalationStateDTO);
        assertEquals(segnalationStateDTO.getId(), s.getId());

    }
}
