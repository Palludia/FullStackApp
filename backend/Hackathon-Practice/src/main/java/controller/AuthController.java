package controller;

import dto.LoginRequest;
import dto.LoginResponse;
import dto.RegisterRequest;
import dto.UserResponse;
import model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest req) {
        User newUser = userService.registerUser(req);
        return ResponseEntity.ok(new UserResponse(newUser.getUsername()));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
        LoginResponse response = userService.loginUser(req);
        return ResponseEntity.ok(response);
    }
}
