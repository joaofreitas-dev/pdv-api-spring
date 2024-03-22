package com.joaofreitas.pdvapi.web.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserPasswordDto {
    @NotBlank
    @Size(min = 6, max = 8)
    private String currentPassword;
    @NotBlank
    @Size(min = 6, max = 8)
    private String newPassword;
    @NotBlank
    @Size(min = 6, max = 8)
    private String confirmPassword;
}