package com.spa.ecommerce.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private List<CategoryDTO> subCategories;

    public CategoryDTO(Long id, String name, List<CategoryDTO> subCategories) {
        this.id = id;
        this.name = name;
        this.subCategories = subCategories;
    }
}
