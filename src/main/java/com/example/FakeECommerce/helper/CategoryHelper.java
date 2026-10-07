package com.example.FakeECommerce.helper;

import com.example.FakeECommerce.entity.Category;
import com.example.FakeECommerce.exception.ExceptionHelper;
import com.example.FakeECommerce.mapper.CategoryMapper;
import com.example.FakeECommerce.repository.CategoryRepository;
import com.example.FakeECommerce.request.CategoryRequest;
import com.example.FakeECommerce.response.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class CategoryHelper {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ExceptionHelper exceptionHelper;

    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        log.info("Inside saveCategory method of CategoryHelper");
        try {
            Category category = categoryMapper.mapToCategory(categoryRequest);
            Category savedCategory = categoryRepository.save(category);
            log.info("Category Saved Successfully");
            return categoryMapper.mapToCategoryResponse(savedCategory);
        }catch (Exception e) {
            throw new RuntimeException("Error while saving category");
        }
    }

    public CategoryResponse getCategory(Long id) {
        log.info("Inside getCategory method of CategoryHelper");
            Category category = getCategoryById(id);
            log.info("Category Found Successfully");
            return  categoryMapper.mapToCategoryResponse(category);

    }

    public List<CategoryResponse> getAllCategories() {
        log.info("Inside getAllCategories method of CategoryHelper");
        List<Category> categories = categoryRepository.findAll();
        if(CollectionUtils.isEmpty(categories)) {
            log.warn("Category Not Found");
            return new ArrayList<>();
        }
        log.info("Categories Found Successfully");
        return categories.stream()
                .map(categoryMapper::mapToCategoryResponse)
                .toList();
    }

    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        log.info("Inside updateCategory method of CategoryHelper");
        Category category = getCategoryById(id);
        category.setCategoryName(categoryRequest.getCategoryName());
        categoryRepository.save(category);
        log.info("Category Updated Successfully");
        return categoryMapper.mapToCategoryResponse(category);
    }

    public void deleteCategory(Long id ) {
        log.info("Inside deleteCategory method of CategoryHelper");
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
        log.info("Category Deleted Successfully");
    }


    private Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Found"));
    }

}
