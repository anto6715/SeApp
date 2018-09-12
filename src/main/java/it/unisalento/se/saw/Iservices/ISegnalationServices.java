package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.Segnalation;
import it.unisalento.se.saw.dto.SegnalationDTO;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.exceptions.SegnalationNotFoundException;
import it.unisalento.se.saw.exceptions.SegnalationStateNotFoundException;

import java.util.List;
import java.util.Set;

public interface ISegnalationServices {

    public List<SegnalationDTO> getAll();
    public List<SegnalationDTO> getByRoom(int id);
    public List<SegnalationDTO> getByProfessor(int id);
    public SegnalationDTO getById(int id) throws SegnalationNotFoundException;
    public SegnalationDTO save(SegnalationDTO segnalationDTO) throws ProfessorNotFoundException, RoomNotFoundException, SegnalationStateNotFoundException;
    public SegnalationDTO update(SegnalationDTO segnalationDTO) throws SegnalationStateNotFoundException;
}
