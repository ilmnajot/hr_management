package com.example.hr_managament.dto;

import com.example.hr_managament.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class RegisterDto {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    public static RegisterDto toDto(User user){
        RegisterDto dto = new RegisterDto();

        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getUsername());

        return dto;
    }
}
