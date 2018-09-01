package it.unisalento.se.saw.models.DomainFactory;

import it.unisalento.se.saw.Iservices.IProfessorServices;
import it.unisalento.se.saw.Iservices.IRoomServices;
import it.unisalento.se.saw.Iservices.ISegnalationStateServices;
import it.unisalento.se.saw.domain.*;
import it.unisalento.se.saw.dto.SegnalationDTO;
import it.unisalento.se.saw.dto.SegnalationStateDTO;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.exceptions.SegnalationStateNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.FactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;

public class SegnalationDomainModel implements Domain<SegnalationDTO, Segnalation> {

    @Autowired
    IRoomServices roomServices;

    @Autowired
    IProfessorServices professorServices;

    @Autowired
    ISegnalationStateServices segnalationStateServices;

    AbstractFactory abstractDomainFactory = FactoryProducer.getFactory("DOMAIN");

    @Override
    public Segnalation create(SegnalationDTO segnalationDTO) {
        Domain<SegnalationStateDTO, SegnalationState> segnalationStateDomain = abstractDomainFactory.getDomain("SegnalationState");

        Professor professor = null;
        try {
            professor = professorServices.getById(segnalationDTO.getProfessorDTO().getId());
        } catch (ProfessorNotFoundException e) {
            e.printStackTrace();
        }

        Room room = null;
        try {
            room = roomServices.getById(segnalationDTO.getRoomDTO().getId());
        } catch (RoomNotFoundException e) {
            e.printStackTrace();
        }

        SegnalationState segnalationState = null;
        try {
            segnalationState = segnalationStateDomain.create(segnalationStateServices.getById(segnalationDTO.getIdState()));
        } catch (SegnalationStateNotFoundException e) {
            e.printStackTrace();
        }




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
        return segnalation;
    }
}
