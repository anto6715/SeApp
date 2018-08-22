package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.Review;
import it.unisalento.se.saw.dto.ReviewDTO;
import it.unisalento.se.saw.exceptions.ReviewNotFoundException;
import it.unisalento.se.saw.exceptions.ReviewTypeNotFoundException;
import it.unisalento.se.saw.exceptions.StudentNotFoundException;

import java.util.List;

public interface IReviewServices {

    public List<Review> getAll();
    public Review getById(int id) throws ReviewNotFoundException;
    public List<Review> getByType(int idType);
    public Review save(ReviewDTO reviewDTO) throws ReviewTypeNotFoundException, StudentNotFoundException;
    public Review getByIdStudentAndIdMaterial(int idStudent, int idMaterial) throws ReviewNotFoundException;
    public Review getByIdStudentAndIdLesson(int idStudent, int idLesson) throws ReviewNotFoundException;
    public List<Review> getByIdLesson(int id);

}
