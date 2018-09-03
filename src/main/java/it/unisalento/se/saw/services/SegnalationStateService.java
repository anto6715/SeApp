package it.unisalento.se.saw.services;


import it.unisalento.se.saw.Iservices.ISegnalationStateServices;
import it.unisalento.se.saw.domain.SegnalationState;
import it.unisalento.se.saw.dto.SegnalationStateDTO;
import it.unisalento.se.saw.exceptions.SegnalationStateNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.DomainFactory.Domain;
import it.unisalento.se.saw.models.FactoryProducer;
import it.unisalento.se.saw.repositories.SegnalationStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class SegnalationStateService implements ISegnalationStateServices {

    @Autowired
    SegnalationStateRepository segnalationStateRepository;

    AbstractFactory abstractDTOFactory = FactoryProducer.getFactory("DTO");
    AbstractFactory abstractDomainFactory = FactoryProducer.getFactory("DOMAIN");

    @Transactional
    public Set<SegnalationStateDTO> getAll() {
        DTO<List<SegnalationState>, Set<SegnalationStateDTO>> dto = abstractDTOFactory.getDTO("SetSegnalationState");
        return dto.create(segnalationStateRepository.findAll());
    }

    @Transactional
    public SegnalationStateDTO getById(int id) throws SegnalationStateNotFoundException {
        try {
            DTO<SegnalationState, SegnalationStateDTO> dto = abstractDTOFactory.getDTO("SegnalationState");
            return dto.create(segnalationStateRepository.getOne(id));
        } catch (Exception e) {
            throw new SegnalationStateNotFoundException();
        }
    }

    public SegnalationState getDomainById(int id) throws SegnalationStateNotFoundException {
        return segnalationStateRepository.getOne(id);
    }

    @Transactional
    public SegnalationStateDTO save(SegnalationStateDTO segnalationStateDTO) {
        DTO<SegnalationState, SegnalationStateDTO> dto = abstractDTOFactory.getDTO("SegnalationState");
        Domain<SegnalationStateDTO, SegnalationState> domain = abstractDomainFactory.getDomain("SegnalationState");
        return dto.create(segnalationStateRepository.save(domain.create(segnalationStateDTO)));
    }
}
