package it.unisalento.se.saw.services;

import it.unisalento.se.saw.domain.Room;
import it.unisalento.se.saw.dto.RoomDTO;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.repositories.RoomRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoomServiceTest {
    @Mock
    RoomRepository roomRepository;

    @InjectMocks
    RoomService roomService;

    @Test
    public void getAllTest() {
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");

        List<Room> rooms = new ArrayList<>();
        rooms.add(room);

        when(roomRepository.findAll()).thenReturn(rooms);

        Set<RoomDTO> roomDTOS = roomService.getAll();

        assertEquals(room.getIdRoom(),(Integer) roomDTOS.iterator().next().getId());


    }

    @Test
    public void getByCapacityTest() {
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");

        List<Room> rooms = new ArrayList<>();
        rooms.add(room);

        when(roomRepository.findRoomsByCapacity(1)).thenReturn(rooms);

        Set<RoomDTO> roomDTOS = roomService.getByCapacity(1);

        assertEquals(room.getIdRoom(),(Integer) roomDTOS.iterator().next().getId());


    }

    @Test
    public void getByIdTest() throws RoomNotFoundException {
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");

        when(roomRepository.getOne(1)).thenReturn(room);

        RoomDTO roomDTO = roomService.getById(1);

        assertEquals(room.getIdRoom(), (Integer) roomDTO.getId());
    }

    @Test
    public void getByIdErrorTest() throws RoomNotFoundException {
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");

        when(roomRepository.getOne(1)).thenReturn(room);

        try{
            RoomDTO roomDTO = roomService.getById(3);
            assertEquals(room.getIdRoom(), (Integer) roomDTO.getId());
            Assert.fail();
        } catch (RoomNotFoundException e) {
            assertEquals("Room not found",e.getMessage());
        }


    }

    @Test
    public void getDomainByIdTest() throws RoomNotFoundException {
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");

        when(roomRepository.getOne(1)).thenReturn(room);

        Room r = roomService.getDomainById(1);

        assertEquals(room,r);
    }

    @Test
    public void saveTest() throws RoomNotFoundException {
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");

        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(1);
        roomDTO.setCapacity(1);
        roomDTO.setName("name");

        when(roomRepository.save(any(Room.class))).thenReturn(room);

        RoomDTO r = roomService.save(roomDTO);

        assertEquals(roomDTO.getId(), r.getId());
    }
}
