package com.spa.ecommerce.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<Collection<CategoryDTO>> index() {
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> get(@PathVariable("id") Long id){
        return categoryService.get(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> save(@RequestBody CategoryDTO category) {
        Optional<CategoryDTO> savedUser = categoryService.save(category);
        return savedUser.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO category) {
        Optional<CategoryDTO> updatedUserOpt = categoryService.update(id, category);
        return updatedUserOpt
                .map(CategoryDTO -> new ResponseEntity<>(CategoryDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDTO> delete(@PathVariable Long id, @RequestBody CategoryDTO category) {
        Optional<CategoryDTO> updatedUserOpt = categoryService.delete(id, category);
        return updatedUserOpt
                .map(CategoryDTO -> new ResponseEntity<>(CategoryDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/sub-category")
    public ResponseEntity<CategoryDTO> getSub(@PathVariable Long id, @RequestBody CategoryDTO category) {
        Optional<CategoryDTO> updatedUserOpt = categoryService.update(id, category);
        return updatedUserOpt
                .map(CategoryDTO -> new ResponseEntity<>(CategoryDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/sub-category")
    public ResponseEntity<CategoryDTO> saveSub(@PathVariable Long id, @RequestBody CategoryDTO category) {
        Optional<CategoryDTO> updatedUserOpt = categoryService.saveSub(id, category);
        return updatedUserOpt
                .map(CategoryDTO -> new ResponseEntity<>(CategoryDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}/sub-category/{subId}")
    public ResponseEntity<CategoryDTO> updateSub(@PathVariable Long id,@PathVariable Long subId, @RequestBody CategoryDTO category) {
        Optional<CategoryDTO> updatedUserOpt = categoryService.updateSub(id, subId, category);
        return updatedUserOpt
                .map(CategoryDTO -> new ResponseEntity<>(CategoryDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
