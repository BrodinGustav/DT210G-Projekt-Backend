package DT210G_Projekt.service;

import java.util.List;

import DT210G_Projekt.dto.BookDTO;

public interface BookService {
    List<BookDTO> searchBooks(String query);
    BookDTO getBookById(String bookId);
}

