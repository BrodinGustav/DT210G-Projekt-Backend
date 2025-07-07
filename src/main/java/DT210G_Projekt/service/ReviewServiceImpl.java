package DT210G_Projekt.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DT210G_Projekt.dto.ReviewDTO;
import DT210G_Projekt.model.Review;
import DT210G_Projekt.model.User;
import DT210G_Projekt.repository.ReviewRepository;
import DT210G_Projekt.repository.UserRepository;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ReviewDTO> getReviewsForBook(String bookId) {
        List<Review> reviews = reviewRepository.findByBookId(bookId);

        return reviews.stream()
                .map(review -> new ReviewDTO(
                        review.getId(),
                        review.getBookId(),
                        review.getRating(),
                        review.getReviewText(),
                        review.getUser() != null ? review.getUser().getId() : null,
                        review.getUser() != null ? review.getUser().getEmail() : null
                // review.getUser().getEmail() // om ReviewDTO har detta fält aktivt
                ))
                .collect(Collectors.toList());
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
    public Optional<ReviewDTO> findById(Long id) {
        return reviewRepository.findById(id).map(review -> {
            ReviewDTO dto = new ReviewDTO();
            dto.setId(review.getId());
            dto.setBookId(review.getBookId());
            dto.setComment(review.getReviewText());
            dto.setRating(review.getRating());
            dto.setUserEmail(review.getUser().getEmail());
            return dto;
        });
    }

    @Override
    public void deleteReview(Long reviewId, Long userId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NoSuchElementException("Recension med id " + reviewId + " finns inte."));

        if (review.getUser().getId().equals(userId)) {
            reviewRepository.delete(review);
        } else {
            throw new AccessDeniedException("Du har inte behörighet att radera denna recension.");
        }
    }

    @Override
    public ReviewDTO updateReview(Long id, ReviewDTO reviewDto, Authentication authentication) {

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Recension med id " + id + " finns inte."));

        // Hämta inloggad användare
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Ingen användare hittades"));

        // Kontrollera att användaren äger recensionen
        if (!review.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("Du får inte ändra någon annans recension.");
        }

        // Uppdatera recensionen
        review.setRating(reviewDto.getRating());
        review.setReviewText(reviewDto.getComment());

        Review updatedReview = reviewRepository.save(review);

        return new ReviewDTO(
                updatedReview.getId(),
                updatedReview.getBookId(),
                updatedReview.getRating(),
                updatedReview.getReviewText(),
                updatedReview.getUser() != null ? review.getUser().getId() : null,
                updatedReview.getUser() != null ? review.getUser().getEmail() : null
        // updatedReview.getUserId()
        );

    }
}
