package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.IMaterialServices;
import it.unisalento.se.saw.Iservices.IRoomServices;
import it.unisalento.se.saw.dto.MaterialDTO;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
public class MaterialRestControllerTest {

    public static final MediaType APPLICATION_JSON_UTF = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    private MockMvc mockMvc;

    @Mock
    IMaterialServices materialServicesMock;

    @InjectMocks
    MaterialRestController materialRestController;


    @Before
    public void setUp(){
        mockMvc = Tools.getMockMvc(materialRestController);
    }

    @Test
    public void getAllTest() throws Exception {
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setDate(null);
        materialDTO.setName("name");
        materialDTO.setLink("link");
        materialDTO.setIdLesson(1);
        materialDTO.setIdUserProf(2);
        materialDTO.setId(3);
        List<MaterialDTO> materialDTOS = new ArrayList<>();
        materialDTOS.add(materialDTO);

        when(materialServicesMock.getAll()).thenReturn(materialDTOS);

        mockMvc.perform(get("/material/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].date", is(nullValue())))
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].link", is("link")))
                .andExpect(jsonPath("$[0].idLesson", is(1)))
                .andExpect(jsonPath("$[0].idUserProf", is(2)))
                .andExpect(jsonPath("$[0].id", is(3)));

        verify(materialServicesMock, times(1)).getAll();
        verifyNoMoreInteractions(materialServicesMock);
    }

    @Test
    public void getByIdLessonTest() throws Exception {
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setDate(null);
        materialDTO.setName("name");
        materialDTO.setLink("link");
        materialDTO.setIdLesson(1);
        materialDTO.setIdUserProf(2);
        materialDTO.setId(3);
        List<MaterialDTO> materialDTOS = new ArrayList<>();
        materialDTOS.add(materialDTO);

        when(materialServicesMock.getByIdLesson(3)).thenReturn(materialDTOS);

        mockMvc.perform(get("/material/getByIdLesson/{id}",3))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].date", is(nullValue())))
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].link", is("link")))
                .andExpect(jsonPath("$[0].idLesson", is(1)))
                .andExpect(jsonPath("$[0].idUserProf", is(2)))
                .andExpect(jsonPath("$[0].id", is(3)));

        verify(materialServicesMock, times(1)).getByIdLesson(3);
        verifyNoMoreInteractions(materialServicesMock);
    }

    @Test
    public void getByIdTeachingTest() throws Exception {
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setDate(null);
        materialDTO.setName("name");
        materialDTO.setLink("link");
        materialDTO.setIdLesson(1);
        materialDTO.setIdUserProf(2);
        materialDTO.setId(3);
        List<MaterialDTO> materialDTOS = new ArrayList<>();
        materialDTOS.add(materialDTO);

        when(materialServicesMock.getByIdTeaching(3)).thenReturn(materialDTOS);

        mockMvc.perform(get("/material/getByIdTeaching/{id}",3))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].date", is(nullValue())))
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].link", is("link")))
                .andExpect(jsonPath("$[0].idLesson", is(1)))
                .andExpect(jsonPath("$[0].idUserProf", is(2)))
                .andExpect(jsonPath("$[0].id", is(3)));

        verify(materialServicesMock, times(1)).getByIdTeaching(3);
        verifyNoMoreInteractions(materialServicesMock);
    }

    @Test
    public void getByIdTest() throws Exception {
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setDate(null);
        materialDTO.setName("name");
        materialDTO.setLink("link");
        materialDTO.setIdLesson(1);
        materialDTO.setIdUserProf(2);
        materialDTO.setId(3);

        when(materialServicesMock.getById(3)).thenReturn(materialDTO);

        mockMvc.perform(get("/material/getById/{id}",3))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.date", is(nullValue())))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.link", is("link")))
                .andExpect(jsonPath("$.idLesson", is(1)))
                .andExpect(jsonPath("$.idUserProf", is(2)))
                .andExpect(jsonPath("$.id", is(3)));

        verify(materialServicesMock, times(1)).getById(3);
        verifyNoMoreInteractions(materialServicesMock);
    }

    @Test
    public void saveTest() throws Exception {
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setDate(null);
        materialDTO.setName("name");
        materialDTO.setLink("link");
        materialDTO.setIdLesson(1);
        materialDTO.setIdUserProf(2);
        materialDTO.setId(3);

        String json = Tools.getJson(materialDTO);
        when(materialServicesMock.save(any(MaterialDTO.class))).thenReturn(materialDTO);

        mockMvc.perform(post("/material/save")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.date", is(nullValue())))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.link", is("link")))
                .andExpect(jsonPath("$.idLesson", is(1)))
                .andExpect(jsonPath("$.idUserProf", is(2)))
                .andExpect(jsonPath("$.id", is(3)));

        verify(materialServicesMock, times(1)).save(refEq(materialDTO));
        verifyNoMoreInteractions(materialServicesMock);
    }
}
