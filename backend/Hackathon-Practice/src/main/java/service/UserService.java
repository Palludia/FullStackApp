package service;

import dto.LoginRequest;
import dto.LoginResponse;
import dto.RegisterRequest;
import exception.InvalidCredentialsException;
import exception.UsernameDoesNotExistsException;
import exception.UsernameOrEmailAlreadyExistsException;
import model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repository.UserRepository;
import security.JwtUtil;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public User registerUser(RegisterRequest req) {
        if(userRepository.findByUsername(req.getUsername()).isPresent() || userRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new UsernameOrEmailAlreadyExistsException("Username or Email is already taken");
        }
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        return userRepository.save(user);
    }

    public boolean matchPasswords(String rawPassword, String hashedPasssword) {
        return passwordEncoder.matches(rawPassword,hashedPasssword);
    }

    public LoginResponse loginUser(LoginRequest req) {
        User user = userRepository.findByUsername(req.getUsername()).orElseThrow(() -> new UsernameDoesNotExistsException("User not found"));

        if(!matchPasswords(req.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid Password!");
        }

        String token = jwtUtil.generateToken(user.getUsername());

        return new LoginResponse(token);
    }
}
