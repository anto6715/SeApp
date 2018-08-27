package it.unisalento.se.saw.models;

import it.unisalento.se.saw.domain.Segnalation;
import it.unisalento.se.saw.dto.SegnalationDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetSegnalationDTOModel implements DTO<List<Segnalation>, Set<SegnalationDTO>> {
    @Override
    public Set<SegnalationDTO> create(List<Segnalation> segnalations) {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<Segnalation, SegnalationDTO> dto = abstractFactory.getDTO("Segnalation");
        Set<SegnalationDTO> segnalationDTOS = new HashSet<>(0);
        for (Segnalation segnalation: segnalations) {
            segnalationDTOS.add(dto.create(segnalation));
        }
        return segnalationDTOS;
    }
}
