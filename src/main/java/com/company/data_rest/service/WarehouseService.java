package com.company.data_rest.service;

import com.company.data_rest.entity.User;
import com.company.data_rest.entity.Warehouse;
import com.company.data_rest.payload.Result;
import com.company.data_rest.payload.WarehouseDto;
import com.company.data_rest.repository.UserRepository;
import com.company.data_rest.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;
    UserRepository userRepository;

    public List<Warehouse> get(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Warehouse> warehousePage = warehouseRepository.findAll(pageable);
        return warehousePage.getContent();
    }

    public Warehouse getById(Integer id) {
        return warehouseRepository.findById(id).orElse(null);
    }

    public Result create(WarehouseDto warehouseDto) {
        if (warehouseRepository.existsByName(warehouseDto.getName())) {
            return new Result("Bunday nomli ombor mavjud", false);
        }
        Optional<User> optionalUser = userRepository.findById(warehouseDto.getUserId());
        if (optionalUser.isEmpty()) {
            return new Result("Bunday user topilmadi", false);
        }
        Warehouse warehouse = new Warehouse();
        return new Result();
    }
}
