package DT210G_Projekt.controller;

import DT210G_Projekt.dto.BookDTO;
import DT210G_Projekt.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Öppen endpoint – inget login krävs
    @GetMapping("/search")
    public List<BookDTO> searchBooks(@RequestParam String query) {
        return bookService.searchBooks(query);
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable String id) {
        return bookService.getBookById(id);
    }
}
