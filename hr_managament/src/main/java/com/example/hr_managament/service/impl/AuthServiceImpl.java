package com.example.hr_managament.service.impl;

import com.example.hr_managament.dto.*;
import com.example.hr_managament.entity.User;
import com.example.hr_managament.exception.AppException;
import com.example.hr_managament.repository.UserRepository;
import com.example.hr_managament.response.ApiResponse;
import com.example.hr_managament.security.JwtProvider;
import com.example.hr_managament.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    private final JavaMailSender javaMailSender;


    @Override
    public RegisterDto register(RegisterForm form) {
        Optional<User> optionalUser = userRepository.findUserByUsername(form.getUsername());
        if (optionalUser.isEmpty()) {
            throw new AppException("User not found");
        }
        User user = new User();
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setUsername(form.getUsername());

        int nextedInt = new Random().nextInt(999999);
        user.setEmailCode(String.valueOf(nextedInt).substring(0, 4));
        User savedUser = userRepository.save(user);
        sendMail(user.getEmailCode(), user.getUsername());
        RegisterDto dto = RegisterDto.toDto(savedUser);
        return dto;
    }

    @Override
    public LoginDto login(LoginForm form) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                form.getUsername(),
                form.getPassword()
        ));

        String token = jwtProvider.generateToken(form.getUsername());
        LoginDto dto = new LoginDto();
        dto.setToken(token);
        return dto;
    }

    @Override
    public ApiResponse verify(String username, String emailCode) {

        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (emailCode.equals(user.getEmailCode())) {
                user.setEnabled(true);
                userRepository.save(user);
                return new ApiResponse("successfully verified user", true);
            }
            return new ApiResponse("code does not match", false);
        }
        return new ApiResponse("there is no user", false);
    }

    public Boolean sendMail(String username, String emailCode) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("no-reply2023@gmail.com");
            message.setTo(username);
            message.setSubject("verify the account");
            message.setText(emailCode);
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}


