package com.isa.manager.isa_manager_api.controllers;

import com.isa.manager.isa_manager_api.domain.User.*;
import com.isa.manager.isa_manager_api.infra.secutiry.TokenService;
import com.isa.manager.isa_manager_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        User user = this.userRepository.findByEmail(loginRequestDTO.email())
                .orElseThrow(() -> new RuntimeException("User not found."));
        if(this.passwordEncoder.matches(loginRequestDTO.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        Optional<User> user = this.userRepository.findByEmail(registerRequestDTO.email());

        if(user.isEmpty()) {
            User newUser = new User();
            newUser.setName(registerRequestDTO.name());
            newUser.setEmail(registerRequestDTO.email());
            newUser.setPassword(this.passwordEncoder.encode(registerRequestDTO.password()));
            this.userRepository.save(newUser);
            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new RegisterResponseDTO(newUser.getName(), newUser.getEmail(), newUser.getPassword(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
