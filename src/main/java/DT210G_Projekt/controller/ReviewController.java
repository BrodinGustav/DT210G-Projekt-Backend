package DT210G_Projekt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO reviewDTO, Authentication authentication) {

        System.out.println("== DEBUG: authentication.getName() = " + authentication.getName());
        System.out.println("== DEBUG: authentication.getPrincipal() = " + authentication.getPrincipal());
        System.out.println("== DEBUG: authentication = " + authentication.toString());

        // DEBUG 1: Vad finns i Authentication-objektet?
        System.out.println("== DEBUG: createReview ==");
        System.out.println("Authentication name (från JWT): " + authentication.getName());

        String username = authentication.getName();

        // DEBUG 2: Finns användaren i databasen enligt email?
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> {
                    System.out.println("== FEL: Ingen användare med email: " + username);

                    // DEBUG 3: Lista alla användare i databasen
                    System.out.println("== Befintliga användare i databasen: ==");
                    userRepository.findAll()
                            .forEach(u -> System.out.println(" - " + u.getEmail() + " (id: " + u.getId() + ")"));

                    return new RuntimeException("Användare med användarnamn " + username + " hittades inte");
                });

        // DEBUG 4: Allt ser bra ut, användaren är hämtad
        System.out.println("== Användare hittad: " + user.getEmail() + " (id: " + user.getId() + ")");

        System.out.println("Email från token: " + authentication.getName());

        System.out.println("Söker användare med email: " + username);

        // Skapa Review-entity från DTO
        Review review = new Review();
        review.setBookId(reviewDTO.getBookId());
        review.setRating(reviewDTO.getRating());
        review.setReviewText(reviewDTO.getComment());
        review.setUser(user);

        // DEBUG 5: Innan sparning
        System.out.println("== Sparar recension för bokId: " + review.getBookId() + ", rating: " + review.getRating());

        // Spara Review
        Review savedReview = reviewService.saveReview(review);

        // Mappa tillbaka till DTO för respons
        ReviewDTO savedReviewDTO = new ReviewDTO(
                savedReview.getId(),
                savedReview.getBookId(),
                savedReview.getRating(),
                savedReview.getReviewText(),
                savedReview.getUser() != null ? review.getUser().getId() : null,
                savedReview.getUser() != null ? review.getUser().getEmail() : null);
        // savedReview.getUserId());
        // savedReview.getUser().getEmail());

        // DEBUG 6: Recension sparad
        System.out.println("== Recension sparad med id: " + savedReview.getId());

        return ResponseEntity.ok(savedReviewDTO);
    }

    // Hämta recension via ID
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long id) {
        Optional<ReviewDTO> review = reviewService.findById(id);
        return review.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();

        System.out.println("AUTH: " + authentication.getName());

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Användare ej hittad"));

        reviewService.deleteReview(id, user.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDto,
            Authentication authentication) {
        reviewService.updateReview(id, reviewDto, authentication);
        return ResponseEntity.ok(reviewDto);
    }
    
@GetMapping("/user/{userId}")
public ResponseEntity<List<ReviewDTO>> getReviewsByUser(@PathVariable String userId) {
    List<ReviewDTO> reviews = reviewService.getReviewsByUser(userId);
    return ResponseEntity.ok(reviews);
}


}
