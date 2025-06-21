package DT210G_Projekt.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class User {
       @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 
       private Long id;
    private String name;
    private String email;


      @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Undviker att lösenordet inkluderas i JSON-svaret
    private String password;

    //Relationer
    @ManyToOne
    private Review review;
    @JoinColumn(referencedColumnName = "id")
    private List<Review> reviews;
    public Long getId() {
        return id;
    }




    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Review getReview() {
        return review;
    }
    public void setReview(Review review) {
        this.review = review;
    }
    public List<Review> getReviews() {
        return reviews;
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }




    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((review == null) ? 0 : review.hashCode());
        result = prime * result + ((reviews == null) ? 0 : reviews.hashCode());
        return result;
    }




    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (review == null) {
            if (other.review != null)
                return false;
        } else if (!review.equals(other.review))
            return false;
        if (reviews == null) {
            if (other.reviews != null)
                return false;
        } else if (!reviews.equals(other.reviews))
            return false;
        return true;
    }




    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", review="
                + review + ", reviews=" + reviews + "]";
    }


    
}


