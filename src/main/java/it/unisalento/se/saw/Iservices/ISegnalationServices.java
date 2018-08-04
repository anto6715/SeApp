package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.Segnalation;
import it.unisalento.se.saw.dto.SegnalationDTO;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.exceptions.SegnalationNotFoundException;

import java.util.List;

public interface ISegnalationServices {

    public List<Segnalation> getAll();
    public List<Segnalation> getByRoom(int id);
    public List<Segnalation> getByProfessor(int id);
    public Segnalation getById(int id) throws SegnalationNotFoundException;
    public Segnalation save(SegnalationDTO segnalationDTO) throws ProfessorNotFoundException, RoomNotFoundException;
    public void remove(int id) throws SegnalationNotFoundException;
}
