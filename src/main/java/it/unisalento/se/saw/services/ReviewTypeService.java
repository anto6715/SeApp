package it.unisalento.se.saw.services;


import it.unisalento.se.saw.Iservices.IReviewTypeServices;
import it.unisalento.se.saw.domain.ReviewType;
import it.unisalento.se.saw.dto.ReviewTypeDTO;
import it.unisalento.se.saw.exceptions.ReviewTypeNotFoundException;
import it.unisalento.se.saw.repositories.ReviewTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewTypeService implements IReviewTypeServices {

    @Autowired
    ReviewTypeRepository reviewTypeRepository;

    public List<ReviewType> getAll() {
        return reviewTypeRepository.findAll();
    }
    @Transactional
    public ReviewType getById(int id) throws ReviewTypeNotFoundException {
        try {
            return reviewTypeRepository.getOne(id);
        } catch (Exception e) {
            throw new ReviewTypeNotFoundException();
        }
    }
    @Transactional
    public ReviewType save(ReviewTypeDTO reviewTypeDTO) {
        ReviewType reviewType = new ReviewType();
        reviewType.setType(reviewTypeDTO.getType());
        return reviewTypeRepository.save(reviewType);
    }
    @Transactional
    public void remove(int id) throws ReviewTypeNotFoundException {
        try {
            ReviewType reviewType = reviewTypeRepository.getOne(id);
            reviewTypeRepository.delete(reviewType);
        } catch (Exception e) {
            throw new ReviewTypeNotFoundException();
        }

    }
}
