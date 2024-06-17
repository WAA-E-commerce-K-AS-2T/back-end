package com.spa.ecommerce.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    CategoryDTOMapper categoryDTOMapper;

    @Override
    public Collection<CategoryDTO> getAll() {
        return categoryRepo.findAll().stream().map(categoryDTOMapper).collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryDTO> get(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<CategoryDTO> save(CategoryDTO entity) {
        return Optional.empty();
    }

    @Override
    public Optional<CategoryDTO> update(Long aLong, CategoryDTO entity) {
        return Optional.empty();
    }

    @Override
    public Optional<CategoryDTO> delete(Long aLong, CategoryDTO entity) {
        return Optional.empty();
    }
}
