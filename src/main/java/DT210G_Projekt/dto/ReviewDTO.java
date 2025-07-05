package DT210G_Projekt.dto;

public class ReviewDTO {
    private Long id; 
    private String bookId;
    private int rating;
    private String comment;
    private Long userId;
 

    public ReviewDTO(Long id, String bookId, int rating, String comment, Long userId) {
        this.id = id;
        this.bookId = bookId;
        this.rating = rating;
        this.comment = comment;
        this.userId = userId;
    }

       public ReviewDTO(){
    }

        public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

       public Long getId() {
           return id;
       }

       public void setId(Long id) {
           this.id = id;
       }

  /*    public String getUserEmail() {
        return userEmail;
       }

       public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
       }
*/


}


