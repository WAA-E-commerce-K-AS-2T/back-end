package com.spa.ecommerce.category;

import com.spa.ecommerce.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(Constant.CATEGORY_URL_PREFIX)
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<Collection<CategoryDTO>> index() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> get(@PathVariable("id") Long id){
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> save(@RequestBody CategoryDTO category) {
        Optional<CategoryDTO> savedUser = categoryService.createCategory(category);
        return savedUser.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO category) {
        Optional<CategoryDTO> updatedUserOpt = categoryService.updateCategory(id, category);
        return updatedUserOpt
                .map(CategoryDTO -> new ResponseEntity<>(CategoryDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDTO> delete(@PathVariable Long id) {
        Optional<CategoryDTO> updatedUserOpt = categoryService.deleteCategory(id);
        return updatedUserOpt
                .map(CategoryDTO -> new ResponseEntity<>(CategoryDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/sub-category")
    public ResponseEntity<CategoryDTO> saveSub(@PathVariable Long id, @RequestBody CategoryDTO subCategory) {
        Optional<CategoryDTO> updatedUserOpt = categoryService.addSubCategory(id, subCategory);
        return updatedUserOpt
                .map(CategoryDTO -> new ResponseEntity<>(CategoryDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
