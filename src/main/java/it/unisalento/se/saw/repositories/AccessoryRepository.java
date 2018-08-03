package it.unisalento.se.saw.repositories;

import it.unisalento.se.saw.domain.Accessory;
import it.unisalento.se.saw.domain.AccessoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, AccessoryId> {

    public List<Accessory> findAccessoriesById_RoomIdRoom(int id);
    public Accessory findAccessoryById_IdAccessory(int id);
}
