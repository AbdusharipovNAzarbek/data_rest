package com.company.data_rest.controller;

import com.company.data_rest.entity.Input;
import com.company.data_rest.payload.InputDto;
import com.company.data_rest.payload.Result;
import com.company.data_rest.service.InputsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/input")
public class InputsController {
    @Autowired
    InputsService inputService;

    @GetMapping
    public ResponseEntity<List<Input>> get(@RequestParam int page, @RequestParam int size) {
        List<Input> inputs = inputService.get(page, size);
        return ResponseEntity.ok(inputs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Input> getId(@PathVariable Integer id) {
        Input input = inputService.getById(id);
        return ResponseEntity.status(input != null ? 200 : 404).body(input);
    }

    @PostMapping
    public ResponseEntity<Result> create(@Valid @RequestBody InputDto inputDto) {
        Result result = inputService.create(inputDto);
        return ResponseEntity.status(result.isStatus() ? 201 : 409).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> edit(@PathVariable Integer id, @RequestBody InputDto inputDto) {
        Result result = inputService.edit(id, inputDto);
        return ResponseEntity.status(result.isStatus() ? 203 : 409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id) {
        Result result = inputService.delete(id);
        return ResponseEntity.status(result.isStatus() ? 204 : 409).body(result);
    }

}
