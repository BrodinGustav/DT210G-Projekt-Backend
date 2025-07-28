package DT210G_Projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DT210G_Projekt.dto.AuthRequestDTO;
import DT210G_Projekt.dto.RegisterDTO;
import DT210G_Projekt.security.JWTUtil;
import DT210G_Projekt.service.UserService;
import DT210G_Projekt.dto.UserDTO;
import DT210G_Projekt.model.User;
import DT210G_Projekt.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private final JWTUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthController(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // Registrering
    @PostMapping("/register")
    public ResponseEntity<RegisterDTO> registerHandler(@RequestBody User user) {

        try {

            // Kontroll om användare redan finns
            if (userRepository.existsByEmail(user.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new RegisterDTO("Användare finns redan.", ""));
            }

            // Encoding Password med Bcrypt
            String encodedPass = passwordEncoder.encode(user.getPassword());

            // Sätter det encoded password
            user.setPassword(encodedPass);
            // Spara användare
            user = userRepository.save(user);

            // Genererar JWT
            String token = jwtUtil.generateToken(user.getEmail());

            // Returnera namn, email och token
            return ResponseEntity.ok(new RegisterDTO(user.getEmail(), token));

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RegisterDTO("Registreringen misslyckades.", ""));
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO request) {

        try {
            User user = userService.authenticate(request.getEmail(), request.getPassword());
            String token = jwtUtil.generateToken(user.getEmail());

            UserDTO userDTO = new UserDTO(user.getId(), user.getEmail());
            return ResponseEntity.ok(new AuthResponse(token, userDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    public static class AuthResponse {
        private String token;
        private UserDTO user;

        public AuthResponse(String token, UserDTO user) {
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