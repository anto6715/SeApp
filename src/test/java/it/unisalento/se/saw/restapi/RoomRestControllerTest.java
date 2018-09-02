package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.IRoomServices;
import it.unisalento.se.saw.dto.RoomDTO;
import it.unisalento.se.saw.tools.Tools;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class RoomRestControllerTest {

    public static final MediaType APPLICATION_JSON_UTF = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    private MockMvc mockMvc;

    @Mock
    IRoomServices roomServicesMock;

    @InjectMocks
    RoomRestController roomRestController;


    @Before
    public void setUp(){
        mockMvc = Tools.getMockMvc(roomRestController);
    }

    @Test
    public void getAllTest() throws Exception {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setName("name");
        roomDTO.setLocation("lecce");
        roomDTO.setLongitude(1.025);
        roomDTO.setLatitude(0.568);
        roomDTO.setCapacity(100);
        roomDTO.setId(1);
        Set<RoomDTO> roomDTOS = new HashSet<>(0);
        roomDTOS.add(roomDTO);

        when(roomServicesMock.getAll()).thenReturn(roomDTOS);

        mockMvc.perform(get("/room/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].capacity", is(100)))
                .andExpect(jsonPath("$[0].latitude", is(0.568)))
                .andExpect(jsonPath("$[0].longitude", is(1.025)))
                .andExpect(jsonPath("$[0].location", is("lecce")))
                .andExpect(jsonPath("$[0].name", is("name")));

        verify(roomServicesMock, times(1)).getAll();
        verifyNoMoreInteractions(roomServicesMock);
    }

    @Test
    public void saveTest() throws Exception{
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setName("name");
        roomDTO.setLocation("lecce");
        roomDTO.setLongitude(1.025);
        roomDTO.setLatitude(0.568);
        roomDTO.setCapacity(100);
        roomDTO.setId(1);
        String json = Tools.getJson(roomDTO);

        when(roomServicesMock.save(any(RoomDTO.class))).thenReturn(roomDTO);

        mockMvc.perform(post("/room/save")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.capacity", is(100)))
                .andExpect(jsonPath("$.latitude", is(0.568)))
                .andExpect(jsonPath("$.longitude", is(1.025)))
                .andExpect(jsonPath("$.location", is("lecce")))
                .andExpect(jsonPath("$.name", is("name")));

        verify(roomServicesMock, times(1)).save(refEq(roomDTO));
        verifyNoMoreInteractions(roomServicesMock);
    }

    @Test
    public void getByIdTest() throws Exception {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setName("name");
        roomDTO.setLocation("lecce");
        roomDTO.setLongitude(1.025);
        roomDTO.setLatitude(0.568);
        roomDTO.setCapacity(100);
        roomDTO.setId(1);

        when(roomServicesMock.getById(1)).thenReturn(roomDTO);

        mockMvc.perform(get("/room/getById/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.capacity", is(100)))
                .andExpect(jsonPath("$.latitude", is(0.568)))
                .andExpect(jsonPath("$.longitude", is(1.025)))
                .andExpect(jsonPath("$.location", is("lecce")))
                .andExpect(jsonPath("$.name", is("name")));

        verify(roomServicesMock, times(1)).getById(1);
        verifyNoMoreInteractions(roomServicesMock);
    }

    @Test
    public void getByCapacity() throws Exception{
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setName("name");
        roomDTO.setLocation("lecce");
        roomDTO.setLongitude(1.025);
        roomDTO.setLatitude(0.568);
        roomDTO.setCapacity(100);
        roomDTO.setId(1);
        Set<RoomDTO> roomDTOS = new HashSet<>(0);
        roomDTOS.add(roomDTO);

        when(roomServicesMock.getByCapacity(100)).thenReturn(roomDTOS);

        mockMvc.perform(get("/room/getByCapacity/{capacity}",100))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].capacity", is(100)))
                .andExpect(jsonPath("$[0].latitude", is(0.568)))
                .andExpect(jsonPath("$[0].longitude", is(1.025)))
                .andExpect(jsonPath("$[0].location", is("lecce")))
                .andExpect(jsonPath("$[0].name", is("name")));

        verify(roomServicesMock, times(1)).getByCapacity(100);
        verifyNoMoreInteractions(roomServicesMock);
    }
}
