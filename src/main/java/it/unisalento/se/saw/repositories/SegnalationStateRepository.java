package it.unisalento.se.saw.repositories;

import it.unisalento.se.saw.domain.SegnalationState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegnalationStateRepository extends JpaRepository<SegnalationState,Integer> {
}
