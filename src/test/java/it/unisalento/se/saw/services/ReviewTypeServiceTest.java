package it.unisalento.se.saw.services;

import it.unisalento.se.saw.domain.ReviewType;
import it.unisalento.se.saw.dto.ReviewTypeDTO;
import it.unisalento.se.saw.exceptions.ReviewTypeNotFoundException;
import it.unisalento.se.saw.repositories.ReviewTypeRepository;
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
public class ReviewTypeServiceTest {

    @Mock
    ReviewTypeRepository reviewTypeRepository;

    @InjectMocks
    ReviewTypeService reviewTypeService;

    @Test
    public void getAllTest() {
        ReviewType reviewType = new ReviewType();
        reviewType.setType("type");
        reviewType.setIdReviewType(1);

        List<ReviewType> reviewTypes = new ArrayList<>();
        reviewTypes.add(reviewType);

        when(reviewTypeRepository.findAll()).thenReturn(reviewTypes);

        List<ReviewTypeDTO> reviewTypeDTOS = reviewTypeService.getAll();
        assertEquals(reviewType.getIdReviewType(), reviewTypeDTOS.iterator().next().getId());
    }

    @Test
    public void getByIdTest() throws ReviewTypeNotFoundException {
        ReviewType reviewType = new ReviewType(1);
        reviewType.setType("type");

        when(reviewTypeRepository.getOne(1)).thenReturn(reviewType);

        ReviewTypeDTO reviewTypeDTO = reviewTypeService.getById(1);
        assertEquals(reviewType.getIdReviewType(), reviewTypeDTO.getId());
    }

    @Test
    public void getByIdErrorTest() throws ReviewTypeNotFoundException {
        ReviewType reviewType = new ReviewType(1, "type",null);

        when(reviewTypeRepository.getOne(1)).thenReturn(reviewType);

        try {
            ReviewTypeDTO reviewTypeDTO = reviewTypeService.getById(3);
            assertEquals(reviewType.getIdReviewType(), reviewTypeDTO.getId());
        } catch (ReviewTypeNotFoundException e) {
            assertEquals("ReviewType not found",e.getMessage());
        }

    }


    @Test
    public void getDomainByIdTest() throws ReviewTypeNotFoundException {
        ReviewType reviewType = new ReviewType();
        reviewType.setType("type");
        reviewType.setIdReviewType(1);

        when(reviewTypeRepository.getOne(1)).thenReturn(reviewType);

        ReviewType r = reviewTypeService.getDomainById(1);
        assertEquals(reviewType, r);
    }

    @Test
    public void saveTest() throws ReviewTypeNotFoundException {
        ReviewType reviewType = new ReviewType();
        reviewType.setType("type");
        reviewType.setIdReviewType(1);

        ReviewTypeDTO reviewTypeDTO = new ReviewTypeDTO();
        reviewTypeDTO.setType("type");
        reviewTypeDTO.setId(1);

        when(reviewTypeRepository.save(any(ReviewType.class))).thenReturn(reviewType);

        ReviewTypeDTO r = reviewTypeService.save(reviewTypeDTO);
        assertEquals(reviewTypeDTO.getType(), r.getType());
    }
}
