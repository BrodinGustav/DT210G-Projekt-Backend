package DT210G_Projekt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DT210G_Projekt.dto.AuthRequestDTO;
import DT210G_Projekt.security.JWTUtil;

import DT210G_Projekt.dto.UserDTO;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JWTUtil jwtUtil;

    public AuthController(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO request) {

        // Hårdkodat användarnamn och lösenord
        if ("user".equals(request.getUsername()) && "password".equals(request.getPassword())) {
            String token = jwtUtil.generateToken(request.getEmail());

            // Skapa ett fake user-objekt att skicka tillbaka
            UserDTO user = new UserDTO(1L, request.getEmail()); // id sätts manuellt till 1

            return ResponseEntity.ok(new AuthResponse(token, user));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public static class AuthResponse {
        private String token;
           private UserDTO user;


        public AuthResponse(String token,  UserDTO user) {
            this.token = token;
             this.user = user;
        }

        public String getToken() {
            return token;
        }
         
        public UserDTO getUser() {
            return user;
        }
    }
}