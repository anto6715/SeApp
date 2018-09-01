package it.unisalento.se.saw.models.DomainFactory;

import it.unisalento.se.saw.domain.SegnalationState;
import it.unisalento.se.saw.dto.SegnalationStateDTO;

public class SegnalationStateDomainModel implements Domain<SegnalationStateDTO, SegnalationState> {
    @Override
    public SegnalationState create(SegnalationStateDTO segnalationStateDTO) {
        SegnalationState segnalationState = new SegnalationState();
        segnalationState.setState(segnalationStateDTO.getState());
        segnalationState.setIdSegnalationState(segnalationStateDTO.getId());
        return segnalationState;
    }
}
