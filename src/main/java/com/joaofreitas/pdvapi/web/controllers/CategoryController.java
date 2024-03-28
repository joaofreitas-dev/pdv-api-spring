package com.joaofreitas.pdvapi.web.controllers;

import com.joaofreitas.pdvapi.entities.Category;
import com.joaofreitas.pdvapi.services.CategoryService;
import com.joaofreitas.pdvapi.web.dtos.category.CategoryCreateDto;
import com.joaofreitas.pdvapi.web.dtos.category.CategoryResponseDto;
import com.joaofreitas.pdvapi.web.dtos.mapper.CategoryMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> create (@RequestBody @Valid CategoryCreateDto createDto) {
        Category category = categoryService.create(CategoryMapper.toCategory(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toDto(category));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> findAll() {
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(CategoryMapper.toListDto(categories));
    }
}
