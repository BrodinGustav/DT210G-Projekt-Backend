package DT210G_Projekt.dto;

public class AuthRequestDTO {
    private String email;
    private String password;
        private String username;


public AuthRequestDTO(String password, String email, String username) {
    this.email = email;
    this.password = password;
    this.username = username;
}


public String getEmail() {
    return email;
}


public void setPassword(String password) {
    this.password = password;
}


public void setEmail(String email) {
    this.email = email;
}


public String getPassword() {
    return password;
}


public String getUsername() {
  return username;
}
}


   