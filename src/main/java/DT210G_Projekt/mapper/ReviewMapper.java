package DT210G_Projekt.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import DT210G_Projekt.dto.ReviewDTO;
import DT210G_Projekt.model.Review;

@Component
public class ReviewMapper {
    public ReviewDTO toDTO(Review review) {
        return new ReviewDTO (
            review.getId(),
             review.getBookId(),  
            review.getRating(),
            review.getReviewText(),
            review.getUser().getId(),
            review.getUser().getEmail()
        );
    }

     public List<ReviewDTO> toDTOList(List<Review> reviews) {
        return reviews.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
