package com.example.FakeECommerce.service;

import com.example.FakeECommerce.helper.CategoryHelper;
import com.example.FakeECommerce.request.CategoryRequest;
import com.example.FakeECommerce.response.CategoryResponse;
import com.example.FakeECommerce.validator.CategoryValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryValidator categoryValidator;
    private final CategoryHelper categoryHelper;


    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        log.info("Inside saveCategory method of CategoryService");
        categoryValidator.validateCategoryRequest(categoryRequest);
        CategoryResponse categoryResponse = categoryHelper.saveCategory(categoryRequest);
        log.info("Exiting saveCategory method of CategoryService");
        return categoryResponse;

    }

    public CategoryResponse getCategoryById(Long id) {
        log.info("Inside getCategoryById method of CategoryService");
        CategoryResponse categoryResponse = categoryHelper.getCategory(id);
        log.info("Exiting getCategoryById method of CategoryService");
        return categoryResponse;

    }

    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        log.info("Inside updateCategory method of CategoryService");
        categoryValidator.validateCategoryRequest(categoryRequest);
        CategoryResponse categoryResponse = categoryHelper.updateCategory(id, categoryRequest);
        log.info("Exiting updateCategory method of CategoryService");
        return categoryResponse;
    }

    public void deleteCategory(Long id) {
        log.info("Inside deleteCategory method of CategoryService");
        categoryHelper.deleteCategory(id);
        log.info("Exiting deleteCategory method of CategoryService");
    }
}
