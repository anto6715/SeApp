package it.unisalento.se.saw.repositories;

import it.unisalento.se.saw.domain.Secretary;
import it.unisalento.se.saw.domain.SecretaryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecretaryRepository extends JpaRepository<Secretary, SecretaryId> {

    public Secretary findSecretaryById_IdSecretary(int id);
}
