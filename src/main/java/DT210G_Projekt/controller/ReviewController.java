package DT210G_Projekt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DT210G_Projekt.dto.ReviewDTO;
import DT210G_Projekt.model.Review;
import DT210G_Projekt.model.User;
import DT210G_Projekt.repository.UserRepository;
import DT210G_Projekt.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO reviewDTO, Authentication authentication) {

        String username = authentication.getName();

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Användare med användarnamn " + username + " hittades inte"));

        System.out.println("Söker användare med email: " + username);

        // Skapa Review-entity från DTO
        Review review = new Review();
        review.setBookId(reviewDTO.getBookId());
        review.setRating(reviewDTO.getRating());
        review.setReviewText(reviewDTO.getComment());
        review.setUser(user);

        // Spara Review
        Review savedReview = reviewService.saveReview(review);

        // Mappa tillbaka till DTO för respons
        ReviewDTO savedReviewDTO = new ReviewDTO(
                savedReview.getBookId(),
                savedReview.getRating(),
                savedReview.getReviewText());

        return ResponseEntity.ok(savedReviewDTO);
    }

    @GetMapping("/book/{bookId}")
    public List<Review> getReviewsForBook(@PathVariable String bookId) {
        return reviewService.getReviewsForBook(bookId);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Användare ej hittad"));

        reviewService.deleteReview(id, user.getId());
    }
}
