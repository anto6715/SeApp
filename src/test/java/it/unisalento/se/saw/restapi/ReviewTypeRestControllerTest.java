package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.IReviewTypeServices;
import it.unisalento.se.saw.Iservices.IRoomServices;
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
    }
}
