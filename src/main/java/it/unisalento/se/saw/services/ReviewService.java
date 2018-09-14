package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.*;
import it.unisalento.se.saw.domain.*;
import it.unisalento.se.saw.dto.ReviewDTO;
import it.unisalento.se.saw.exceptions.ReviewNotFoundException;
import it.unisalento.se.saw.exceptions.ReviewTypeNotFoundException;
import it.unisalento.se.saw.exceptions.StudentNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.FactoryProducer;
import it.unisalento.se.saw.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class ReviewService implements IReviewServices {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ILessonServices lessonServices;

    @Autowired
    IMaterialServices materialServices;

    @Autowired
    IReviewTypeServices reviewTypeServices;

    @Autowired
    IStudentServices studentServices;

    AbstractFactory dtoFactory = FactoryProducer.getFactory("DTO");
    DTO<List<Review>, List<ReviewDTO> > listReviewDto = dtoFactory.getDTO("LISTREVIEW");
    DTO<Review, ReviewDTO> reviewDto = dtoFactory.getDTO("Review");

    @Transactional
    public List<ReviewDTO> getAll() {
        return listReviewDto.create(reviewRepository.findAll());
    }
    @Transactional
    public ReviewDTO getById(int id) throws ReviewNotFoundException {
        try{
            return reviewDto.create(reviewRepository.findReviewById_IdReview(id));
        } catch (Exception e) {
            throw new ReviewNotFoundException();
        }
    }

    @Transactional
    public List<ReviewDTO> getByType(int idType){
        return listReviewDto.create(reviewRepository.findReviewsById_ReviewTypeIdReviewType(idType));
    }

    @Transactional
    public ReviewDTO save(ReviewDTO reviewDTO) throws ReviewTypeNotFoundException , StudentNotFoundException{
        Lesson lesson;
        Material material;
        ReviewType reviewType;
        Student student;

        try {
            student = studentServices.getDomainById(reviewDTO.getIdStudent());
        } catch (Exception e) {
            throw new StudentNotFoundException();
        }

        try {
            lesson = lessonServices.getDomainById(reviewDTO.getIdLesson());
        } catch (Exception e) {
            lesson = new Lesson();
        }

        try {
            material = materialServices.getDomainById(reviewDTO.getIdMaterial());
        } catch (Exception e) {
            material = new Material();
        }

        try {
            reviewType = reviewTypeServices.getDomainById(reviewDTO.getIdReviewType());

        } catch (Exception e) {
            throw new ReviewTypeNotFoundException();
        }

        ReviewId reviewId = new ReviewId();
        reviewId.setReviewTypeIdReviewType(reviewType.getIdReviewType());
        reviewId.setStudentIdStudent(student.getId().getIdStudent());
        reviewId.setStudentCourseIdCourse(student.getId().getCourseIdCourse());
        reviewId.setStudentUserIdUser(student.getId().getUserIdUser());


        Review review = new Review();
        review.setNote(reviewDTO.getNote());
        review.setRate(reviewDTO.getRate());
        review.setId(reviewId);
        review.setLesson(lesson);
        review.setMaterial(material);
        review.setReviewType(reviewType);

        System.out.println(review.getId().getReviewTypeIdReviewType());


        return reviewDto.create(reviewRepository.save(review));
    }

    @Transactional
    public ReviewDTO getByIdStudentAndIdMaterial(int idStudent, int idMaterial) throws ReviewNotFoundException {
        Review review = reviewRepository.findReviewById_StudentIdStudentAndMaterial_Id_IdMaterial(idStudent,idMaterial);
        if (review != null)
            return reviewDto.create(review);
        else throw new ReviewNotFoundException();
    }

    @Transactional
    public ReviewDTO getByIdStudentAndIdLesson(int idStudent, int idLesson) throws ReviewNotFoundException {
        Review review = reviewRepository.findReviewById_StudentIdStudentAndLesson_Id_IdLesson(idStudent,idLesson);
        if (review != null)
            return reviewDto.create(review);
        else throw new ReviewNotFoundException();
    }

    @Transactional
    public List<ReviewDTO> getByIdLesson(int id) {
        return listReviewDto.create(reviewRepository.findReviewsByLesson_Id_IdLesson(id));
    }

    @Transactional
    public List<ReviewDTO> getByIdMaterial(int id) {
        return listReviewDto.create(reviewRepository.findReviewsByMaterial_Id_IdMaterial(id));
    }
}
