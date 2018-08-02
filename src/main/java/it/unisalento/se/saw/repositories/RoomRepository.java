package it.unisalento.se.saw.repositories;


import it.unisalento.se.saw.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    public Room findRoomByName(String name);
    public List<Room> findRoomsByCapacity(int capacity);
}
