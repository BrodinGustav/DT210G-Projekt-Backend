package DT210G_Projekt.service;

import java.util.List;

import DT210G_Projekt.dto.ReviewDTO;
import DT210G_Projekt.model.Review;

public interface ReviewService {
    List<ReviewDTO> getReviewsForBook(String bookId);
    List<Review> getReviewsByUser(String userId);
    Review saveReview(Review review);
    void deleteReview(Long reviewId, Long userId);
}

