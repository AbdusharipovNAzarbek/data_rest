package com.company.data_rest.service;

import com.company.data_rest.entity.Attachment;
import com.company.data_rest.entity.Category;
import com.company.data_rest.entity.Measure;
import com.company.data_rest.entity.Product;
import com.company.data_rest.payload.ProductDto;
import com.company.data_rest.payload.Result;
import com.company.data_rest.repository.AttachmentRepository;
import com.company.data_rest.repository.CategoryRepository;
import com.company.data_rest.repository.MeasureRepository;
import com.company.data_rest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MeasureRepository measureRepository;
    @Autowired
    AttachmentRepository attachmentRepository;

    public List<Product> get(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.getContent();
    }

    public Product getById(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    public Result create(ProductDto productDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (optionalCategory.isEmpty()) {
            return new Result("Bunday kategoriya topilmadi", false);
        }
        if (productRepository.existsByNameAndCategory_Id(productDto.getName(), productDto.getCategoryId())) {
            return new Result("Ushbu kategoriya bunday nomli mahsulot mavjud", false);
        }
        Optional<Measure> optionalMeasure = measureRepository.findById(productDto.getMeasureId());
        if (optionalMeasure.isEmpty()) {
            return new Result("Bunday o'lchov birligi topilmadi", false);
        }
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getAttachmentId());
        if (optionalAttachment.isEmpty()) {
            return new Result("Bunday rasm mavjud emas", false);
        }
        Product product = new Product(productDto.getName(), optionalCategory.get(),
                optionalAttachment.get(), optionalMeasure.get(), productDto.getCode());
        productRepository.save(product);
        return new Result("Mahsulot saqlandi", true);
    }

    public Result edit(Integer id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return new Result("Bunday mahsulot topilmadi", false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (optionalCategory.isEmpty()) {
            return new Result("Bunday kategoriya topilmadi", false);
        }
        Optional<Measure> optionalMeasure = measureRepository.findById(productDto.getMeasureId());
        if (optionalMeasure.isEmpty()) {
            return new Result("Bunday o'lchov birligi topilmadi", false);
        }
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getAttachmentId());
        if (productRepository.existsByNameAndCategory_Id(productDto.getName(), productDto.getCategoryId())) {
            return new Result("Ushbu kategoriya bunday nomli mahsulot mavjud", false);
        }
        if (optionalAttachment.isEmpty()) {
            return new Result("Bunday rasm mavjud emas", false);
        }
        Product product = optionalProduct.get();
        product.setActive(productDto.isActive());
        product.setCode(productDto.getCode());
        product.setMeasure(optionalMeasure.get());
        product.setCategory(optionalCategory.get());
        product.setAttachment(optionalAttachment.get());
        productRepository.save(product);
        return new Result("Mahsulot tahrirlandi", true);

    }

    public Result delete(Integer id) {
        if (productRepository.findById(id).isEmpty()) {
            return new Result("Bunday mahsulot topilmadi", false);
        }
        productRepository.deleteById(id);
        return new Result("Mahsulot o'chirildi", true);
    }
}
