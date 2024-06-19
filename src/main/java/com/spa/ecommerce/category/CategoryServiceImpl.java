package com.spa.ecommerce.category;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final CategoryDTOMapper categoryDTOMapper;


    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepo.findAll()
                .stream().filter(cat -> !cat.getSubCategories().isEmpty()).map(categoryDTOMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public  Optional<CategoryDTO> getCategoryById(Long id) {
        Optional<Category> category = categoryRepo.findById(id);
        return category.map(categoryDTOMapper::toDTO);
    }

    @Override
    public Optional<CategoryDTO> createCategory(CategoryDTO categoryDTO) {
        Category category = categoryDTOMapper.toEntity(categoryDTO);
        Category savedCategory = categoryRepo.save(category);
        return Optional.of(categoryDTOMapper.toDTO(savedCategory));
    }

    @Override
    public Optional<CategoryDTO> updateCategory(Long id, CategoryDTO categoryDTO) {
        Optional<Category> categoryOptional = categoryRepo.findById(id);
        if(categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            BeanUtils.copyProperties(categoryDTO, category);
            Category savedCategory = categoryRepo.save(category);
            return Optional.of(categoryDTOMapper.toDTO(savedCategory));
        }else return Optional.empty();
    }

    @Override
    public Optional<CategoryDTO> deleteCategory(Long id) {
        Optional<Category> categoryOptional = categoryRepo.findById(id);
        if(categoryOptional.isPresent()) {
            categoryRepo.deleteById(id);
            return Optional.of(categoryDTOMapper.toDTO(categoryOptional.get()));
        }else return Optional.empty();
    }

    @Override
    public Optional<CategoryDTO> addSubCategory(Long id,CategoryDTO categoryDTO) {
        Optional<Category> categoryOptional = categoryRepo.findById(id);
        if(categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            List<Category> subCategories = category.getSubCategories();
            subCategories.add(categoryDTOMapper.toEntity(categoryDTO));
            categoryRepo.save(category);
            return Optional.of(categoryDTOMapper.toDTO(category));
        }
        return Optional.empty();
    }
}
