package com.joaofreitas.pdvapi.web.dtos;


import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserResponseDto {
    private UUID id;
    private String username;
    private String role;
}
