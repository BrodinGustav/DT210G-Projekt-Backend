package DT210G_Projekt.dto;

public class AuthRequestDTO {
    private String email;
    private String password;


public AuthRequestDTO(String password, String email) {
    this.email = email;
    this.password = password;
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


}


   