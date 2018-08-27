package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.Review;
import it.unisalento.se.saw.dto.ReviewDTO;

public class ReviewDTOModel implements DTO<Review, ReviewDTO> {
    @Override
    public ReviewDTO create(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId().getIdReview());
        if (review.getLesson() != null) {
            reviewDTO.setIdLesson(review.getLesson().getId().getIdLesson());
        }
        if (review.getMaterial() != null) {
            reviewDTO.setIdMaterial(review.getMaterial().getId().getIdMaterial());
        }
        reviewDTO.setIdStudent(review.getId().getStudentIdStudent());
        reviewDTO.setIdReviewType(review.getId().getReviewTypeIdReviewType());
        reviewDTO.setRate(review.getRate());
        reviewDTO.setNote(review.getNote());
        return reviewDTO;
    }
}
