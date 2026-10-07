package com.example.FakeECommerce.controller;

import com.example.FakeECommerce.request.CategoryRequest;
import com.example.FakeECommerce.response.ApiResponse;
import com.example.FakeECommerce.response.CategoryResponse;
import com.example.FakeECommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(@RequestBody CategoryRequest categoryRequest) {
        log.info(("Inside createCategory method of CategoryController"));
        CategoryResponse categoryResponse = categoryService.saveCategory(categoryRequest);
        log.info("Exiting createCategory method of CategoryController");
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(categoryResponse, "Category created successfully"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAllcategories() {
        log.info(("Inside getAlcategories method of CategoryController"));
        List<CategoryResponse> categoryResponses = categoryService.getAllCategpries();
        log.info("Exiting getAllCategories method of CategoryController");
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(categoryResponses, "All Categories retrieved successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategoryById(@PathVariable Long id) {
        log.info(("Inside getCategoryById method of CategoryController"));
        CategoryResponse categoryResponse = categoryService.getCategoryById(id);
        log.info("Exiting getCategoryById method of CategoryController");
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(categoryResponse, "Category retrieved successfully"));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
        log.info(("Inside updateCategory method of CategoryController"));
        CategoryResponse categoryResponse = categoryService.updateCategory(id, categoryRequest);
        log.info("Exiting updateCategory method of CategoryController");
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(categoryResponse, "Category updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        log.info(("Inside deleteCategory method of CategoryController"));
        categoryService.deleteCategory(id);
        log.info("Exiting deleteCategory method of CategoryController");
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
