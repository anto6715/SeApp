package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.IProfessorServices;
import it.unisalento.se.saw.Iservices.IRoomServices;
import it.unisalento.se.saw.Iservices.ISegnalationServices;
import it.unisalento.se.saw.Iservices.ISegnalationStateServices;
import it.unisalento.se.saw.domain.*;
import it.unisalento.se.saw.dto.SegnalationDTO;
import it.unisalento.se.saw.dto.SegnalationStateDTO;
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

    @Autowired
    IRoomServices roomServices;

    @Autowired
    IProfessorServices professorServices;

    @Autowired
    ISegnalationStateServices segnalationStateServices;


    AbstractFactory abstractDTOFactory = FactoryProducer.getFactory("DTO");
    DTO<List<Segnalation>, List<SegnalationDTO>> listSegnalationDto = abstractDTOFactory.getDTO("LISTSEGNALATION");
    DTO<Segnalation, SegnalationDTO> segnalationDto = abstractDTOFactory.getDTO("Segnalation");


    @Transactional
    public List<SegnalationDTO> getAll() {
        return listSegnalationDto.create(segnalationRepository.findAll());
    }
    @Transactional
    public List<SegnalationDTO> getByRoom(int id) {
        return listSegnalationDto.create(segnalationRepository.findSegnalationsById_RoomIdRoom(id));
    }
    @Transactional
    public List<SegnalationDTO> getByProfessor(int id){
        return listSegnalationDto.create(segnalationRepository.findSegnalationsById_ProfessorIdProfessor(id));
    }
    @Transactional
    public SegnalationDTO getById(int id) throws SegnalationNotFoundException {
        try {
            return segnalationDto.create(segnalationRepository.findSegnalationById_IdSegnalation(id));
        } catch (Exception e) {
            throw new SegnalationNotFoundException();
        }
    }

    @Transactional
    public SegnalationDTO save(SegnalationDTO segnalationDTO) throws RoomNotFoundException, ProfessorNotFoundException, SegnalationStateNotFoundException{


        Professor professor = professorServices.getDomainById(segnalationDTO.getIdProfessor());
        Room room = roomServices.getDomainById(segnalationDTO.getIdRoom());
        SegnalationState segnalationState = segnalationStateServices.getDomainById(segnalationDTO.getIdState());

        SegnalationId segnalationId = new SegnalationId();
        segnalationId.setProfessorIdProfessor(professor.getId().getIdProfessor());
        segnalationId.setProfessorUserIdUser(professor.getId().getUserIdUser());
        segnalationId.setRoomIdRoom(room.getIdRoom());

        Segnalation segnalation = new Segnalation();
        segnalation.setId(segnalationId);
        segnalation.setNote(segnalationDTO.getNote());
        segnalation.setDescription(segnalationDTO.getDescription());
        segnalation.setRoom(room);
        segnalation.setProfessor(professor);
        segnalation.setSegnalationState(segnalationState);
        return segnalationDto.create(segnalationRepository.save(segnalation));
    }

    @Transactional
    public SegnalationDTO update(SegnalationDTO segnalationDTO) throws SegnalationStateNotFoundException{
        Segnalation segnalation = segnalationRepository.findSegnalationById_IdSegnalation(segnalationDTO.getId());
        SegnalationState segnalationState = segnalationStateServices.getDomainById(segnalationDTO.getIdState());

        segnalation.setNote(segnalationDTO.getNote());
        segnalation.setSegnalationState(segnalationState);

        return segnalationDto.create(segnalationRepository.save(segnalation));
    }
}
