package com.spa.ecommerce.category;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CategoryDTOMapper implements Function<Category, CategoryDTO> {

    @Override
    public CategoryDTO apply(Category category) {
        CategoryDTO parentDTO = category.getCategoryParent() != null ? this.apply(category.getCategoryParent()) : null;
        return new CategoryDTO(category.getId(), category.getName(), parentDTO);
    }
}
