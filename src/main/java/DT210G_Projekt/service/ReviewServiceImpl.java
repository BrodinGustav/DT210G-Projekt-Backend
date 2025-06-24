package DT210G_Projekt.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DT210G_Projekt.model.Review;
import DT210G_Projekt.repository.ReviewRepository;

import org.springframework.security.access.AccessDeniedException;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getReviewsForBook(String bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    @Override
    public List<Review> getReviewsByUser(String userId) {
        Long id = Long.parseLong(userId);
        
        return reviewRepository.findByUserId(id);
    }

    @Override
    public Review saveReview(Review review) {
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

   @Override
public void deleteReview(Long reviewId, Long userId) {
    Review review = reviewRepository.findById(reviewId)
        .orElseThrow(() -> new NoSuchElementException("Recension med id " + reviewId + " finns inte."));
    
    if (review.getUser().equals(userId)) {
        reviewRepository.delete(review);
    } else {
        throw new AccessDeniedException("Du har inte behörighet att radera denna recension.");
    }
}

}
 
