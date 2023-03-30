package com.company.data_rest.controller;

import com.company.data_rest.entity.Output;
import com.company.data_rest.payload.OutputDto;
import com.company.data_rest.payload.Result;
import com.company.data_rest.service.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/output")
public class OutputController {
    @Autowired
    OutputService outputService;

    @GetMapping
    public ResponseEntity<List<Output>> get(@RequestParam int page, @RequestParam int size) {
        List<Output> outputs = outputService.get(page, size);
        return ResponseEntity.ok(outputs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Output> getById(@PathVariable Integer id) {
        Output output = outputService.getById(id);
        return ResponseEntity.status(output != null ? 200 : 404).body(output);
    }

    @PostMapping
    public ResponseEntity<Result> create(@Valid @RequestBody OutputDto outputDto) {
        Result result = outputService.create(outputDto);
        return ResponseEntity.status(result.isStatus() ? 202 : 409).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> edit(@PathVariable Integer id, @RequestBody OutputDto outputDto) {
        Result result = outputService.edit(id, outputDto);
        return ResponseEntity.status(result.isStatus() ? 203 : 409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id) {
        Result result = outputService.delete(id);
        return ResponseEntity.status(result.isStatus() ? 204 : 409).body(result);
    }
}
