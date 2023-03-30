package com.company.data_rest.controller;

import com.company.data_rest.entity.Warehouse;
import com.company.data_rest.payload.Result;
import com.company.data_rest.payload.WarehouseDto;
import com.company.data_rest.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<List<Warehouse>> get(@RequestParam int page, @RequestParam int size) {
        List<Warehouse> warehouses = warehouseService.get(page, size);
        return ResponseEntity.ok(warehouses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getById(@PathVariable Integer id) {
        Warehouse warehouse = warehouseService.getById(id);
        return ResponseEntity.status(warehouse != null ? 200 : 404).body(warehouse);
    }

    @PostMapping
    public ResponseEntity<Result> create(@Valid WarehouseDto warehouseDto) {
        Result result = warehouseService.create(warehouseDto);
        return ResponseEntity.status(result.isStatus() ? 203 : 409).body(result);
    }
}
