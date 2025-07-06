package DT210G_Projekt.dto;

public class UserDTO {
    private Long userId;
    private String userEmail;

    public UserDTO(Long userId, String userEmail) {
        this.userId = userId;
        this.userEmail = userEmail;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    @Override
    public String toString() {
        return "UserDTO{id=" + userId + ", email='" + userEmail + "'}";
    }
}