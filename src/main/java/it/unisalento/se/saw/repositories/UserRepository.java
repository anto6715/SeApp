package it.unisalento.se.saw.repositories;

import it.unisalento.se.saw.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select u from User u where u.name = :name ")
    public User getUserByName(@Param("name") String name);

    public User getUserByNameAndSurname(String name, String surname);

}
