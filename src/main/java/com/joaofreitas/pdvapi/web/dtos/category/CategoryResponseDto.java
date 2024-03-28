package com.joaofreitas.pdvapi.web.dtos.category;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryResponseDto {
    private Long id;
    private String description;
}
