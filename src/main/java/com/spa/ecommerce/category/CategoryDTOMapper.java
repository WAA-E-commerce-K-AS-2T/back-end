package com.spa.ecommerce.category;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CategoryDTOMapper {

    public CategoryDTO toDTO(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getSubCategories() != null ? category.getSubCategories().stream().map(this::toDTO).collect(Collectors.toList()) : Collections.emptyList()
        );
    }

    public Category toEntity(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setSubCategories(categoryDTO.getSubCategories() != null ? categoryDTO.getSubCategories().stream().map(this::toEntity).collect(Collectors.toList()) : Collections.emptyList());
        return category;
    }
}
