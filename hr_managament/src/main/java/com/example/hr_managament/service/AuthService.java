package com.example.hr_managament.service;

import com.example.hr_managament.dto.*;
import com.example.hr_managament.response.ApiResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface AuthService {

    RegisterDto register(RegisterForm form);

    LoginDto login(LoginForm form);

    ApiResponse verify(String username, String emailCode);
}
