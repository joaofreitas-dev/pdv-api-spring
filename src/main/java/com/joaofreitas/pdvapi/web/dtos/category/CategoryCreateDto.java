package com.joaofreitas.pdvapi.web.dtos.category;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryCreateDto {

    @NotBlank
    private String description;

}