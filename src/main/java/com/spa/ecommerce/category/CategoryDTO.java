package com.spa.ecommerce.category;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@Data
@RequiredArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private CategoryDTO parent_category;

    public CategoryDTO(Long id, String name, CategoryDTO parent_category) {
        this.id = id;
        this.name = name;
        this.parent_category = parent_category;
    }
}
