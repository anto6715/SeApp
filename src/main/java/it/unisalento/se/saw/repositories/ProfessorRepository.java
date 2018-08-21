package it.unisalento.se.saw.repositories;

import it.unisalento.se.saw.domain.Professor;
import it.unisalento.se.saw.domain.ProfessorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, ProfessorId> {

    public Professor findProfessorById_UserIdUser(int id);
    public Professor findProfessorById_IdProfessor(int id);
    public Professor findProfessorByUser_NameAndUser_Surname(String name, String surname);
    public List<Professor> findProfessorsByUser_Name(String name);
    public List<Professor> findProfessorsByUser_Surname(String surname);
    public Professor findProfessorByUserUid(String uid);

}
