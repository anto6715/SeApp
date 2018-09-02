package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.IReviewServices;
import it.unisalento.se.saw.Iservices.IRoomServices;
import it.unisalento.se.saw.dto.ReviewDTO;
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
public class ReviewRestControllerTest {

    public static final MediaType APPLICATION_JSON_UTF = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    private MockMvc mockMvc;

    @Mock
    IReviewServices reviewServicesMock;

    @InjectMocks
    ReviewRestController reviewRestController;


    @Before
    public void setUp(){
        mockMvc = Tools.getMockMvc(reviewRestController);
    }

    @Test
    public void getByIdTest() throws Exception {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setNote("note");
        reviewDTO.setRate(1);
        reviewDTO.setIdReviewType(2);
        reviewDTO.setId(3);
        reviewDTO.setIdStudent(4);
        reviewDTO.setIdMaterial(5);
        reviewDTO.setIdLesson(6);

        when(reviewServicesMock.getById(1)).thenReturn(reviewDTO);

        mockMvc.perform(get("/review/getById/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.note", is("note")))
                .andExpect(jsonPath("$.rate", is(1)))
                .andExpect(jsonPath("$.idReviewType", is(2)))
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.idStudent", is(4)))
                .andExpect(jsonPath("$.idMaterial", is(5)))
                .andExpect(jsonPath("$.idLesson", is(6)));

        verify(reviewServicesMock, times(1)).getById(1);
        verifyNoMoreInteractions(reviewServicesMock);
    }

    @Test
    public void getByIdStudentAndIdMaterialTest() throws Exception {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setNote("note");
        reviewDTO.setRate(1);
        reviewDTO.setIdReviewType(2);
        reviewDTO.setId(3);
        reviewDTO.setIdStudent(4);
        reviewDTO.setIdMaterial(5);
        reviewDTO.setIdLesson(6);

        when(reviewServicesMock.getByIdStudentAndIdMaterial(4,5)).thenReturn(reviewDTO);

        mockMvc.perform(get("/review/getByIdStudentAndIdMaterial/{idStudent}_{idMaterial}",4,5))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.note", is("note")))
                .andExpect(jsonPath("$.rate", is(1)))
                .andExpect(jsonPath("$.idReviewType", is(2)))
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.idStudent", is(4)))
                .andExpect(jsonPath("$.idMaterial", is(5)))
                .andExpect(jsonPath("$.idLesson", is(6)));

        verify(reviewServicesMock, times(1)).getByIdStudentAndIdMaterial(4,5);
        verifyNoMoreInteractions(reviewServicesMock);
    }

    @Test
    public void getByIdStudentAndIdLessonTest() throws Exception {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setNote("note");
        reviewDTO.setRate(1);
        reviewDTO.setIdReviewType(2);
        reviewDTO.setId(3);
        reviewDTO.setIdStudent(4);
        reviewDTO.setIdMaterial(5);
        reviewDTO.setIdLesson(6);

        when(reviewServicesMock.getByIdStudentAndIdLesson(4,6)).thenReturn(reviewDTO);

        mockMvc.perform(get("/review/getByIdStudentAndIdLesson/{idStudent}_{idLesson}",4,6))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.note", is("note")))
                .andExpect(jsonPath("$.rate", is(1)))
                .andExpect(jsonPath("$.idReviewType", is(2)))
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.idStudent", is(4)))
                .andExpect(jsonPath("$.idMaterial", is(5)))
                .andExpect(jsonPath("$.idLesson", is(6)));

        verify(reviewServicesMock, times(1)).getByIdStudentAndIdLesson(4,6);
        verifyNoMoreInteractions(reviewServicesMock);
    }

    @Test
    public void saveTest() throws Exception {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setNote("note");
        reviewDTO.setRate(1);
        reviewDTO.setIdReviewType(2);
        reviewDTO.setId(3);
        reviewDTO.setIdStudent(4);
        reviewDTO.setIdMaterial(5);
        reviewDTO.setIdLesson(6);
        String json = Tools.getJson(reviewDTO);

        when(reviewServicesMock.save(any(ReviewDTO.class))).thenReturn(reviewDTO);

        mockMvc.perform(post("/review/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.note", is("note")))
                .andExpect(jsonPath("$.rate", is(1)))
                .andExpect(jsonPath("$.idReviewType", is(2)))
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.idStudent", is(4)))
                .andExpect(jsonPath("$.idMaterial", is(5)))
                .andExpect(jsonPath("$.idLesson", is(6)));

        verify(reviewServicesMock, times(1)).save(refEq(reviewDTO));
        verifyNoMoreInteractions(reviewServicesMock);
    }

    @Test
    public void getByIdLessonTest() throws Exception {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setNote("note");
        reviewDTO.setRate(1);
        reviewDTO.setIdReviewType(2);
        reviewDTO.setId(3);
        reviewDTO.setIdStudent(4);
        reviewDTO.setIdMaterial(5);
        reviewDTO.setIdLesson(6);
        Set<ReviewDTO> reviewDTOS = new HashSet<>(0);
        reviewDTOS.add(reviewDTO);

        when(reviewServicesMock.getByIdLesson(6)).thenReturn(reviewDTOS);

        mockMvc.perform(get("/review/getByIdLesson/{idLesson}",6))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].note", is("note")))
                .andExpect(jsonPath("$[0].rate", is(1)))
                .andExpect(jsonPath("$[0].idReviewType", is(2)))
                .andExpect(jsonPath("$[0].id", is(3)))
                .andExpect(jsonPath("$[0].idStudent", is(4)))
                .andExpect(jsonPath("$[0].idMaterial", is(5)))
                .andExpect(jsonPath("$[0].idLesson", is(6)));

        verify(reviewServicesMock, times(1)).getByIdLesson(6);
        verifyNoMoreInteractions(reviewServicesMock);
    }

    @Test
    public void getByIdMaterialTest() throws Exception {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setNote("note");
        reviewDTO.setRate(1);
        reviewDTO.setIdReviewType(2);
        reviewDTO.setId(3);
        reviewDTO.setIdStudent(4);
        reviewDTO.setIdMaterial(5);
        reviewDTO.setIdLesson(6);
        Set<ReviewDTO> reviewDTOS = new HashSet<>(0);
        reviewDTOS.add(reviewDTO);

        when(reviewServicesMock.getByIdMaterial(5)).thenReturn(reviewDTOS);

        mockMvc.perform(get("/review/getByIdMaterial/{idMateria}",5))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].note", is("note")))
                .andExpect(jsonPath("$[0].rate", is(1)))
                .andExpect(jsonPath("$[0].idReviewType", is(2)))
                .andExpect(jsonPath("$[0].id", is(3)))
                .andExpect(jsonPath("$[0].idStudent", is(4)))
                .andExpect(jsonPath("$[0].idMaterial", is(5)))
                .andExpect(jsonPath("$[0].idLesson", is(6)));

        verify(reviewServicesMock, times(1)).getByIdMaterial(5);
        verifyNoMoreInteractions(reviewServicesMock);
    }
}
