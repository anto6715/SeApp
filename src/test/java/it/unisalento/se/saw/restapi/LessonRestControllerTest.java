package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.ILessonServices;
import it.unisalento.se.saw.dto.LessonDTO;
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
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class LessonRestControllerTest {

    public static final MediaType APPLICATION_JSON_UTF = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    private MockMvc mockMvc;

    @Mock
    ILessonServices lessonServicesMock;

    @InjectMocks
    LessonRestController lessonRestController;


    @Before
    public void setUp(){
        mockMvc = Tools.getMockMvc(lessonRestController);
    }

    @Test
    public void getAllTest() throws Exception {
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setStart(null);
        lessonDTO.setTeachingDTO(null);
        lessonDTO.setIdTeaching(1);
        lessonDTO.setIdRoom(2);
        Set<LessonDTO> lessonDTOS = new HashSet<>(0);
        lessonDTOS.add(lessonDTO);

        when(lessonServicesMock.getAll()).thenReturn(lessonDTOS);

        mockMvc.perform(get("/lesson/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].start", is(nullValue())))
                .andExpect(jsonPath("$[0].teachingDTO", is(nullValue())))
                .andExpect(jsonPath("$[0].idTeaching", is(1)))
                .andExpect(jsonPath("$[0].idRoom", is(2)));

        verify(lessonServicesMock, times(1)).getAll();
        verifyNoMoreInteractions(lessonServicesMock);
    }

    @Test
    public void getByIdRoomTest() throws Exception {
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setStart(null);
        lessonDTO.setTeachingDTO(null);
        lessonDTO.setIdTeaching(1);
        lessonDTO.setIdRoom(2);
        Set<LessonDTO> lessonDTOS = new HashSet<>(0);
        lessonDTOS.add(lessonDTO);

        when(lessonServicesMock.getByRoom(2)).thenReturn(lessonDTOS);

        mockMvc.perform(get("/lesson/getByIdRoom/{id}",2))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].start", is(nullValue())))
                .andExpect(jsonPath("$[0].teachingDTO", is(nullValue())))
                .andExpect(jsonPath("$[0].idTeaching", is(1)))
                .andExpect(jsonPath("$[0].idRoom", is(2)));

        verify(lessonServicesMock, times(1)).getByRoom(2);
        verifyNoMoreInteractions(lessonServicesMock);
    }

    @Test
    public void getByIdTest() throws Exception {
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setStart(null);
        lessonDTO.setTeachingDTO(null);
        lessonDTO.setIdTeaching(1);
        lessonDTO.setIdRoom(2);
        lessonDTO.setId(3);

        when(lessonServicesMock.getById(3)).thenReturn(lessonDTO);

        mockMvc.perform(get("/lesson/getById/{id}",3))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.start", is(nullValue())))
                .andExpect(jsonPath("$.teachingDTO", is(nullValue())))
                .andExpect(jsonPath("$.idTeaching", is(1)))
                .andExpect(jsonPath("$.idRoom", is(2)));

        verify(lessonServicesMock, times(1)).getById(3);
        verifyNoMoreInteractions(lessonServicesMock);
    }

    @Test
    public void saveTest() throws Exception {
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setStart(null);
        lessonDTO.setTeachingDTO(null);
        lessonDTO.setIdTeaching(1);
        lessonDTO.setIdRoom(2);
        lessonDTO.setId(3);

        String json = Tools.getJson(lessonDTO);

        when(lessonServicesMock.save(any(LessonDTO.class))).thenReturn(lessonDTO);

        mockMvc.perform(post("/lesson/save")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.start", is(nullValue())))
                .andExpect(jsonPath("$.teachingDTO", is(nullValue())))
                .andExpect(jsonPath("$.idTeaching", is(1)))
                .andExpect(jsonPath("$.idRoom", is(2)));

        verify(lessonServicesMock, times(1)).save(refEq(lessonDTO));
        verifyNoMoreInteractions(lessonServicesMock);
    }

    @Test
    public void updateTest() throws Exception {
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setStart(null);
        lessonDTO.setTeachingDTO(null);
        lessonDTO.setIdTeaching(1);
        lessonDTO.setIdRoom(2);
        lessonDTO.setId(3);

        String json = Tools.getJson(lessonDTO);

        when(lessonServicesMock.update(any(LessonDTO.class))).thenReturn(lessonDTO);

        mockMvc.perform(post("/lesson/update")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.start", is(nullValue())))
                .andExpect(jsonPath("$.teachingDTO", is(nullValue())))
                .andExpect(jsonPath("$.idTeaching", is(1)))
                .andExpect(jsonPath("$.idRoom", is(2)));

        verify(lessonServicesMock, times(1)).update(refEq(lessonDTO));
        verifyNoMoreInteractions(lessonServicesMock);
    }

}
