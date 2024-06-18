package com.spa.ecommerce.category;

import com.spa.ecommerce.common.GeneralService;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    Optional<CategoryDTO> getCategoryById(Long id);

    Optional<CategoryDTO> createCategory(CategoryDTO categoryDTO);

    Optional<CategoryDTO> updateCategory(Long id, CategoryDTO categoryDTO);

    Optional<CategoryDTO> deleteCategory(Long id);

    Optional<CategoryDTO> addSubCategory(Long id, CategoryDTO categoryDTO);
}
