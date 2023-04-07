package com.company.data_rest.service;

import com.company.data_rest.entity.Output;
import com.company.data_rest.entity.OutputProduct;
import com.company.data_rest.entity.Product;
import com.company.data_rest.payload.OutputProductDto;
import com.company.data_rest.payload.Result;
import com.company.data_rest.repository.OutputProductRepository;
import com.company.data_rest.repository.OutputRepository;
import com.company.data_rest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {
    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OutputRepository outputRepository;

    public List<OutputProduct> get(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return outputProductRepository.findAll(pageable).getContent();
    }

    public OutputProduct getById(Integer id) {
        return outputProductRepository.findById(id).orElse(null);
    }

    public Result create(OutputProductDto outputProductDto) {
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (optionalProduct.isEmpty()) {
            return new Result("Bunday mahsulot mavjud emas", false);
        }
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (optionalOutput.isEmpty()) {
            return new Result("Bunday chiqim topilmadi", false);
        }
        OutputProduct outputProduct = new OutputProduct(outputProductDto.getPrice(),
                outputProductDto.getAmount(), optionalProduct.get(), optionalOutput.get());
        outputProductRepository.save(outputProduct);
        return new Result("Output product saqlandi", true);
    }

    public Result edit(Integer id, OutputProductDto outputProductDto) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (optionalOutputProduct.isEmpty()) {
            return new Result("BUnday OutputProduct topilmadi", false);
        }
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (optionalOutput.isEmpty()) {
            return new Result("Bunday chiqim topilmadi", false);
        }
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (optionalProduct.isEmpty()) {
            return new Result("Bunday mahsulot mavjud emas", false);
        }
        OutputProduct outputProduct = optionalOutputProduct.get();
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setAmount(outputProductDto.getAmount());
        return new Result("OutputProduct taxrirlandi", true);
    }

    public Result delete(Integer id) {
        try {
            outputProductRepository.deleteById(id);
            return new Result("OutputProduct o'chirildi", true);
        }catch (Exception e){
            return new Result("OutputProduct o'chirilmadi",false);
        }
    }
}
