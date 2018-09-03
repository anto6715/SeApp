package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.IAccessoryServices;
import it.unisalento.se.saw.Iservices.IRoomServices;
import it.unisalento.se.saw.dto.AccessoryDTO;
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
public class AccessoryRestControllerTest {

    public static final MediaType APPLICATION_JSON_UTF = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    private MockMvc mockMvc;

    @Mock
    IAccessoryServices accessoryServicesMock;

    @InjectMocks
    AccessoryRestController accessoryRestController;


    @Before
    public void setUp(){
        mockMvc = Tools.getMockMvc(accessoryRestController);
    }

    @Test
    public void getAllTest() throws Exception {
        AccessoryDTO accessoryDTO = new AccessoryDTO();
        accessoryDTO.setId(1);
        accessoryDTO.setType("type");
        accessoryDTO.setIdRoom(2);
        Set<AccessoryDTO> accessoryDTOS = new HashSet<>(0);
        accessoryDTOS.add(accessoryDTO);

        when(accessoryServicesMock.getAll()).thenReturn(accessoryDTOS);

        mockMvc.perform(get("/accessory/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].type", is("type")))
                .andExpect(jsonPath("$[0].idRoom", is(2)));

        verify(accessoryServicesMock, times(1)).getAll();
        verifyNoMoreInteractions(accessoryServicesMock);
    }

    @Test
    public void getByIdRoomTest() throws Exception {
        AccessoryDTO accessoryDTO = new AccessoryDTO();
        accessoryDTO.setId(1);
        accessoryDTO.setType("type");
        accessoryDTO.setIdRoom(2);
        Set<AccessoryDTO> accessoryDTOS = new HashSet<>(0);
        accessoryDTOS.add(accessoryDTO);

        when(accessoryServicesMock.getByIdRoom(2)).thenReturn(accessoryDTOS);

        mockMvc.perform(get("/accessory/getByIdRoom/{id}",2))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].type", is("type")))
                .andExpect(jsonPath("$[0].idRoom", is(2)));

        verify(accessoryServicesMock, times(1)).getByIdRoom(2);
        verifyNoMoreInteractions(accessoryServicesMock);
    }

    @Test
    public void getByIdTest() throws Exception {
        AccessoryDTO accessoryDTO = new AccessoryDTO();
        accessoryDTO.setId(1);
        accessoryDTO.setType("type");
        accessoryDTO.setIdRoom(2);

        when(accessoryServicesMock.getById(1)).thenReturn(accessoryDTO);

        mockMvc.perform(get("/accessory/getById/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.type", is("type")))
                .andExpect(jsonPath("$.idRoom", is(2)));

        verify(accessoryServicesMock, times(1)).getById(1);
        verifyNoMoreInteractions(accessoryServicesMock);
    }

    @Test
    public void saveTest() throws Exception {
        AccessoryDTO accessoryDTO = new AccessoryDTO();
        accessoryDTO.setId(1);
        accessoryDTO.setType("type");
        accessoryDTO.setIdRoom(2);

        String json = Tools.getJson(accessoryDTO);

        when(accessoryServicesMock.save(any(AccessoryDTO.class))).thenReturn(accessoryDTO);

        mockMvc.perform(post("/accessory/save")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.type", is("type")))
                .andExpect(jsonPath("$.idRoom", is(2)));

        verify(accessoryServicesMock, times(1)).save(refEq(accessoryDTO));
        verifyNoMoreInteractions(accessoryServicesMock);
    }
}
