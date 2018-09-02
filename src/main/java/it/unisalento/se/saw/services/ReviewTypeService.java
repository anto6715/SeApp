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

    public Set<ReviewTypeDTO> getAll() {
        DTO<List<ReviewType>, Set<ReviewTypeDTO>> dto = dtoFactory.getDTO("SetReviewType");
        return dto.create(reviewTypeRepository.findAll());
    }
    @Transactional
    public ReviewTypeDTO getById(int id) throws ReviewTypeNotFoundException {
        try {
            DTO<ReviewType, ReviewTypeDTO> dto = dtoFactory.getDTO("ReviewType");
            return dto.create(reviewTypeRepository.getOne(id));
        } catch (Exception e) {
            throw new ReviewTypeNotFoundException();
        }
    }

    @Transactional
    public ReviewType getDomainById(int id) throws ReviewTypeNotFoundException {
        try {
            return reviewTypeRepository.getOne(id);
        } catch (Exception e) {
            throw new ReviewTypeNotFoundException();
        }
    }
    @Transactional
    public ReviewTypeDTO save(ReviewTypeDTO reviewTypeDTO) {
        DTO<ReviewType, ReviewTypeDTO> dto = dtoFactory.getDTO("ReviewType");
        ReviewType reviewType = new ReviewType();
        reviewType.setType(reviewTypeDTO.getType());
        return dto.create(reviewTypeRepository.save(reviewType));
    }
}
