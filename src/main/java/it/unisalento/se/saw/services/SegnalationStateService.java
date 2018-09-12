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
    DTO<List<SegnalationState>, List<SegnalationStateDTO>> listSegnalationState = abstractDTOFactory.getDTO("LISTSEGNALATIONSTATE");
    DTO<SegnalationState, SegnalationStateDTO> segnalationDto = abstractDTOFactory.getDTO("SegnalationState");

    @Transactional
    public List<SegnalationStateDTO> getAll() {
        return listSegnalationState.create(segnalationStateRepository.findAll());
    }

    @Transactional
    public SegnalationStateDTO getById(int id) throws SegnalationStateNotFoundException {
        try {
            return segnalationDto.create(segnalationStateRepository.getOne(id));
        } catch (Exception e) {
            throw new SegnalationStateNotFoundException();
        }
    }

    public SegnalationState getDomainById(int id) throws SegnalationStateNotFoundException {
        SegnalationState segnalationState = segnalationStateRepository.getOne(id);
        if (segnalationState != null)
            return segnalationState;
        else
            throw new SegnalationStateNotFoundException();
    }

    @Transactional
    public SegnalationStateDTO save(SegnalationStateDTO segnalationStateDTO) {
        Domain<SegnalationStateDTO, SegnalationState> domain = abstractDomainFactory.getDomain("SegnalationState");
        return segnalationDto.create(segnalationStateRepository.save(domain.create(segnalationStateDTO)));
    }
}
