package com.company.data_rest.controller;

import com.company.data_rest.entity.Product;
import com.company.data_rest.payload.ProductDto;
import com.company.data_rest.payload.Result;
import com.company.data_rest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> get(@RequestParam int page, @RequestParam int size) {
        List<Product> products = productService.get(page, size);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Integer id) {
        Product product = productService.getById(id);
        return ResponseEntity.status(product != null ? 200 : 404).body(product);
    }

    @PostMapping
    public ResponseEntity<Result> create(@Valid @RequestBody ProductDto productDto) {
        Result result = productService.create(productDto);
        return ResponseEntity.status(result.isStatus() ? 201 : 409).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> edit(@Valid @PathVariable Integer id, @RequestBody ProductDto productDto) {
        Result result = productService.edit(id, productDto);
        return ResponseEntity.status(result.isStatus() ? 203 : 409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id) {
        Result result = productService.delete(id);
        return ResponseEntity.status(result.isStatus() ? 204 : 404).body(result);
    }
}
