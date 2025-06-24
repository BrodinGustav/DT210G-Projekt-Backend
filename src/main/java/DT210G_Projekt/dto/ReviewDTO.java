package DT210G_Projekt.dto;

public class ReviewDTO {
    private String bookId;
    private int rating;
    private String comment;
 
    public ReviewDTO(String bookId, int rating, String comment) {
        this.bookId = bookId;
        this.rating = rating;
        this.comment = comment;
    }

       public ReviewDTO(){
    }

       public String getBookId() {
           return bookId;
       }

       public void setBookId(String bookId) {
           this.bookId = bookId;
       }

       public int getRating() {
           return rating;
       }

       public void setRating(int rating) {
           this.rating = rating;
       }

       public String getComment() {
           return comment;
       }

       public void setComment(String comment) {
           this.comment = comment;
       }



}


