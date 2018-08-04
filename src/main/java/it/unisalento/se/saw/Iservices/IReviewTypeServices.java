package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.ReviewType;
import it.unisalento.se.saw.dto.ReviewTypeDTO;
import it.unisalento.se.saw.exceptions.ReviewTypeNotFoundException;

import java.util.List;

public interface IReviewTypeServices {

    public List<ReviewType> getAll();
    public ReviewType getById(int id) throws ReviewTypeNotFoundException;
    public ReviewType save(ReviewTypeDTO reviewTypeDTO);
    public void remove(int id) throws ReviewTypeNotFoundException;
}
