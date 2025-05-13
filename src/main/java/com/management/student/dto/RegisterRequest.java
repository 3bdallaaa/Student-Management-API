package com.management.student.dto;

import com.management.student.model.Role;
import jakarta.validation.constraints.*;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    private Role role;

}