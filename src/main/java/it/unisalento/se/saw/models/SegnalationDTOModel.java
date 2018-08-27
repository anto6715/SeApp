package it.unisalento.se.saw.models;

import it.unisalento.se.saw.domain.Professor;
import it.unisalento.se.saw.domain.Room;
import it.unisalento.se.saw.domain.Segnalation;
import it.unisalento.se.saw.dto.ProfessorDTO;
import it.unisalento.se.saw.dto.RoomDTO;
import it.unisalento.se.saw.dto.SegnalationDTO;

public class SegnalationDTOModel implements DTO<Segnalation, SegnalationDTO> {
    @Override
    public SegnalationDTO create(Segnalation segnalation) {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<Professor, ProfessorDTO> dtoProfessor = abstractFactory.getDTO("Professor");
        DTO<Room, RoomDTO> dtoRoom = abstractFactory.getDTO("Room");
        SegnalationDTO segnalationDTO = new SegnalationDTO();
        segnalationDTO.setDescription(segnalation.getDescription());
        segnalationDTO.setId(segnalation.getId().getIdSegnalation());
        segnalationDTO.setIdState(segnalation.getSegnalationState().getIdSegnalationState());
        segnalationDTO.setProfessorDTO(dtoProfessor.create(segnalation.getProfessor()));
        segnalationDTO.setRoomDTO(dtoRoom.create(segnalation.getRoom()));
        segnalationDTO.setNote(segnalation.getNote());

        return segnalationDTO;
    }
}
