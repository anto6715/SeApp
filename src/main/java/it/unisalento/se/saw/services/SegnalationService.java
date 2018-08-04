package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.IProfessorServices;
import it.unisalento.se.saw.Iservices.IRoomServices;
import it.unisalento.se.saw.Iservices.ISegnalationServices;
import it.unisalento.se.saw.Iservices.ISegnalationStateServices;
import it.unisalento.se.saw.domain.*;
import it.unisalento.se.saw.dto.SegnalationDTO;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.exceptions.SegnalationNotFoundException;
import it.unisalento.se.saw.repositories.SegnalationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public List<Segnalation> getAll() {
        return segnalationRepository.findAll();
    }
    @Transactional
    public List<Segnalation> getByRoom(int id) {
        return segnalationRepository.findSegnalationsById_RoomIdRoom(id);
    }
    @Transactional
    public List<Segnalation> getByProfessor(int id){
        return segnalationRepository.findSegnalationsById_ProfessorIdProfessor(id);
    }
    @Transactional
    public Segnalation getById(int id) throws SegnalationNotFoundException {
        try {
            return segnalationRepository.findSegnalationById_IdSegnalation(id);
        } catch (Exception e) {
            throw new SegnalationNotFoundException();
        }
    }

    @Transactional
    public Segnalation save(SegnalationDTO segnalationDTO) throws ProfessorNotFoundException, RoomNotFoundException {
        try {
            Professor professor = professorServices.getById(segnalationDTO.getIdProfessor());
            Room room = roomServices.getById(segnalationDTO.getIdRoom());
            SegnalationState segnalationState = segnalationStateServices.getById(segnalationDTO.getIdState());



            SegnalationId segnalationId = new SegnalationId();
            segnalationId.setProfessorIdProfessor(professor.getId().getIdProfessor());
            segnalationId.setProfessorUserIdUser(professor.getId().getUserIdUser());
            segnalationId.setRoomIdRoom(room.getIdRoom());
            segnalationId.setSegnalationStateIdSegnalationState(segnalationState.getIdSegnalationState());

            Segnalation segnalation = new Segnalation();
            segnalation.setNote(segnalationDTO.getNote());
            segnalation.setRoom(room);
            segnalation.setId(segnalationId);
            segnalation.setProfessor(professor);
            segnalation.setSegnalationState(segnalationState);

            return segnalationRepository.save(segnalation);
        } catch (Exception e) {
            throw new ProfessorNotFoundException();
        }
    }
    @Transactional
    public void remove(int id) throws SegnalationNotFoundException {
        try {
            Segnalation segnalation = segnalationRepository.findSegnalationById_IdSegnalation(id);
            segnalationRepository.delete(segnalation);
        } catch (Exception e) {
            throw new SegnalationNotFoundException();
        }
    }
}
