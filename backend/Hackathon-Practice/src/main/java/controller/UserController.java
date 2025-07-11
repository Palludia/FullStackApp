package controller;


import dto.UserResponse;
import model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getProfile(Authentication auth) {
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(new UserResponse(user.getUsername()));
    }

    @GetMapping("/protected")
    public ResponseEntity<String> protectedEndPoint() {
        return ResponseEntity.ok("This is a protected endpoint!");
    }
}
