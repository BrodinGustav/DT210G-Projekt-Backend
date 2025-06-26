package DT210G_Projekt.dto;

public class ReviewDTO {
    private String bookId;
    private int rating;
    private String comment;
//     private String userEmail;
 
    public ReviewDTO(String bookId, int rating, String comment /*String userEmail*/) {
        this.bookId = bookId;
        this.rating = rating;
        this.comment = comment;
     //    this.userEmail = userEmail;
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

  /*    public String getUserEmail() {
        return userEmail;
       }

       public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
       }
*/


}


