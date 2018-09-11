package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.Professor;
import it.unisalento.se.saw.domain.Room;
import it.unisalento.se.saw.domain.Segnalation;
import it.unisalento.se.saw.domain.SegnalationState;
import it.unisalento.se.saw.dto.ProfessorDTO;
import it.unisalento.se.saw.dto.RoomDTO;
import it.unisalento.se.saw.dto.SegnalationDTO;
import it.unisalento.se.saw.dto.SegnalationStateDTO;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.FactoryProducer;

public class SegnalationDTOModel implements DTO<Segnalation, SegnalationDTO> {
    @Override
    public SegnalationDTO create(Segnalation segnalation) {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<Professor, ProfessorDTO> dtoProfessor = abstractFactory.getDTO("Professor");
        DTO<Room, RoomDTO> dtoRoom = abstractFactory.getDTO("Room");
        DTO<SegnalationState, SegnalationStateDTO> dtoSegnalationState = abstractFactory.getDTO("SegnalationState");
        SegnalationDTO segnalationDTO = new SegnalationDTO();
        segnalationDTO.setDescription(segnalation.getDescription());
        segnalationDTO.setId(segnalation.getId().getIdSegnalation());
        segnalationDTO.setIdState(segnalation.getSegnalationState().getIdSegnalationState());
        segnalationDTO.setProfessorDTO(dtoProfessor.create(segnalation.getProfessor()));
        segnalationDTO.setRoomDTO(dtoRoom.create(segnalation.getRoom()));
        segnalationDTO.setSegnalationStateDTO(dtoSegnalationState.create(segnalation.getSegnalationState()));
        segnalationDTO.setNote(segnalation.getNote());

        return segnalationDTO;
    }
}
