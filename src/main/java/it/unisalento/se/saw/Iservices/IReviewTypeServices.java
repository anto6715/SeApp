package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.ReviewType;
import it.unisalento.se.saw.dto.ReviewTypeDTO;
import it.unisalento.se.saw.exceptions.ReviewTypeNotFoundException;

import java.util.List;
import java.util.Set;

public interface IReviewTypeServices {

    public List<ReviewTypeDTO> getAll();
    public ReviewTypeDTO getById(int id) throws ReviewTypeNotFoundException;
    public ReviewTypeDTO save(ReviewTypeDTO reviewTypeDTO);
    public ReviewType getDomainById(int id) throws ReviewTypeNotFoundException;
}
