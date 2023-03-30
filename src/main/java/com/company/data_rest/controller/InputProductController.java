package com.company.data_rest.controller;

import com.company.data_rest.entity.InputProduct;
import com.company.data_rest.payload.InputProductDto;
import com.company.data_rest.payload.Result;
import com.company.data_rest.service.InputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/input_product")
public class InputProductController {
    @Autowired
    InputProductService inputProductService;

    @GetMapping
    public ResponseEntity<List<InputProduct>> get(@RequestParam int page, @RequestParam int size) {
        List<InputProduct> inputProducts = inputProductService.get(page, size);
        return ResponseEntity.ok(inputProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InputProduct> getById(@PathVariable Integer id) {
        InputProduct inputProduct = inputProductService.getById(id);
        return ResponseEntity.status(inputProduct != null ? 200 : 404).body(inputProduct);
    }

    @PostMapping
    public ResponseEntity<Result> create(@Valid @RequestBody InputProductDto inputProductDto) {
        Result result = inputProductService.create(inputProductDto);
        return ResponseEntity.status(result.isStatus() ? 202 : 409).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> edit(@Valid @PathVariable Integer id, @RequestBody InputProductDto inputProductDto) {
        Result result = inputProductService.edit(id, inputProductDto);
        return ResponseEntity.status(result.isStatus() ? 203 : 409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id) {
        Result result = inputProductService.delete(id);
        return ResponseEntity.status(result.isStatus() ? 204 : 409).body(result);
    }
}
