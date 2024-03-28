package com.joaofreitas.pdvapi.web.dtos.mapper;

import com.joaofreitas.pdvapi.entities.Category;
import com.joaofreitas.pdvapi.entities.User;
import com.joaofreitas.pdvapi.web.dtos.category.CategoryCreateDto;
import com.joaofreitas.pdvapi.web.dtos.category.CategoryResponseDto;
import com.joaofreitas.pdvapi.web.dtos.user.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {
    public static Category toCategory(CategoryCreateDto createDto) {
        return new ModelMapper().map(createDto, Category.class);
    }

    public static CategoryResponseDto toDto(Category category){
        PropertyMap<Category, CategoryResponseDto> propertyMap = new PropertyMap<Category, CategoryResponseDto>() {
            protected void configure() {
                map().setDescription(source.getDescription());
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(propertyMap);
        return mapper.map(category, CategoryResponseDto.class);
    }

    public static List<CategoryResponseDto> toListDto (List<Category> obj) {
        return obj.stream().map(category -> toDto(category)).collect(Collectors.toList());
    }
}
