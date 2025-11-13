package com.yourorg.pocketexpense.controller;

import com.yourorg.pocketexpense.entity.Category;
import com.yourorg.pocketexpense.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) { this.categoryRepository = categoryRepository; }

    @GetMapping
    public List<Category> list() { return categoryRepository.findAll(); }

    @PostMapping
    public ResponseEntity<Category> create(@Valid @RequestBody Category c) {
        Category saved = categoryRepository.save(c);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category c) {
        return categoryRepository.findById(id).map(existing -> {
            existing.setName(c.getName());
            existing.setDescription(c.getDescription());
            Category s = categoryRepository.save(existing);
            return ResponseEntity.ok(s);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!categoryRepository.existsById(id)) return ResponseEntity.notFound().build();
        categoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
