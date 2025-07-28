package DT210G_Projekt.controller;

import DT210G_Projekt.dto.BookDTO;
import DT210G_Projekt.dto.BookWithReviewsDTO;
import DT210G_Projekt.dto.ReviewDTO;
import DT210G_Projekt.service.BookService;
import DT210G_Projekt.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private ReviewService reviewService;

    // Öppen endpoint – inget login krävs
    @GetMapping("/search")
    public List<BookDTO> searchBooks(@RequestParam String query) {

        //debugg
         System.out.println("SÖKTRÄFF FRÅN: " + query);
         
        return bookService.searchBooks(query);
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable String id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/{bookId}/reviews")
    public BookWithReviewsDTO getBookWithReviews(@PathVariable String bookId) {
     
        // 1. Hämta bokdata från Google Books API (via service)
        BookDTO googleBook = bookService.getBookById(bookId);

        // 2. Hämta recensioner från din databas
        List<ReviewDTO> reviewDTOs = reviewService.getReviewsForBook(bookId);

        // 3. Slå ihop till en DTO
        BookWithReviewsDTO dto = new BookWithReviewsDTO();
        dto.setId(googleBook.getId());
        dto.setTitle(googleBook.getTitle());
        dto.setAuthors(Arrays.asList(googleBook.getAuthors()));
        dto.setPublishedDate(googleBook.getPublishedDate());
        dto.setDescription(googleBook.getDescription());
        dto.setReviews(reviewDTOs);

        return dto;
    }

}
