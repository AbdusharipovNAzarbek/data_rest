package com.company.data_rest.controller;

import com.company.data_rest.entity.Output;
import com.company.data_rest.entity.OutputProduct;
import com.company.data_rest.payload.OutputProductDto;
import com.company.data_rest.payload.Result;
import com.company.data_rest.service.OutputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/output_product")
public class OutputProductController {
    @Autowired
    OutputProductService outputProductService;

    @GetMapping
    public ResponseEntity<List<OutputProduct>> get(@RequestParam int page, @RequestParam int size) {
        List<OutputProduct> outputProducts = outputProductService.get(page, size);
        return ResponseEntity.ok(outputProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutputProduct> getById(@PathVariable Integer id) {
        OutputProduct outputProduct = outputProductService.getById(id);
        return ResponseEntity.status(outputProduct != null ? 200 : 404).body(outputProduct);
    }

    @PostMapping
    public ResponseEntity<Result> create(@Valid @RequestBody OutputProductDto outputProductDto) {
        Result result = outputProductService.create(outputProductDto);
        return ResponseEntity.status(result.isStatus() ? 202 : 409).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> edit(@Valid @PathVariable Integer id, @RequestBody OutputProductDto outputProductDto) {
        Result result = outputProductService.edit(id, outputProductDto);
        return ResponseEntity.status(result.isStatus() ? 203 : 409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id) {
        Result result = outputProductService.delete(id);
        return ResponseEntity.status(result.isStatus() ? 204 : 404).body(result);
    }
}