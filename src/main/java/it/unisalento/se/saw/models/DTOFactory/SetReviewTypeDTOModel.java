package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.ReviewType;
import it.unisalento.se.saw.dto.ReviewTypeDTO;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.FactoryProducer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetReviewTypeDTOModel implements DTO<List<ReviewType>, List<ReviewTypeDTO>> {
    @Override
    public List<ReviewTypeDTO> create(List<ReviewType> reviewTypes) {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<ReviewType, ReviewTypeDTO> dto = abstractFactory.getDTO("ReviewType");
        List<ReviewTypeDTO> reviewTypeDTOS = new ArrayList<>();
        for (ReviewType reviewType: reviewTypes) {
            reviewTypeDTOS.add(dto.create(reviewType));
        }
        return reviewTypeDTOS;
    }
}
