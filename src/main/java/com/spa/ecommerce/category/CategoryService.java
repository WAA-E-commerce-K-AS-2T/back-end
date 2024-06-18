package com.spa.ecommerce.category;

import com.spa.ecommerce.common.GeneralService;

import java.util.Optional;

public interface CategoryService extends GeneralService<CategoryDTO, Long> {

    Optional<CategoryDTO> saveSub(Long id, CategoryDTO category);

    Optional<CategoryDTO> updateSub(Long id, Long subId, CategoryDTO category);
}
