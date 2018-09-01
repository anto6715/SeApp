package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.ISegnalationServices;
import it.unisalento.se.saw.domain.*;
import it.unisalento.se.saw.dto.SegnalationDTO;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.exceptions.SegnalationNotFoundException;
import it.unisalento.se.saw.exceptions.SegnalationStateNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.DomainFactory.Domain;
import it.unisalento.se.saw.models.FactoryProducer;
import it.unisalento.se.saw.repositories.SegnalationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class SegnalationService implements ISegnalationServices {

    @Autowired
    SegnalationRepository segnalationRepository;



    AbstractFactory abstractDomainFactory = FactoryProducer.getFactory("DOMAIN");
    AbstractFactory abstractDTOFactory = FactoryProducer.getFactory("DTO");

    @Transactional
    public Set<SegnalationDTO> getAll() {
        DTO<List<Segnalation>, Set<SegnalationDTO>> dto = abstractDTOFactory.getDTO("SetSegnalation");
        return dto.create(segnalationRepository.findAll());
    }
    @Transactional
    public Set<SegnalationDTO> getByRoom(int id) {
        DTO<List<Segnalation>, Set<SegnalationDTO>> dto = abstractDTOFactory.getDTO("SetSegnalation");
        return dto.create(segnalationRepository.findSegnalationsById_RoomIdRoom(id));
    }
    @Transactional
    public Set<SegnalationDTO> getByProfessor(int id){
        DTO<List<Segnalation>, Set<SegnalationDTO>> dto = abstractDTOFactory.getDTO("SetSegnalation");
        return dto.create(segnalationRepository.findSegnalationsById_ProfessorIdProfessor(id));
    }
    @Transactional
    public SegnalationDTO getById(int id) throws SegnalationNotFoundException {
        try {
            DTO<Segnalation, SegnalationDTO> dto = abstractDTOFactory.getDTO("Segnalation");
            return dto.create(segnalationRepository.findSegnalationById_IdSegnalation(id));
        } catch (Exception e) {
            throw new SegnalationNotFoundException();
        }
    }

    @Transactional
    public SegnalationDTO save(SegnalationDTO segnalationDTO) throws ProfessorNotFoundException, RoomNotFoundException, SegnalationStateNotFoundException {
        Domain<SegnalationDTO, Segnalation> segnalationDomain = abstractDomainFactory.getDomain("Segnalation");
        DTO<Segnalation, SegnalationDTO> dto = abstractDTOFactory.getDTO("Segnalation");
        return dto.create(segnalationRepository.save(segnalationDomain.create(segnalationDTO)));
    }
}
