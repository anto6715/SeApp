package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.SegnalationState;
import it.unisalento.se.saw.dto.SegnalationStateDTO;

public class SegnalationStateDTOModel implements DTO<SegnalationState, SegnalationStateDTO> {
    @Override
    public SegnalationStateDTO create(SegnalationState segnalationState) {
        SegnalationStateDTO segnalationStateDTO = new SegnalationStateDTO();
        segnalationStateDTO.setState(segnalationState.getState());
        segnalationStateDTO.setId(segnalationState.getIdSegnalationState());
        return segnalationStateDTO;
    }
}
