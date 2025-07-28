package DT210G_Projekt.service;


import com.fasterxml.jackson.databind.JsonNode;

import DT210G_Projekt.dto.BookDTO;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    private final RestTemplate restTemplate;

    public BookServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<BookDTO> searchBooks(String query) {
        String url = "https://www.googleapis.com/books/v1/volumes?q=" + query;
        JsonNode root = restTemplate.getForObject(url, JsonNode.class);
        List<BookDTO> results = new ArrayList<>();

        JsonNode items = root.get("items");
        if (items != null && items.isArray()) {
            for (JsonNode item : items) {
                results.add(mapToBookDto(item));
            }
        }

        return results;
    }

    @Override
    public BookDTO getBookById(String bookId) {
        String url = "https://www.googleapis.com/books/v1/volumes/" + bookId;
        JsonNode item = restTemplate.getForObject(url, JsonNode.class);
        return mapToBookDto(item);
    }

    private BookDTO mapToBookDto(JsonNode item) {
        JsonNode volumeInfo = item.get("volumeInfo");

        BookDTO book = new BookDTO();
        book.setId(item.get("id").asText());
        book.setTitle(volumeInfo.path("title").asText(""));
        book.setAuthors(volumeInfo.has("authors")
                ? StreamSupport.stream(volumeInfo.get("authors").spliterator(), false)
                    .map(JsonNode::asText)
                    .toArray(String[]::new)
                : new String[0]);
        book.setPublishedDate(volumeInfo.path("publishedDate").asText(""));
        book.setDescription(volumeInfo.path("description").asText(""));
    
        return book;
    }
}
 
    
