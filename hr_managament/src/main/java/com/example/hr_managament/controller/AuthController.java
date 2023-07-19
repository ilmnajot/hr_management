package com.example.hr_managament.controller;
import com.example.hr_managament.dto.*;
import com.example.hr_managament.response.ApiResponse;
import com.example.hr_managament.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    @PostMapping("/register")
    public HttpEntity<RegisterDto> register(@Valid @RequestBody RegisterForm form) {
        RegisterDto registered = authService.register(form);
        return ResponseEntity.ok(registered);
    }
    @PutMapping("/verifyEmail")
    public HttpEntity<ApiResponse> verify(@RequestParam String username, @RequestParam String emailCode) {
        ApiResponse apiResponse = authService.verify(username, emailCode);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 408).body(apiResponse);
    }
    @PostMapping("/login")
    public LoginDto login(@RequestBody LoginForm form) {
        return authService.login(form);

    }

}
