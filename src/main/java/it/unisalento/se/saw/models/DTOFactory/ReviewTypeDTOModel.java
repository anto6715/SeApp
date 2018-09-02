package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.ReviewType;
import it.unisalento.se.saw.dto.ReviewTypeDTO;

public class ReviewTypeDTOModel implements DTO<ReviewType, ReviewTypeDTO> {
    @Override
    public ReviewTypeDTO create(ReviewType reviewType) {
        ReviewTypeDTO reviewTypeDTO = new ReviewTypeDTO();
        reviewTypeDTO.setId(reviewType.getIdReviewType());
        reviewTypeDTO.setType(reviewType.getType());
        return reviewTypeDTO;
    }
}
