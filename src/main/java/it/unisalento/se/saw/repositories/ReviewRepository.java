package it.unisalento.se.saw.repositories;

import it.unisalento.se.saw.domain.Review;
import it.unisalento.se.saw.domain.ReviewId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, ReviewId> {

    public Review findReviewById_IdReview(int id);
    public List<Review> findReviewsById_ReviewTypeIdReviewType(int id);
}
