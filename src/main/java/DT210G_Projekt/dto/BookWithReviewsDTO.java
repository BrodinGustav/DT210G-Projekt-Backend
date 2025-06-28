package DT210G_Projekt.dto;

import java.util.List;

public class BookWithReviewsDTO {
    private String id;
    private String title;
    private List<String> authors;
    private String publishedDate;
    private String description;
    private List<ReviewDTO> reviews;    //Returnerar ReviewDTO istället för Review för att undvika oändlig JSON-nästning


    
    public BookWithReviewsDTO(String id, String title, List<String> authors, String publishedDate, String description,
            List<ReviewDTO> reviews) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.description = description;
        this.reviews = reviews;
    }

    public BookWithReviewsDTO() {
}

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<String> getAuthors() {
        return authors;
    }
    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
    public String getPublishedDate() {
        return publishedDate;
    }
    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<ReviewDTO> getReviews() {
        return reviews;
    }
    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }


}
