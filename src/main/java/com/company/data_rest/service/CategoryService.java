package com.company.data_rest.service;


import com.company.data_rest.entity.Category;
import com.company.data_rest.payload.CategoryDto;
import com.company.data_rest.payload.Result;
import com.company.data_rest.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Result create(CategoryDto categoryDto) {
        Result result = new Result();
        Category category = new Category();
        if (categoryRepository.existsByName(categoryDto.getName())) {
            result.setMessage("Bunaqa nomli kategoriya mavjud");
            result.setStatus(false);
            return result;
        } else if (categoryDto.getParenId() != null) {

            category.setName(category.getName());
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParenId());
            if (optionalCategory.isEmpty()) {
                result.setMessage("Bunaqa nomli ota kategoriya mavjud emas");
                result.setStatus(false);
                return result;
            }
            category.setCategory(optionalCategory.get());
            categoryRepository.save(category);

            result.setMessage("Kategoriya muvaffaqiyatli saqlandi");
            result.setStatus(true);
            result.setObject(category.getId());
            return result;

        }
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        result.setMessage("Kategoriya muvaffaqiyatli saqlandi");
        result.setStatus(true);
        result.setObject(category.getId());
        return result;
    }

    public List<Category> read() {
        return categoryRepository.findAll();
    }

    public Category readById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Result update(Integer id, Category category) {
        Result result = new Result();
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category currentCategory = optionalCategory.get();
            currentCategory.setCategory(category.getCategory());
            currentCategory.setName(category.getName());
            currentCategory.setActive(category.isActive());
            result.setStatus(true);
            result.setMessage("Kategoriya muvaffaqiyatli taxrirlandi");
            return result;
        }
        result.setMessage("Kategoriya topilmadi");
        result.setStatus(false);
        return result;
    }

    public Result delete(Integer id) {
        Result result = new Result();
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            categoryRepository.deleteById(id);
            result.setStatus(true);
            result.setMessage("Kategoriya muvaffaqiyatli o'chirildi");
            return result;
        }
        result.setStatus(false);
        result.setMessage("Kategoriya topilmadi");
        return result;
    }
}
