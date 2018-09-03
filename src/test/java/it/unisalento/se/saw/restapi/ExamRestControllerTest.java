package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.IExamServices;
import it.unisalento.se.saw.Iservices.IRoomServices;
import it.unisalento.se.saw.dto.ExamDTO;
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
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ExamRestControllerTest {

    public static final MediaType APPLICATION_JSON_UTF = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    private MockMvc mockMvc;

    @Mock
    IExamServices examServicesMock;

    @InjectMocks
    ExamRestController examRestController;


    @Before
    public void setUp(){
        mockMvc = Tools.getMockMvc(examRestController);
    }

    @Test
    public void getAllTest() throws Exception {
        ExamDTO examDTO = new ExamDTO();
        examDTO.setTime(null);
        examDTO.setIdTeaching(1);
        examDTO.setTime(null);
        examDTO.setIdRoom(2);
        examDTO.setId(3);
        examDTO.setDate(null);
        Set<ExamDTO> examDTOS = new HashSet<>(0);
        examDTOS.add(examDTO);

        when(examServicesMock.getAll()).thenReturn(examDTOS);

        mockMvc.perform(get("/exam/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].date", is(nullValue())))
                .andExpect(jsonPath("$[0].idTeaching", is(1)))
                .andExpect(jsonPath("$[0].id", is(3)))
                .andExpect(jsonPath("$[0].time", is(nullValue())))
                .andExpect(jsonPath("$[0].idRoom", is(2)))
                .andExpect(jsonPath("$[0].date", is(nullValue())));

        verify(examServicesMock, times(1)).getAll();
        verifyNoMoreInteractions(examServicesMock);
    }

    @Test
    public void getByIdTest() throws Exception {
        ExamDTO examDTO = new ExamDTO();
        examDTO.setTime(null);
        examDTO.setIdTeaching(1);
        examDTO.setTime(null);
        examDTO.setIdRoom(2);
        examDTO.setDate(null);
        examDTO.setId(3);

        when(examServicesMock.getById(3)).thenReturn(examDTO);

        mockMvc.perform(get("/exam/getById/{id}",3))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.date", is(nullValue())))
                .andExpect(jsonPath("$.idTeaching", is(1)))
                .andExpect(jsonPath("$.time", is(nullValue())))
                .andExpect(jsonPath("$.idRoom", is(2)))
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.date", is(nullValue())));

        verify(examServicesMock, times(1)).getById(3);
        verifyNoMoreInteractions(examServicesMock);
    }

    @Test
    public void saveTest() throws Exception {
        ExamDTO examDTO = new ExamDTO();
        examDTO.setTime(null);
        examDTO.setIdTeaching(1);
        examDTO.setTime(null);
        examDTO.setIdRoom(2);
        examDTO.setDate(null);
        examDTO.setId(3);

        String json = Tools.getJson(examDTO);

        when(examServicesMock.save(any(ExamDTO.class))).thenReturn(examDTO);

        mockMvc.perform(post("/exam/save")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.date", is(nullValue())))
                .andExpect(jsonPath("$.idTeaching", is(1)))
                .andExpect(jsonPath("$.time", is(nullValue())))
                .andExpect(jsonPath("$.idRoom", is(2)))
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.date", is(nullValue())));

        verify(examServicesMock, times(1)).save(refEq(examDTO));
        verifyNoMoreInteractions(examServicesMock);
    }
}
