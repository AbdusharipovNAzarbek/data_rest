package com.company.data_rest.service;

import com.company.data_rest.entity.Input;
import com.company.data_rest.entity.InputProduct;
import com.company.data_rest.entity.Product;
import com.company.data_rest.payload.InputProductDto;
import com.company.data_rest.payload.Result;
import com.company.data_rest.repository.InputProductRepository;
import com.company.data_rest.repository.InputRepository;
import com.company.data_rest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class InputProductService {
    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputRepository inputRepository;

    public List<InputProduct> get(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<InputProduct> inputProductPage = inputProductRepository.findAll(pageable);
        return inputProductPage.getContent();
    }

    public InputProduct getById(Integer id) {
        return inputProductRepository.findById(id).orElse(null);
    }

    public Result create(InputProductDto inputProductDto) {
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (optionalInput.isEmpty()) {
            return new Result("Bunday kirim mavjud emas", false);
        }
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (optionalProduct.isEmpty()) {
            return new Result("Bunday mahsulot mavjud emas", false);
        }
        InputProduct inputProduct = new InputProduct(inputProductDto.getExpireDate(), inputProductDto.getPrice(),
                inputProductDto.getAmount(), optionalProduct.get(), optionalInput.get());
        return new Result("Kiruvchi mahsulot qo'shildi", true);
    }

    public Result edit(Integer id, InputProductDto inputProductDto) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isEmpty()) {
            return new Result("Bunday kiruvchi mahsulot topilmadi", false);
        }
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (optionalInput.isEmpty()) {
            return new Result("Bunday kirim mavjud emas!", false);
        }
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (optionalProduct.isEmpty()) {
            return new Result("Bunday mahsulot mavjud emas", false);
        }
        InputProduct inputProduct = optionalInputProduct.get();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpire_date(inputProductDto.getExpireDate());
        inputProductRepository.save(inputProduct);
        return new Result("Kiruvchi mahsulot saqlandi", true);
    }

    public Result delete(Integer id) {
        try {
            inputProductRepository.deleteById(id);
            return new Result("Kiruvchi mahsulot o'chirildi", true);
        } catch (Exception e) {
            return new Result("Kiruvchi mahsulot o'chirilmadi", false);
        }
    }
}
