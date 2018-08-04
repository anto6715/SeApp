package it.unisalento.se.saw.repositories;


import it.unisalento.se.saw.domain.Segnalation;
import it.unisalento.se.saw.domain.SegnalationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SegnalationRepository extends JpaRepository<Segnalation, SegnalationId> {

    public Segnalation findSegnalationById_IdSegnalation(int id);
    public List<Segnalation> findSegnalationsById_ProfessorIdProfessor(int id);
    public List<Segnalation> findSegnalationsById_RoomIdRoom(int id);
}
