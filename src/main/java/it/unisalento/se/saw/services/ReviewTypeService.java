package it.unisalento.se.saw.services;


import it.unisalento.se.saw.Iservices.IReviewTypeServices;
import it.unisalento.se.saw.domain.ReviewType;
import it.unisalento.se.saw.dto.ReviewTypeDTO;
import it.unisalento.se.saw.exceptions.ReviewTypeNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.FactoryProducer;
import it.unisalento.se.saw.repositories.ReviewTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class ReviewTypeService implements IReviewTypeServices {

    @Autowired
    ReviewTypeRepository reviewTypeRepository;

    AbstractFactory dtoFactory = FactoryProducer.getFactory("DTO");
    DTO<List<ReviewType>, List<ReviewTypeDTO>> listReviewTypeDto = dtoFactory.getDTO("LISTREVIEWTYPE");
    DTO<ReviewType, ReviewTypeDTO> reviewTypeDto = dtoFactory.getDTO("ReviewType");


    public List<ReviewTypeDTO> getAll() {
        return listReviewTypeDto.create(reviewTypeRepository.findAll());
    }
    @Transactional
    public ReviewTypeDTO getById(int id) throws ReviewTypeNotFoundException {
        try {
            return reviewTypeDto.create(reviewTypeRepository.getOne(id));
        } catch (Exception e) {
            throw new ReviewTypeNotFoundException();
        }
    }

    @Transactional
    public ReviewType getDomainById(int id) throws ReviewTypeNotFoundException {
        return reviewTypeRepository.getOne(id);
    }

    @Transactional
    public ReviewTypeDTO save(ReviewTypeDTO reviewTypeDTO) {
        ReviewType reviewType = new ReviewType();
        reviewType.setType(reviewTypeDTO.getType());
        return reviewTypeDto.create(reviewTypeRepository.save(reviewType));
    }
}
