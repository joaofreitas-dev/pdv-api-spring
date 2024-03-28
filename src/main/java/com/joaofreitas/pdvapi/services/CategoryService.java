package com.joaofreitas.pdvapi.services;

import com.joaofreitas.pdvapi.entities.Category;
import com.joaofreitas.pdvapi.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category create(Category category){
        return categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public List<Category> findAll () {
        return categoryRepository.findAll();
    }
}
