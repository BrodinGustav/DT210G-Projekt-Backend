package DT210G_Projekt.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DT210G_Projekt.model.User;
import DT210G_Projekt.service.UserService;
import DT210G_Projekt.dto.AuthRequestDTO;


import DT210G_Projekt.security.JWTUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JWTUtil jwtUtil;

    public AuthController(UserService userService, JWTUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequestDTO request) {
        userService.register(request.getEmail(), request.getPassword());
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO request) {
        User user = userService.authenticate(request.getEmail(), request.getPassword());
        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(Map.of("token", token));
    }

}
