package it.unisalento.se.saw.services;

import it.unisalento.se.saw.domain.Accessory;
import it.unisalento.se.saw.domain.AccessoryId;
import it.unisalento.se.saw.domain.Room;
import it.unisalento.se.saw.dto.AccessoryDTO;
import it.unisalento.se.saw.exceptions.AccessoryNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.repositories.AccessoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccessoryServiceTest {

    @Mock
    AccessoryRepository accessoryRepository;

    @Mock
    RoomService roomService;

    @InjectMocks
    AccessoryService accessoryService;

    @Test
    public void getAllTest() {
        /****************************Room**************************/
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");
        /******************************************************/

        /******************************************************/
        AccessoryId accessoryId = new AccessoryId();
        accessoryId.setRoomIdRoom(1);
        accessoryId.setIdAccessory(1);

        Accessory accessory = new Accessory();
        accessory.setId(accessoryId);
        accessory.setRoom(room);
        accessory.setType("type");
        /******************************************************/

        List<Accessory> accessories = new ArrayList<>();
        accessories.add(accessory);
        when(accessoryRepository.findAll()).thenReturn(accessories);

        Set<AccessoryDTO> accessoryDTOS = accessoryService.getAll();

        assertEquals(accessory.getId().getIdAccessory(), accessoryDTOS.iterator().next().getId());

    }

    @Test
    public void getByIdRoomTest() {
        /****************************Room**************************/
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");
        /******************************************************/

        /******************************************************/
        AccessoryId accessoryId = new AccessoryId();
        accessoryId.setRoomIdRoom(1);
        accessoryId.setIdAccessory(1);

        Accessory accessory = new Accessory();
        accessory.setId(accessoryId);
        accessory.setRoom(room);
        accessory.setType("type");
        /******************************************************/

        List<Accessory> accessories = new ArrayList<>();
        accessories.add(accessory);
        when(accessoryRepository.findAccessoriesById_RoomIdRoom(1)).thenReturn(accessories);

        Set<AccessoryDTO> accessoryDTOS = accessoryService.getByIdRoom(1);

        assertEquals(accessory.getId().getRoomIdRoom(), accessoryDTOS.iterator().next().getIdRoom());

    }

    @Test
    public void getByIdTest() throws AccessoryNotFoundException {
        /****************************Room**************************/
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");
        /******************************************************/

        /******************************************************/
        AccessoryId accessoryId = new AccessoryId(1,1);

        Accessory accessory = new Accessory(accessoryId,room);
        accessory.setType("type");
        /******************************************************/

        when(accessoryRepository.findAccessoryById_IdAccessory(1)).thenReturn(accessory);

        AccessoryDTO accessoryDTO = accessoryService.getById(1);

        assertEquals(accessory.getType(), accessoryDTO.getType());

    }

    @Test
    public void getByIdErrorTest() throws AccessoryNotFoundException {
        /****************************Room**************************/
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");
        /******************************************************/

        /******************************************************/
        AccessoryId accessoryId = new AccessoryId();
        accessoryId.setRoomIdRoom(1);
        accessoryId.setIdAccessory(1);

        Accessory accessory = new Accessory();
        accessory.setId(accessoryId);
        accessory.setRoom(room);
        accessory.setType("type");
        /******************************************************/

        when(accessoryRepository.findAccessoryById_IdAccessory(1)).thenReturn(accessory);

        try {
            AccessoryDTO accessoryDTO = accessoryService.getById(3);
            assertEquals(accessory.getType(), accessoryDTO.getType());
        } catch (AccessoryNotFoundException e) {
            assertEquals("Accessory not found", e.getMessage());
        }



    }

    @Test
    public void saveTest() throws AccessoryNotFoundException, RoomNotFoundException {
        /****************************Room**************************/
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");
        /******************************************************/

        /***********************Accessory*******************************/
        AccessoryId accessoryId = new AccessoryId();
        accessoryId.setRoomIdRoom(1);
        accessoryId.setIdAccessory(1);

        Accessory accessory = new Accessory(accessoryId,room,"type");
        /******************************************************/

        /***********************AccessoryDTO*******************************/
        AccessoryDTO accessoryDTO = new AccessoryDTO();
        accessoryDTO.setIdRoom(1);
        accessoryDTO.setId(1);
        accessoryDTO.setType("type");
        /******************************************************/

        when(roomService.getDomainById(1)).thenReturn(room);
        when(accessoryRepository.save(any(Accessory.class))).thenReturn(accessory);

        AccessoryDTO a = accessoryService.save(accessoryDTO);

        assertEquals(accessoryDTO.getType(), a.getType());

    }
}
