package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.dto.ReviewDTO;
import it.unisalento.se.saw.exceptions.ReviewNotFoundException;
import it.unisalento.se.saw.exceptions.ReviewTypeNotFoundException;
import it.unisalento.se.saw.exceptions.StudentNotFoundException;

import java.util.Set;

public interface IReviewServices {

    public Set<ReviewDTO> getAll();
    public ReviewDTO getById(int id) throws ReviewNotFoundException;
    public Set<ReviewDTO> getByType(int idType);
    public ReviewDTO save(ReviewDTO reviewDTO) throws ReviewTypeNotFoundException, StudentNotFoundException;
    public ReviewDTO getByIdStudentAndIdMaterial(int idStudent, int idMaterial) throws ReviewNotFoundException;
    public ReviewDTO getByIdStudentAndIdLesson(int idStudent, int idLesson) throws ReviewNotFoundException;
    public Set<ReviewDTO> getByIdLesson(int id);
    public Set<ReviewDTO> getByIdMaterial(int id);

}
