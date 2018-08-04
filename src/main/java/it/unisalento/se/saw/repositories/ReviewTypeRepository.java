package it.unisalento.se.saw.repositories;

import it.unisalento.se.saw.domain.ReviewType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewTypeRepository extends JpaRepository<ReviewType,Integer> {
}
