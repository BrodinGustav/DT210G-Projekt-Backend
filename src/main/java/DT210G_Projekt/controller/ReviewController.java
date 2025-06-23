package DT210G_Projekt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Review createReview(@RequestBody Review review, @RequestHeader("userId") String userId) {

           User user = userRepository.findById(Long.parseLong(userId))
        .orElseThrow(() -> new RuntimeException("Användare med ID " + userId + " hittades inte"));

        review.setUser(user);
        return reviewService.saveReview(review);
    }

    @GetMapping("/book/{bookId}")
    public List<Review> getReviewsForBook(@PathVariable String bookId) {
        return reviewService.getReviewsForBook(bookId);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id, @RequestHeader("userId") String userId) {
        reviewService.deleteReview(id, userId);
    }
}


