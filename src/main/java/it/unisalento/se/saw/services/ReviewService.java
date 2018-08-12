package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.*;
import it.unisalento.se.saw.domain.*;
import it.unisalento.se.saw.dto.ReviewDTO;
import it.unisalento.se.saw.exceptions.ReviewNotFoundException;
import it.unisalento.se.saw.exceptions.ReviewTypeNotFoundException;
import it.unisalento.se.saw.exceptions.StudentNotFoundException;
import it.unisalento.se.saw.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }
    @Transactional
    public Review getById(int id) throws ReviewNotFoundException {
        try{
            return reviewRepository.findReviewById_IdReview(id);
        } catch (Exception e) {
            throw new ReviewNotFoundException();
        }
    }

    @Transactional
    public List<Review> getByType(int idType){
        return reviewRepository.findReviewsById_ReviewTypeIdReviewType(idType);
    }

    @Transactional
    public Review save(ReviewDTO reviewDTO) throws ReviewTypeNotFoundException , StudentNotFoundException{
        Lesson lesson;
        Material material;
        ReviewType reviewType;
        Student student;

        try {
            student = studentServices.getById(reviewDTO.getIdStudent());
        } catch (Exception e) {
            throw new StudentNotFoundException();
        }

        try {
            lesson = lessonServices.getById(reviewDTO.getIdLesson());
        } catch (Exception e) {
            lesson = new Lesson();
        }

        try {
            material = materialServices.getById(reviewDTO.getIdMaterial());
        } catch (Exception e) {
            material = new Material();
        }

        try {
            reviewType = reviewTypeServices.getById(reviewDTO.getIdReviewType());

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

        return reviewRepository.save(review);
    }

    @Transactional
    public Review getByIdStudentAndIdMaterial(int idStudent, int idMaterial) throws ReviewNotFoundException {
        try {
            return reviewRepository.findReviewById_StudentIdStudentAndMaterial_Id_IdMaterial(idStudent,idMaterial);
        } catch (Exception e) {
            throw new ReviewNotFoundException();
        }
    }

    @Transactional
    public Review getByIdStudentAndIdLesson(int idStudent, int idLesson) throws ReviewNotFoundException {
        try {
            return reviewRepository.findReviewById_StudentIdStudentAndLesson_Id_IdLesson(idStudent,idLesson);
        } catch (Exception e) {
            throw new ReviewNotFoundException();
        }
    }
}
