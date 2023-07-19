package com.example.hr_managament.dto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterForm {

//    @NotNull(message = "name cannot be null")
    private String firstName;

//    @NotNull(message = "lastname cannot be null")
    private String lastName;

//    @NotNull(message = "username cannot be null")
    private String username;
}
