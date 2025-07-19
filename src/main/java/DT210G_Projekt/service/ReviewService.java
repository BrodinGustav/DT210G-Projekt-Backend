package DT210G_Projekt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;

import DT210G_Projekt.dto.ReviewDTO;
import DT210G_Projekt.model.Review;

public interface ReviewService {
    List<ReviewDTO> getReviewsForBook(String bookId);
    List<ReviewDTO> getReviewsByUser(String userId);
    Review saveReview(Review review);
    void deleteReview(Long reviewId, Long userId);
    ReviewDTO updateReview(Long id, ReviewDTO dto,  Authentication authentication);
    Optional<ReviewDTO> findById(Long id);
}

