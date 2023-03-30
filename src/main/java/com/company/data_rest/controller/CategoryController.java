package com.company.data_rest.controller;


import com.company.data_rest.entity.Category;
import com.company.data_rest.payload.CategoryDto;
import com.company.data_rest.payload.Result;
import com.company.data_rest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Result> create(@RequestBody CategoryDto categoryDto) {
        Result result = categoryService.create(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<Category>> read() {
        List<Category> categories = categoryService.read();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> readBYId(@PathVariable Integer id) {
        Category category = categoryService.readById(id);
        return ResponseEntity.status(category != null ? 200 : 404).body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> update(@PathVariable Integer id, @RequestBody Category category) {
        Result result = categoryService.update(id, category);
        return ResponseEntity.status(result.isStatus() ? 203 : 404).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id) {
        Result result = categoryService.delete(id);
        return ResponseEntity.status(result.isStatus() ? 203 : 404).body(result);
    }
}
