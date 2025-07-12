package DT210G_Projekt.dto;

public class UserDTO {
    private Long userId;
    private String email;

    public UserDTO(Long userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "UserDTO{id=" + userId + ", email='" + email + "'}";
    }
}