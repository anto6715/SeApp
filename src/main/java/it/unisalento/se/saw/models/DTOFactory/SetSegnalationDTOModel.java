package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.Segnalation;
import it.unisalento.se.saw.dto.SegnalationDTO;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.FactoryProducer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetSegnalationDTOModel implements DTO<List<Segnalation>, List<SegnalationDTO>> {
    @Override
    public List<SegnalationDTO> create(List<Segnalation> segnalations) {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<Segnalation, SegnalationDTO> dto = abstractFactory.getDTO("Segnalation");
        List<SegnalationDTO> segnalationDTOS = new ArrayList<>();
        for (Segnalation segnalation: segnalations) {
            segnalationDTOS.add(dto.create(segnalation));
        }
        return segnalationDTOS;
    }
}
