package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.Review;
import it.unisalento.se.saw.dto.ReviewDTO;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.FactoryProducer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetReviewDTOModel implements DTO<List<Review>, List<ReviewDTO>> {
    @Override
    public List<ReviewDTO> create(List<Review> reviews) {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<Review, ReviewDTO> dto = abstractFactory.getDTO("review");
        List<ReviewDTO> reviewDTOS = new ArrayList<>(0);
        for (Review review: reviews) {
            reviewDTOS.add(dto.create(review));
        }
        return reviewDTOS;
    }
}
