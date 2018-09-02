package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.SegnalationState;
import it.unisalento.se.saw.dto.SegnalationStateDTO;
import it.unisalento.se.saw.exceptions.SegnalationStateNotFoundException;

import java.util.Set;

public interface ISegnalationStateServices {

    public Set<SegnalationStateDTO> getAll();
    public SegnalationStateDTO getById(int id) throws SegnalationStateNotFoundException;
    public SegnalationState getDomainById(int id) throws SegnalationStateNotFoundException;
    public SegnalationStateDTO save(SegnalationStateDTO segnalationStateDTO);
}
