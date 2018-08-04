package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.SegnalationState;
import it.unisalento.se.saw.dto.SegnalationDTO;
import it.unisalento.se.saw.dto.SegnalationStateDTO;
import it.unisalento.se.saw.exceptions.SegnalationStateNotFoundException;

import java.util.List;

public interface ISegnalationStateServices {

    public List<SegnalationState> getAll();
    public SegnalationState getById(int id) throws SegnalationStateNotFoundException;
    public SegnalationState save(SegnalationStateDTO segnalationStateDTO);
    public void remove(int id) throws SegnalationStateNotFoundException;
}
