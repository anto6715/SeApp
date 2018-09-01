package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.SegnalationState;
import it.unisalento.se.saw.dto.SegnalationStateDTO;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.FactoryProducer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetSegnalationStateDTOModel implements DTO<List<SegnalationState>, Set<SegnalationStateDTO>> {
    @Override
    public Set<SegnalationStateDTO> create(List<SegnalationState> segnalationStates) {
        AbstractFactory abstractDTOFactory = FactoryProducer.getFactory("DTO");
        DTO<SegnalationState, SegnalationStateDTO> dto = abstractDTOFactory.getDTO("SegnalationState");
        Set<SegnalationStateDTO> segnalationStateDTOS = new HashSet<>(0);
        for (SegnalationState segnalationState: segnalationStates) {
            segnalationStateDTOS.add(dto.create(segnalationState));
        }
        return segnalationStateDTOS;
    }
}
