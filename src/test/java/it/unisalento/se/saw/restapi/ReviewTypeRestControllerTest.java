package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.IReviewTypeServices;
import it.unisalento.se.saw.dto.ReviewTypeDTO;
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
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ReviewTypeRestControllerTest {

    public static final MediaType APPLICATION_JSON_UTF = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    private MockMvc mockMvc;

    @Mock
    IReviewTypeServices reviewTypeServicesMock;

    @InjectMocks
    ReviewTypeRestController reviewTypeRestController;


    @Before
    public void setUp(){
        mockMvc = Tools.getMockMvc(reviewTypeRestController);
    }

    @Test
    public void getAllTest() throws Exception {
        ReviewTypeDTO reviewTypeDTO = new ReviewTypeDTO();
        reviewTypeDTO.setType("type");
        reviewTypeDTO.setId(1);
        Set<ReviewTypeDTO> reviewTypeDTOS = new HashSet<>(0);
        reviewTypeDTOS.add(reviewTypeDTO);

        when(reviewTypeServicesMock.getAll()).thenReturn(reviewTypeDTOS);

        mockMvc.perform(get("/reviewType/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].type", is("type")));

        verify(reviewTypeServicesMock, times(1)).getAll();
        verifyNoMoreInteractions(reviewTypeServicesMock);
    }

    @Test
    public void getByIdTest() throws Exception {
        ReviewTypeDTO reviewTypeDTO = new ReviewTypeDTO();
        reviewTypeDTO.setType("type");
        reviewTypeDTO.setId(1);

        when(reviewTypeServicesMock.getById(1)).thenReturn(reviewTypeDTO);

        mockMvc.perform(get("/reviewType/getById/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.type", is("type")));

        verify(reviewTypeServicesMock, times(1)).getById(1);
        verifyNoMoreInteractions(reviewTypeServicesMock);
    }

    @Test
    public void saveTest() throws Exception {
        ReviewTypeDTO reviewTypeDTO = new ReviewTypeDTO();
        reviewTypeDTO.setType("type");
        reviewTypeDTO.setId(1);
        String json = Tools.getJson(reviewTypeDTO);

        when(reviewTypeServicesMock.save(any(ReviewTypeDTO.class))).thenReturn(reviewTypeDTO);

        mockMvc.perform(post("/reviewType/save")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.type", is("type")));

        verify(reviewTypeServicesMock, times(1)).save(refEq(reviewTypeDTO));
        verifyNoMoreInteractions(reviewTypeServicesMock);
    }
}
