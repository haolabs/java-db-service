
package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.repo.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryRepository repo;
    public CategoryController(CategoryRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Category> list() { return repo.findAll(); }

    @PostMapping
    public Category create(@RequestBody Category c) { return repo.save(c); }

    @GetMapping("/{id}")
public ResponseEntity<Category> get(@PathVariable("id") Long id) {
    return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
}
}
