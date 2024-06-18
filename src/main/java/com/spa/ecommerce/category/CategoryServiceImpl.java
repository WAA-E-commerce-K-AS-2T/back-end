package com.spa.ecommerce.category;

import com.spa.ecommerce.user.User;
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
    public Optional<CategoryDTO> get(Long id) {
        return categoryRepo.findById(id).map(categoryDTOMapper);
    }

    @Override
    public Optional<CategoryDTO> save(CategoryDTO entity) {
        Category category = new Category();
        category.setId(entity.getId());
        category.setName(entity.getName());
        categoryRepo.save(category);

        return Optional.of(categoryDTOMapper.apply(category));
    }

    @Override
    public Optional<CategoryDTO> update(Long id, CategoryDTO updatedCategory) {
        Optional<Category> existingCategoryOpt = categoryRepo.findById(id);
        if (existingCategoryOpt.isPresent()) {
            Category existingCategory = existingCategoryOpt.get();
            existingCategory.setName(updatedCategory.getName());
            categoryRepo.save(existingCategory);

            return Optional.of(categoryDTOMapper.apply(existingCategory));
        } else {
            return Optional.empty();
        }
    }

//    @Override
//    public Optional<CategoryDTO> update(Long id, CategoryDTO updatedCategory) {
//        Optional<Category> existingCategoryOpt = categoryRepo.findById(id);
//        if (existingCategoryOpt.isPresent()) {
//            Category existingCategory = existingCategoryOpt.get();
//            updateCategoryFromDTO(existingCategory, updatedCategory);
//            categoryRepo.save(existingCategory);
//
//            return Optional.of(categoryDTOMapper.apply(existingCategory));
//        } else {
//            return Optional.empty();
//        }
//    }
//
//    private void updateCategoryFromDTO(Category category, CategoryDTO categoryDTO) {
//        category.setName(categoryDTO.getName());
//        System.out.println("I'm updating");
//
//        if (categoryDTO.getParent_category() != null) {
//            Category parentCategory;
//            if (categoryDTO.getParent_category().getId() != null) {
//                parentCategory = categoryRepo.findById(categoryDTO.getParent_category().getId())
//                        .orElse(new Category());
//            } else {
//                parentCategory = new Category();
//            }
//            updateCategoryFromDTO(parentCategory, categoryDTO.getParent_category());
//            category.setCategoryParent(parentCategory);
//        } else {
//            category.setCategoryParent(null);
//        }
//    }


    @Override
    public Optional<CategoryDTO> delete(Long id, CategoryDTO updatedCategory) {
        Optional<Category> existingCategoryOpt = categoryRepo.findById(id);
        if (existingCategoryOpt.isPresent()) {
            categoryRepo.deleteById(id);
            return Optional.of(categoryDTOMapper.apply(existingCategoryOpt.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<CategoryDTO> saveSub(Long id, CategoryDTO entity) {
        Optional<Category> existingParentCategoryOpt = categoryRepo.findById(id);
        Category category = new Category();
        category.setId(entity.getId());
        category.setName(entity.getName());
        existingParentCategoryOpt.ifPresent(category::setCategoryParent);
        categoryRepo.save(category);

        return Optional.of(categoryDTOMapper.apply(category));
    }

    @Override
    public Optional<CategoryDTO> updateSub(Long id, Long subId, CategoryDTO updatedCategory) {
        Optional<Category> existingParentCategoryOpt = categoryRepo.findById(id);
        Optional<Category> existingCategoryOpt = categoryRepo.findById(id);
        if (existingParentCategoryOpt.isPresent() && existingCategoryOpt.isPresent()) {
            Category existingCategory = existingCategoryOpt.get();
            //existingCategory.setCategoryParent(existingParentCategoryOpt.get());
            existingCategory.setName(updatedCategory.getName());

            categoryRepo.save(existingCategory);

            return Optional.of(categoryDTOMapper.apply(existingCategory));
        } else {
            return Optional.empty();
        }
    }
}
