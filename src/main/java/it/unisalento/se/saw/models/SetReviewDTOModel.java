package it.unisalento.se.saw.models;

import it.unisalento.se.saw.domain.Review;
import it.unisalento.se.saw.dto.ReviewDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetReviewDTOModel implements DTO<List<Review>, Set<ReviewDTO>> {
    @Override
    public Set<ReviewDTO> create(List<Review> reviews) {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<Review, ReviewDTO> dto = abstractFactory.getDTO("review");
        Set<ReviewDTO> reviewDTOS = new HashSet<>(0);
        for (Review review: reviews) {
            reviewDTOS.add(dto.create(review));
        }
        return reviewDTOS;
    }
}
